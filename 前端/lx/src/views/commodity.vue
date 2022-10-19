<template>
  <div>
    <el-button @click="$router.push({path:'/editCommodity'})" style="margin-left: 18%">创建商品</el-button>
    <search :list="list"></search>
    <z-table ref="table" @edit="newEdit" :column="column" :modal="'commodity'"></z-table>
  </div>
</template>

<script>
export default {
  name: "commodity",
  data() {
    return {
      list: [
        { type: 'string', name: '商品名称', key: 'name' },
        {
          type: 'select', name: '商品状态', key: 'status',
          option: [{ label: '上架', value: 1 }, { label: '下架', value: 2 }]
        },

      ],
      column: [
        { prop: 'name', label: '商品名称' },
        { prop: 'status', label: '状态' },
        { prop: 'number', label: '数量' },
        { prop: 'money', label: '单价' },
      ],

    }
  },
  methods: {
    newEdit(e) {
      this.$router.push({ path: 'editCommodity', query: { id: e.id } })

    },


  }
}
</script>

<style scoped>

</style>
