<template>
  <el-upload class="avatar-uploader" action="http://localhost:8010/file/upload/img" :show-file-list="false"
    :on-success="handleAvatarSuccess">
    <img v-if="defaultUrl" :src="defaultUrl" class="avatar" />
    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
  </el-upload>

</template>

<script>

export default {
  name: "upload",
  data() {
    return {
      defaultUrl: ''
    }
  },
  props: {
    imgUrl: {
      type: String,
      default: ''
    }
  },
  methods: {
    handleAvatarSuccess(res) {
      //res就是文件路径
      this.defaultUrl = res;
    },
  },
  created() {
    this.defaultUrl = this.imgUrl
  }

};

</script>

<style scoped>
.avatar-uploader-icon {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  font-size: 28px;
  color: #8c939d;
  width: 250px;
  height: 150px;
  line-height: 150px;
  text-align: center;
}

.avatar-uploader-icon:hover {
  border-color: #409eff;
}

.avatar {
  width: 250px;
  height: 150px;
  display: block;
}
</style>

