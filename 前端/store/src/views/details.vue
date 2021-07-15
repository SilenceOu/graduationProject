<template>
  <div class="div">
    <div style="display: flex">
      <img :src="data.image" style="width: 400px;height: 400px;margin: 20px 80px 20px 220px">
      <div><h2 style="width: 400px;margin-top:40px">{{data.name}}</h2>
        <div style="width: 400px;height: 100px;background: antiquewhite"><h1 style="line-height: 100px">
          价格：<span style="color: #FF4400">￥{{data.money}}</span>

        </h1></div>

        <div style="display: flex;align-items: center">
          <h3>购买数量:</h3>
          <el-input-number v-model="num" @change="handleChange" :min="1" :max="data.number" label="描述文字"></el-input-number>
        </div>
        <h3>库存:{{data.number}}</h3>
        <el-button @click="downOrder">立即购买</el-button>
        <el-button @click="saveCart">加入购物车</el-button>
      </div>
    </div>
    <el-tabs v-model="activeName" style="margin-left: 220px;width: 880px" type="card" @tab-click="handleClick">
      <el-tab-pane label="商品详情" name="first">
        <div class="content" v-html="data.content"></div>
      </el-tab-pane>

    </el-tabs>

  </div>
</template>

<script>
  export default {
    name: "details",
    data() {
      return {
        activeName: 'first',
        data: {},
        num: 1,
        prop: [],
        label: [],
        commodityData: {}
      }
    },

    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      },
      handleChange(e) {
        this.num = e
      },

      downOrder() {
        let param = {}
        param.userId = JSON.parse(localStorage.getItem('user')).id
        param.name = this.data.name
        param.number = this.num
        param.money = this.data.money
        this.$router.push({path: '/downOrder', query: {data: [param], allMoney: this.data.money * this.num}})
      },
      saveCart() {
        let param = {}
        param.userId = JSON.parse(localStorage.getItem('user')).id
        param.name = this.data.name
        param.number = this.num
        param.amount = this.data.money * this.num
        this.$post('/user/saveCart', param, res => {
          this.$message({
            type: 'success',
            message: `加入购物车成功!`
          });

        })
      }
    },
    created() {
      let param = this.$route.query
      this.$post('/commodity/getDetails', param, res => {
        this.data = res.data
      })

    }
  }
</script>

<style lang="less">
  .div {
    /deep/ .el-card {
      border: 2px solid antiquewhite;
    }
    /deep/.el-radio{
      margin-right: 0px;
    }
  }
  .content {
    img {
      width: 880px;
      height: 500px;
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
    width: 875px
  }
</style>
