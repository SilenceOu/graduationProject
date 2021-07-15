<template>
  <div id="app">
    <div v-if="$route.path!=='/index'&&$route.path!=='/'&&$route.path!=='/register'||$route.query.id"
         style="width: 100%;background: #d9d9d9;height: 40px;display: flex;align-items:center">

      <h3 style="margin-left: 280px">欢迎光临淘淘商城！</h3>
      <div :style="`width: 30px;height: 30px;border-radius: 20px;margin-left: 700px;
        background-image: url(${user.avatar});background-size:100% 100%;`"></div>
      {{user.username}}
      <el-dropdown @command="handleCommand" style="margin-left: 20px">
  <span class="el-dropdown-link">
    我的淘淘<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="/homePage">返回商城首页</el-dropdown-item>
          <el-dropdown-item command="/register">基本信息</el-dropdown-item>
          <el-dropdown-item command="/cart">购物车</el-dropdown-item>
          <el-dropdown-item command="/order">我的订单</el-dropdown-item>
          <el-dropdown-item command="/address">收货地址</el-dropdown-item>
          <el-dropdown-item command="/index" divided>退出</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {

      }
    },
    computed: {
      user(){
        return this.$store.state.user?this.$store.state.user:JSON.parse(localStorage.getItem('user'))
      }
    },
    watch: {},
    methods: {
      handleCommand(command) {
        if (command == '/register') {
          this.$router.push({path: command, query: {id: this.user.id}})
        } else if (command == '/index') {
          this.$store.commit('saveUser', {})
          localStorage.removeItem('user')
          console.log(this.$store.state.user)
          this.$router.push({path: command})
        } else {
          this.$router.push({path: command})
        }
      }
    }
  }
</script>

<style lang="less">
  #app {
    margin: 0;
    padding: 0;
  }

  body, html {
    height: 100%;
  }
</style>
