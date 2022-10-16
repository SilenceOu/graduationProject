<template>
  <div style="height: 100%;" >
    <el-col :span="4" style="height: 100%;position: fixed;">
      <div style="height: 50px;text-align: center;line-height: 50px;background: #409EFF;color: aliceblue">
        <span>商家后台管理系统</span></div>
      <el-menu
        :style="`height:100%;`"
        router
        :default-active="$route.path"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#409EFF">
        <el-menu-item index="/manager">
          <i class="el-icon-menu"></i>
          <span slot="title">管理员管理</span>
        </el-menu-item>
        <el-menu-item index="/user">
          <i class="el-icon-user"></i>
          <span slot="title">用户管理</span>
        </el-menu-item>
        <el-menu-item index="/commodity">
          <i class="el-icon-setting"></i>
          <span slot="title">商品管理</span>
        </el-menu-item>
        <el-menu-item index="/order">
          <i class="el-icon-setting"></i>
          <span slot="title">订单管理</span>
        </el-menu-item>

      </el-menu>

    </el-col>
    <div style="height: 60px">
    <el-breadcrumb separator-class="el-icon-arrow-right"
                   style="margin-top:20px;margin-left:18%;height: 20px;width: 1000px;display: inline-block">
      <el-breadcrumb-item
        v-for="(item,index) in breadcrumbList"
        :to="{ path: item.path}"
        :key="item"
      >
        {{item.name}}
      </el-breadcrumb-item>
    </el-breadcrumb>

    <el-button type="danger" @click="$router.push('/index')" round>退出登录</el-button></div>
  </div>
</template>

<script>
  export default {
    name: "layout",
    data() {
      return {
        height: ''
      }
    },
    methods: {},
    watch: {

    },
    created() {

    },
    computed: {
      breadcrumbList(){
        return this.$route.meta.breadcrumb;
      },
    },

    mounted() {

    }
  }
</script>

<style scoped lang="less">

</style>
