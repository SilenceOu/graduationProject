<template>
  <div style="padding-top: 30px;">
    <el-table border ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 80%;margin-left: 18%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center">
      </el-table-column>
      <el-table-column v-for="(item ,i) in tableColumn" :key="item" :label="item.label" :prop="item.prop" width="180"
        align="center">
      </el-table-column>
      <el-table-column prop="pictures" label="商品图片" v-if="modal=='commodity'" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.image" width="40" height="40" class="head_pic" />
        </template>
      </el-table-column>
      <el-table-column prop="pictures" label="头像图片" v-if="modal=='manager'||modal=='user'" align="center">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" width="40" height="40" class="head_pic" />
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button v-if="modal!=='user'" size="mini" @click="handleEdit(scope.$index, scope.row)">编辑
          </el-button>
          <el-button v-if="modal=='commodity'" size="mini" :type="scope.row.status== 1 ?'danger':'primary'"
            @click=" updateStatus(scope.$index, scope.row)">{{scope.row.status== 1 ?'下架':'上架'}}
          </el-button>
          <el-button v-if="modal!=='commodity'" size="mini" type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-left: 70%;width: 20px">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
        :page-sizes="[5, 10, 15, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import bus from '@/bus.js'

export default {
  name: "table",
  data() {
    return {
      searchData: {},
      total: 0,
      pageSize: 5,
      pageNum: 1,
      tableData: [{
        id: '',
        username: '',
        phone: '',
        img: ''
      }],
      multipleSelection: [],
      tableColumn: [{ prop: 'username', label: '用户名' },
      { prop: 'phone', label: '联系方式' }]
    }
  },
  props: {
    column: [],
    modal: ''
  },
  watch: {
    column: {
      immediate: true, /*绑定的时候就执行*/
      handler(columns) {
        console.log(columns)
        this.tableColumn = columns
      }
    },

  },
  methods: {
    updateStatus(i, e) {
      let param = { id: e.id, status: e.status == 1 ? 2 : 1 }
      this.$confirm(`确定${e.status == 1 ? '下架' : '上架'}该商品, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$post('/commodity/updateStatus', param, res => {
          this.search()
        })
        this.$message({
          type: 'success',
          message: `${e.status == 1 ? '下架' : '上架'}成功!`
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });

    },
    search() {
      this.searchData.pageable = {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      }
      let param = JSON.parse(JSON.stringify(this.searchData))

      if (this.modal == 'commodity') {
        this.$post('/commodity/search', param, res => {
          console.log(res.data)
          this.tableData = res.data.result.list
          this.total = res.data.result.total
        })
      }
      if (this.modal == 'manager') {
        this.$post('/manager/search', param, res => {
          console.log(res.data)
          this.tableData = res.data.result.list
          this.total = res.data.result.total
        })
      }
      if (this.modal == 'user') {
        this.$post('/user/search', param, res => {
          console.log(res.data)
          this.tableData = res.data.result.list
          this.total = res.data.result.total
        })
      }

    },

    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.search()
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.search()
    },
    handleEdit(index, row) {
      this.$emit('edit', row)
    },
    handleDelete(index, row) {
      let param = { id: row.id }
      this.$confirm(`确定删除, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        if (this.modal == 'manager') {
          this.$post('/manager/delete', param, res => {
            this.search()
            this.count()
          })
        }
        if (this.modal == 'user') {
          this.$post('/user/delete', param, res => {
            this.search()
            this.count()
          })
        }
        this.$message({
          type: 'success',
          message: `删除成功!`
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });

    }
  },
  mounted() {
    this.search()
    bus.$on('onSearch', () => {
      this.searchData = this.$store.state.searchValue
      this.pageNum = 1
      this.search()
      console.log(this.modal)

    })
  },
  beforeDestroy() {
    bus.$off('onSearch')
  }


}
</script>

<style scoped>

</style>
