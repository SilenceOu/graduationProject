<template>
  <div>
    <div class="page" style="text-align: center">
      <div style="width: 100px;color: #409EFF;height: 20px"></div>
      <el-input style="width: 200px" enter-button="搜索" placeholder="请输入商品名称" v-model="name">
        <el-button slot="append" icon="el-icon-search" @click="search"></el-button>
      </el-input>
      <div style="display:flex;flex-wrap:wrap;align-items:flex-start;width: 1280px;margin: -20px auto;">
        <div v-for="(item,i) in commodityData" class="card" style="width:320px;height: 400px;margin-top: 34px"
          @click="details(i)" :key="i">
          <div @click="">
            <div
              :style="`height:200px;width:200px;border-radius: 20px;margin-left:60px;background-image: url(${item.image});background-size:100% 100%`">
            </div>
            <h3 style="word-wrap:break-word;margin-top: 13px;width: 284px;margin-left:18px ">
              商品名称:{{item.name}}</h3>
            <h2 style="color: red;margin-top: 18px;text-align: center">价格:￥{{item.money}}</h2>
          </div>
        </div>
      </div>
      <div style="margin-left: 50%;width: 20px">
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
          :page-sizes="[5, 10, 15, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
          :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "homePage",
  data() {
    return {
      id: '',
      total: 0,
      pageSize: 5,
      pageNum: 1,
      commodityData: [],
      name: ''
    }
  },
  methods: {
    details(i) {
      this.$router.push({ path: '/details', query: { id: this.commodityData[i].id } })
    },
    search() {
      let param = {}
      if (this.name != '') {
        param.name = this.name
      }
      param.pageable = {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      this.$post('/commodity/searchByStore', param, res => {
        this.commodityData = res.data.result.list
        this.total = res.data.result.total
        this.commodityData.map((s, i) => {
          let money = []
          s.money.map((item, index) => {
            money.push(item.money)
          })
          money.sort(function (a, b) {
            return a - b
          })
          s.minMoney = money[0]
          s.maxMoney = money[money.length - 1]
          console.log(s)
        })
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
  },
}
</script>

<style scoped>

</style>
