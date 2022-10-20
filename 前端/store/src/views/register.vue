<template>
  <div style="margin: 0 auto;width: 500px">
    <h1 style="margin-left:100px" v-if="!$route.query.id">注册界面</h1>
    <h1 style="margin-left:100px" v-if="$route.query.id">基本信息界面</h1>
    <el-form label-position="right" label-width="80px" :model="formLabelAlign">
      <el-form-item label="用户名">
        <el-input v-model="formLabelAlign.username" style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="formLabelAlign.password" style="width: 300px" show-password></el-input>
      </el-form-item>
      <el-form-item label="联系方式">
        <el-input v-model="formLabelAlign.phone" style="width: 300px"></el-input>
      </el-form-item>
      <el-form-item label="头像">
        <z-upload ref="upload" :imgUrl="formLabelAlign.avatar"></z-upload>
      </el-form-item>
    </el-form>
    <el-button v-if="!$route.query.id" @click="register(0)">注册</el-button>
    <el-button v-if="!$route.query.id" @click="$router.push({path:'/index'})">返回登录界面</el-button>
    <el-button v-if="$route.query.id" @click="register(1)">保存</el-button>
  </div>
</template>
<script>
  export default {
    name: "register",
    data() {
      return {
        formLabelAlign: {
          username: '',
          password: '',
          phone: '',
          avatar: []
        },
        show:false
      }
    },
    methods: {
      register(e) {
        this.formLabelAlign.avatar = this.$refs.upload.defaultUrl
        this.formLabelAlign.pageable = {}
        let param = this.formLabelAlign
        
        if (!e) {
          this.$post('/user/register', param, res => {
            if (res.data == '注册成功' ){
              this.$message({
                type: 'success',
                message: `${res.data}!`
              });
              this.$router.push({path: '/index'})
            }else {
              this.$message({
                type: 'error',
                message: `${res.data}!`
              });
            }
            console.log(res.data)
          })
        } else {
          this.$post('/user/update', param, res => {
            this.$message({
              message: '保存成功',
              type: 'success'
            });
            localStorage.setItem('user',JSON.stringify(param))
            this.$store.commit('saveUser',param)
            this.$router.push({path: '/homePage'})
            console.log(res.data)
          })
        }

      },
    },
    created() {
      if (this.$route.query.id) {
        this.formLabelAlign = JSON.parse(localStorage.getItem('user'))
        this.formLabelAlign.avatar=[this.formLabelAlign.avatar]
        this.show=true
        console.log(this.formLabelAlign)
      }
    }
  }
</script>

<style scoped>

</style>
