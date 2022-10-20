<template>
  <div style="padding-top: 30px;">
    <el-table border ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 80%;margin: 0 auto"
      @selection-change="handleSelectionChange">

      <el-table-column v-if="modal!=='downOrderAddress'&&modal!=='downOrder'" type="selection" width="55"
        align="center">
      </el-table-column>

      <el-table-column label="选择" width="70" v-if="modal=='downOrderAddress'" align="center">
        <template slot-scope="scope">
          <el-radio v-model="radio" :label="scope.$index" @change="getCurrentRow(scope.$index,scope.row)">&nbsp;
          </el-radio>
        </template>
      </el-table-column>

      <el-table-column v-for="(item ,i) in tableColumn" :key="item" :label="item.label" :prop="item.prop"
        align="center">
      </el-table-column>

      <el-table-column width="210px" prop="number" label="数量" v-if="modal=='cart'" align="center">
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.number" @change="handleChange($event,scope.$index,scope.row)" :min="1"
            :max="1000" label="描述文字"></el-input-number>
        </template>
      </el-table-column>

      <el-table-column prop="pictures" label="商品图片" v-if="modal=='cart'" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.image" width="40" height="40" class="head_pic" />
        </template>
      </el-table-column>

      <el-table-column label="操作" v-if="modal!=='downOrderAddress'&&modal!=='downOrder'" align="center">
        <template slot-scope="scope">
          <el-button v-if="modal=='address'" size="mini" @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>

    </el-table>

    <div style="margin-left: 50%;width: 20px" v-if="modal!=='downOrder'">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
        :page-sizes="[5, 10, 15, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

    <div v-if="modal=='cart'" style="margin-left: 900px">
      <h1>总价格:￥{{allMoney}}</h1>
      <el-button @click="downOrder">立即下单</el-button>
    </div>
    <h1 v-if="modal=='downOrder'" style="margin-left: 900px">总价格:￥{{allMoney}}</h1>
    <el-dialog title="下单失败" :visible.sync="dialogVisible" width="30%">
      <p v-for="(s,i) in errorCommodity" :key="i">商品{{s.name}}库存为{{s.number}},库存不足</p>
      <p>请重新选择下单商品数量</p>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
import bus from '@/bus.js';
import { postJson } from '@/request/axios.js';
export default {
  name: "table",
  data() {
    return {
      errorCommodity: [],
      dialogVisible: false,
      addressRow: {},
      radio: 0,
      allMoney: 0,
      searchData: {},
      total: 0,
      pageSize: 5,
      pageNum: 1,
      tableData: [{
        date: '2016-05-03',
        username: '王小虎',
        phone: '上海市普陀区金沙江路 1518 弄',
        img: ''
      }],
      multipleSelection: [],
      tableColumn: [{ prop: 'username', label: '用户名' },
      { prop: 'phone', label: '联系方式' }]
    }
  },
  props: {
    column: [],
    modal: ''
  },
  watch: {
    column: {
      immediate: true, /*绑定的时候就执行*/
      handler(columns) {
        console.log(columns)
        this.tableColumn = columns
      }
    },

  },
  methods: {
    getCurrentRow(i, e) {
      this.addressRow = e
      console.log(i, e)
    },
    handleChange(val, i, e) {
      let data = this.tableData
      data[i].amount = data[i].money * val
      this.tableData = data
      this.handleSelectionChange(0)
      let param = JSON.parse(JSON.stringify(data[i]))

      this.$post('/user/updateCart', param, res => {

      })
    },
    search() {
      this.searchData.pageable = {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      let param = JSON.parse(JSON.stringify(this.searchData))

      if (this.modal == 'commodity') {
        this.$post('/commodity/search', param, res => {
          console.log(res.data)
          
          this.tableData = res.data
        })
      }
      if (this.modal == 'userOrder') {
        param.userId = JSON.parse(localStorage.getItem('user')).id
        this.$post('/order/searchByUser', param, res => {
          console.log(res.data)
          
          this.tableData = res.data
        })
      }
      if (this.modal == 'manager') {
        this.$post('/manager/search', param, res => {
          console.log(res.data)
          this.tableData = res.data
        })
      }
      if (this.modal == 'cart') {
        param.userId = JSON.parse(localStorage.getItem('user')).id
        this.$post('/user/getCart', param, res => {
          this.tableData = res.data.result.list
          this.total = res.data.result.total
        })
      }
      if (this.modal == 'address' || this.modal == 'downOrderAddress') {
        param.userId = JSON.parse(localStorage.getItem('user')).id
        this.$post('/user/getAddress', param, res => {
          this.tableData = res.data.result.list
          this.total = res.data.result.total
          if (this.modal == 'downOrderAddress') {
            this.addressRow = this.tableData[0]
          }
        })
      }
    },

    downOrder() {
      let param = []
      this.multipleSelection.map((s, i) => {
        let index = param.findIndex((item) => {
          return item.name == s.name
        })
        if (index == -1) {
          param.push({ name: s.name, number: s.number })
        } else {
          param[index].number = param[index].number + s.number
        }
      })
      console.log(param)
      postJson('/commodity/countNumber', JSON.stringify(param), res => {
        if (res.data.memo == '可以下单') {
          this.$router.push({ path: '/downOrder', query: { data: this.multipleSelection, allMoney: this.allMoney } })
        } else {
          this.errorCommodity = res.data.commodityList
          this.dialogVisible = true
        }
      })

    },
    handleSelectionChange(val) {
      if (val !== 0) {
        this.multipleSelection = val;
      }
      let money = 0
      this.multipleSelection.map((s) => {
        money = money + parseInt(s.amount)
      })
      this.allMoney = money
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.search()
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.search()
    },
    handleEdit(index, row) {
      this.$emit('edit', row, index)
      console.log(index, row);
    },
    handleDelete(index, row) {
      this.tableData.splice(index, 1)
      if (this.modal == 'cart') {
        this.$post('/user/deleteCart', { id: row.id }, res => {
        })
      }
      if (this.modal == 'address') {
        this.$post('/user/deleteAddress', { id: row.id }, res => {
        })
      }
    }
  },
  created() {
    if (this.modal == 'downOrder') {
      console.log(this.$route.query)
      this.allMoney = this.$route.query.allMoney
      this.tableData = this.$route.query.data
    }
    this.search()
    bus.$on('onSearch', () => {
      this.searchData = this.$store.state.searchValue
      this.pageNum = 1
      this.search()
    })

  }
}
</script>

<style scoped>

</style>
