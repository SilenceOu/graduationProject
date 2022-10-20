<template>
  <div>
    <search></search>
    <z-table ref="table" @edit="newEdit" :column="column" :modal="'manager'"></z-table>
    <el-dialog title="编辑" :visible.sync="dialogFormVisible" destroy-on-close>
      <el-form :model="form">
        <el-form-item label="用户名" label-width="120px">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" label-width="120px">
          <el-input v-model="form.password" autocomplete="off" show-password></el-input>
        </el-form-item>
        <el-form-item label="联系方式" label-width="120px">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="头像" label-width="120px" v-if="show">
          <z-upload ref="upload" :imgUrl="form.avatar"></z-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "manager",
  data() {
    return {
      show: false,
      column: [
        { prop: 'username', label: '用户名' },
        { prop: 'phone', label: '联系方式' }
      ],
      dialogFormVisible: false,
      form: {},
    }
  },
  methods: {
    cancel() {
      this.dialogFormVisible = false
    },
    newEdit(e) {
      let param = { id: e.id }
      this.$post('/manager/get', param, res => {
        this.form = res.data
        this.show = true
        this.dialogFormVisible = true
      })
    },
    save() {
      this.form.avatar = this.$refs.upload.defaultUrl
      this.form.pageable = {}
      let param = this.form
 
      this.$post('/manager/update', param, res => {
        this.dialogFormVisible = false
        this.$refs.table.search()
      })
    }

  },
  watch: {
    dialogFormVisible(e) {
      this.show = e
    }
  }
}
</script>

<style scoped>

</style>
