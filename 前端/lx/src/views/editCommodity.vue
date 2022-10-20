<template>
  <div style="display: inline-block;margin-left: 18%">
    <el-form label-position="right" label-width="80px" :model="formLabelAlign">
      <el-form-item label="商品名称">
        <el-input v-model="formLabelAlign.name" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="商品单价">
        <el-input v-model="formLabelAlign.money" style="width: 200px"></el-input>
      </el-form-item>
      <el-form-item label="商品状态">
        <el-radio v-model="formLabelAlign.status" label="上架">上架</el-radio>
        <el-radio v-model="formLabelAlign.status" label="下架">下架</el-radio>
      </el-form-item>
      <el-form-item label="商品数量">
        <el-input-number v-model="formLabelAlign.number" @change="handleChange" :min="1" :max="9999"
                         label="描述文字"></el-input-number>
      </el-form-item>
      <el-form-item label="商品图片" v-if="show">
        <z-upload ref="upload" :imgUrl="formLabelAlign.image"></z-upload>
      </el-form-item>
      <el-form-item label="图文详情">
        <editor style="height: 400px" :defaultContent="formLabelAlign.content"
                @on-change-content="onChangeEditor"></editor>
      </el-form-item>
    </el-form>
    <el-button @click="saveGoods">保存</el-button>
  </div>
</template>

<script>
  export default {
    name: "editCommodity",
    data() {
      return {
        show: false,
        formLabelAlign: {
          name: '',
          status: '下架',
          image: [],
          number: 1,
          content: ''
        }
      }
    },
    methods: {
      onChangeEditor(e) {
        this.formLabelAlign.content = e.html;
      },
      handleChange(val) {
        console.log(val)
        this.formLabelAlign.number = val
      },
      saveGoods() {
        
        this.formLabelAlign.image = this.$refs.upload.defaultUrl
        this.formLabelAlign.pageable = {}
        let param = this.formLabelAlign
        if (this.$route.query.id) {
          param.id=this.$route.query.id
          this.$post('/commodity/update', param, (res) => {
            this.$message({
              type: 'success',
              message: `保存成功!`
            });
            this.$router.push({path:'/commodity'})
            }
          )
        } else {
          this.$post('/commodity/create', param, (res) => {
            this.$message({
              type: 'success',
              message: `保存成功!`
            });
            this.$router.push({path:'/commodity'})

          })
        }
      }

    },
    created() {
      if (this.$route.query.id) {
        let param = {id: this.$route.query.id}
        this.$post('/commodity/get', param, res => {
          this.formLabelAlign = res.data
          this.formLabelAlign.image=[res.data.image]
          this.show = true
        })
      } else {
        this.show = true
      }

    }
  }
</script>

<style scoped lang="less">

</style>
