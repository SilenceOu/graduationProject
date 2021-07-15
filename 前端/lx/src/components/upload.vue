<template>
  <div>
    <!--    <el-upload-->
    <!--      action="http://localhost:3001/upload/headImage"-->
    <!--      list-type="picture-card"-->
    <!--      :on-preview="handlePictureCardPreview"-->
    <!--      :on-remove="handleRemove">-->
    <!--      <i class="el-icon-plus"></i>-->
    <!--    </el-upload>-->
    <!--    <el-dialog :visible.sync="dialogVisible">-->
    <!--      <img width="100%" :src="dialogImageUrl" alt="">-->
    <!--    </el-dialog>-->
    <el-upload
      ref="upload"
      action="http://localhost:3001/upload/headImage"
      list-type="picture-card"
      :file-list="defaultList"
      :limit="limit"
      :on-preview="handlePictureCardPreview"
      :on-remove="handleRemove"
      :on-success="handleSuccess"
      :on-exceed="handleExceed"
      :beforeUpload="onBeforeUpload"
    >
      <i class="el-icon-plus"></i>
    </el-upload>
    <el-dialog :visible.sync="dialogVisible"  :modal-append-to-body='false'>
      <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "upload",
  data(){
    return{
      defaultList:[],
      dialogImageUrl: '',
      dialogVisible: false,
      saveList:[]
    }
  },
  props:{
    imgList: {
      type: Array,
      default: []
    },
  },
  watch:{

  },
  methods:{
    handleSave(){
      this.defaultList.map((s)=>{
        this.saveList=[]
        this.saveList.push(s.response.data)
      })

    },
    handleSuccess(response, file, fileList){
      console.log(response,file, fileList);
      this.defaultList.push(file);
      console.log(this.defaultList)

    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      let index = fileList.findIndex(s=>{
        return s.uid === file.uid;
      });
      console.log(index);
      this.defaultList.splice(index,1);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    }
  },
  created() {
    this.imgList.map((item,i)=>{
      this.defaultList.push(
        {
          name: i,
          url:item,
          response:{
            data:item
          }
        }
      )
    })
  }
}
</script>

<style scoped>

</style>
