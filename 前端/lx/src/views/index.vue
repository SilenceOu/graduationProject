<template>
  <div style="text-align: center;margin-top: 150px">
    <h1>商家后台管理系统登录界面</h1>
    <div>
      用户名:<el-input style="width: 200px" v-model="username" placeholder="请输入用户名">
      <i class="el-icon-user-solid" slot="prefix" />
    </el-input>
      <br>
      <br>

      密码:<el-input style="width: 200px" v-model="password" placeholder="请输入密码" show-password>
      <i class="el-icon-lock" slot="prefix" />
    </el-input>
    </div>
    <br>
    <el-button @click="login">登录</el-button>
    <el-button @click="$router.push('/register')">注册</el-button>


  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        username:'',
        password:''
      }
    },
    methods:{
      login(){
        let param={username:this.username,password:this.password}
        this.$post('/manager/login', param, res => {

          if (res.data !== '用户名错误，请重新输入' && res.data !== '密码错误，请重新输入'){
            this.$message({
              type: 'success',
              message: `${res.data}`
            });
            this.$router.push('/homePage')
          }else {
            this.$message({
              type: 'error',
              message: `${res.data}!`
            });
          }
        })
      },

    },
    mounted() {

    }
  }
</script>

<style lang="less" scoped>

</style>
