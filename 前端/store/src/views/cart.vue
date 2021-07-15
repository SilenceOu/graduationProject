<template>
  <div>
    <h2 style="margin-left: 150px">我的购物车</h2>
    <z-table :column="column" :modal="'cart'" ref="table">

    </z-table>
  </div>
</template>

<script>
  export default {
    name: "cart",
    data() {
      return {
        column: [
          {prop: 'name', label: '商品名称'},
          {prop: 'money', label: '商品单价'},
          {prop: 'amount', label: '价格'}
        ],
      }
    },
    methods: {

    }
  }
</script>

<style scoped>

</style>
