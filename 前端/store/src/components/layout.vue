<template>
  <div>
  <el-col :span="4">
    <div style="height: 50px;text-align: center;line-height: 50px;background: #409EFF;color: aliceblue"><span>商家后台管理系统</span></div>
    <el-menu
      style="height: 600px;"
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
      <el-menu-item index="3">
        <i class="el-icon-user"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/commodity">
        <i class="el-icon-setting"></i>
        <span slot="title">商品管理</span>
      </el-menu-item>
      <el-menu-item index="5">
        <i class="el-icon-setting"></i>
        <span slot="title">订单管理</span>
      </el-menu-item>
      <el-menu-item index="6">
        <i class="el-icon-setting"></i>
        <span slot="title">banner图管理</span>
      </el-menu-item>
    </el-menu>
  </el-col>
    <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-top:20px;height: 20px;width: 1000px;display: inline-block">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>活动管理</el-breadcrumb-item>
      <el-breadcrumb-item>活动列表</el-breadcrumb-item>
      <el-breadcrumb-item>活动详情</el-breadcrumb-item>
    </el-breadcrumb>
    <el-button type="danger" @click="$router.push('/index')" round>退出登录</el-button>
  </div>
</template>

<script>
    export default {
        name: "layout"
    }
</script>

<style scoped>

</style>
