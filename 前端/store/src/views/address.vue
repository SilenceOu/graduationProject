<template>
  <div>
    <h2 style="margin-left: 150px">收货信息</h2>
    <el-button @click="newEdit(0)" style="margin-left:150px;margin-top: 40px">添加地址</el-button>
    <z-table :column="column" :modal="'address'" @edit="newEdit" ref="table">
    </z-table>
    <el-dialog title="编辑" :visible.sync="dialogFormVisible" destroy-on-close>
      <el-form :model="form">
        <el-form-item label="收货人" label-width="120px">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" label-width="120px">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="省市区" label-width="120px">
          <v-distpicker @selected='selected' :province="temp.province" :city="temp.city" :area="temp.area">
          </v-distpicker>
        </el-form-item>
        <el-form-item label="详细地址" label-width="120px">
          <el-input v-model="form.detailAddress" autocomplete="off"></el-input>
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
  import VDistpicker from 'v-distpicker'

  export default {
    name: "address",
    components: {VDistpicker},
    data() {
      return {
        dialogFormVisible: false,
        column: [
          {prop: 'name', label: '收货人'},
          {prop: 'phone', label: '联系电话'},
          {prop: 'city', label: '省市区'},
          {prop: 'detailAddress', label: '详细地址'},
        ],
        city: '',
        form: {
          city: '山西省-阳泉市-城区'
        },
        temp: {},
        visible: false,
        index:''
      }
    },
    methods: {
      cancel() {
        this.dialogFormVisible = false
      },
      selected(data) {
        this.form.city = data.province.value + '-' + data.city.value + '-' + data.area.value
      },
      newEdit(e,i) {
        if (e) {
          this.index=i
          this.form = JSON.parse(JSON.stringify(e))
          let arr = this.form.city.split('-')
          this.temp.province = arr[0]
          this.temp.city = arr[1]
          this.temp.area = arr[2]
          this.visible = true
        } else {
          this.temp={}
          this.form={}
          this.visible = false
        }
        this.dialogFormVisible = true
      },
      save() {
        let param = this.form
        param.userId = JSON.parse(localStorage.getItem('user')).id
        if (!this.visible) {
          this.$post('/user/saveAddress', param, res => {
            this.$refs.table.search()
            this.dialogFormVisible = false

          })
        }else {
          let param=this.form
          this.$post('/user/updateAddress', param, res => {
            this.$refs.table.search()
            this.dialogFormVisible = false

          })
        }

      }
    }
  }
</script>

<style scoped>

</style>
