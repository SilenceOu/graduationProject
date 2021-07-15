<template>
  <div style="text-align: center;margin-top: 150px">
    <h1>淘淘商城登录界面</h1>
    <div>
      用户名:
      <el-input style="width: 200px" v-model="username" placeholder="请输入内容"><i class="el-icon-user-solid" slot="prefix" />
      </el-input>
      <br>
      <br>
      密码:
      <el-input style="width: 200px" v-model="password" placeholder="请输入内容"  show-password><i class="el-icon-lock" slot="prefix" /></el-input>
    </div><br>
    <el-button @click="login">登录</el-button>
    <el-button @click="$router.push('/register')">注册</el-button>




  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        username: '',
        password: ''
      }
    },
    methods: {
      login() {
        let param = {username: this.username, password: this.password}
        this.$post('/user/login', param, res => {
          if (res.data.message !== '用户名错误，请重新输入' && res.data.message !== '密码错误，请重新输入') {
            this.$message({
              type: 'success',
              message: `${res.data.message}`
            });
            this.$store.commit('saveUser',res.data.userList[0])
            localStorage.setItem('user',JSON.stringify(res.data.userList[0]))
            this.$router.push('/homePage');
          }else {
            this.$message({
              type: 'error',
              message: `${res.data.message}!`
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
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
