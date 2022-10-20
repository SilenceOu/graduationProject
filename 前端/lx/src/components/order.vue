<template>
  <div>
    <div class="div">

      <el-card class="box-card" style="margin: 2px 250px" v-for="(item,index) in tableData" :key="index">
        <div slot="header" class="clearfix">
          <span>收货人:{{item.address.name}}</span>
          <span style="margin-left: 40px">收货地址:{{item.address.city}}{{item.address.detailAddress}}</span>
          <span style="margin-left: 40px">联系电话:{{item.address.phone}}</span>
          <el-button v-if="item.status!=='已发货'&&item.status!=='已收货'" style="float: right; padding: 3px 0" type="text"
            @click="openReceive(item.id)">确认发货
          </el-button>
        </div>
        <div v-for="(s,i) in item.commodityList" :key="i" style="margin: 15px 0;display: flex;align-items: center">
          <img :src="s.image" style="width: 50px;height: 50px">
          <span style="margin-left: 30px">商品名称:{{s.name}}</span>
          <span style="margin-left: 30px">数量:{{item.numbers[i]}}</span>
          <span style="margin-left: 30px">单价:￥{{s.money}}</span>
          <span style="margin-left: 30px">价格:￥{{s.money*item.numbers[i]}}</span>
        </div>
        <span style="margin-left: 500px">订单状态:{{item.status}}</span>
        <span style="margin-left: 30px">总价格:￥{{item.allMoney}}</span>
      </el-card>
    </div>


    <el-dialog title="确认发货" :visible.sync="dialogVisible" width="30%">
      <span>确认已经发出货物?</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="receive">确 定</el-button>
      </span>
    </el-dialog>
    <div style="margin-left: 800px;width: 20px">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
        :page-sizes="[5, 10, 15, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import bus from '@/bus.js'
export default {
  name: "order",
  data() {
    return {
      searchData: {},
      dialogVisible: false,
      tableData: [],
      total: 0,
      pageSize: 5,
      pageNum: 1,
      dialogFormVisible: false,
      form: {
        content: '',
        level: ''
      },
      name: '',
      index: '',
      commodityIndex: '',
      orderId: '',
      orderReceiveId: '',
    }
  },
  props: {},
  methods: {
    handleSizeChange(val) {
      this.pageSize = val
      this.search()
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.search()
    },
    openReceive(id) {
      this.orderReceiveId = id
      this.dialogVisible = true
    },
    receive() {
      let param = { id: this.orderReceiveId, status: '已发货' }
      this.$post('/order/receive', param, res => {
        this.search()
        this.dialogVisible = false
      })
    },
    cancel() {
      this.dialogFormVisible = false
    },
   
    search() {
      this.searchData.pageable = {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      let param = JSON.parse(JSON.stringify(this.searchData))
      this.$post('/order/search', param, res => {
        this.tableData = res.data.result.list
        this.total = res.data.result.total
      })
    }
  },
  created() {
    this.search()
    bus.$on('onSearchOrder', () => {
      this.searchData = this.$store.state.searchValue
      this.pageNum = 1
      this.search()
    })
  }
}
</script>

<style scoped lang="less">
.div {
  /deep/ .el-card {
    border: 6px solid cornflowerblue;
  }
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 80%;
}
</style>
