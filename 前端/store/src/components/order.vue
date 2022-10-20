<template>
  <div>
    <div class="div">
      <h2 style="margin-left: 150px">订单信息</h2>
      <el-card class="box-card" style="margin: 20px auto" v-for="(item,index) in tableData" :key="index">
        <div slot="header" class="clearfix">
          <span>收货人:{{item.address.name}}</span>
          <span style="margin-left: 40px">收货地址:{{item.address.city}}{{item.address.detailAddress}}</span>
          <span style="margin-left: 40px">联系电话:{{item.address.phone}}</span>
          <el-button v-if="item.status=='已发货'" style="float: right; padding: 3px 0" type="text"
            @click="openReceive(item.id)">确认收货
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
      <div style="margin-left: 50%;width: 20px">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </div>

    <el-dialog title="确认收货" :visible.sync="dialogVisible" width="30%">
      <span>确认已经收到货物?</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="receive">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "order",
  data() {
    return {
      dialogVisible: false,
      tableData: [],
      pageSize: 5,
      pageNum: 1,
      total: 0,
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
    openReceive(id) {
      this.orderReceiveId = id
      this.dialogVisible = true
    },
    receive() {
      let param = { id: this.orderReceiveId, status: '已收货' }
      this.$post('/order/receive', param, res => {

        this.search()
        this.dialogVisible = false
      })
    },

    cancel() {
      this.dialogFormVisible = false
    },
    search() {
      let param = {}
      param.pageable = {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      param.userId = JSON.parse(localStorage.getItem('user')).id
      this.$post('/order/searchByUser', param, res => {
        this.tableData = res.data.result.list
        this.total = res.data.result.total
      })
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.search()
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.search()
    },
  },
  created() {
    this.search()
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
  width: 900px;
}
</style>
