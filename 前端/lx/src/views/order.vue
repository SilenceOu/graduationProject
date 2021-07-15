<template>
  <div>
    <search :list="list" :modal="'order'"
    ></search>
    <z-order style="margin-left: 20px"></z-order>
  </div>
</template>

<script>
  export default {
    name: "order",
    data(){
      return{
        list: [
          {type: 'string', name: '收货人', key: 'name'},
          {type: 'string', name: '联系电话', key: 'phone'},

        ],
      }
    }
  }
</script>

<style scoped>

</style>
