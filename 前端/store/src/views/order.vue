<template>
    <div>
<z-order></z-order>


    </div>
</template>

<script>
    export default {
        name: "order",
      data(){
          return{
            tableData:[],
            column:[
              {prop:'name',label:'商品名称'},
              {prop:'number',label:'商品数量'},
              {prop:'money',label:'价格'}
            ],
          }
      },
      created() {

      }
    }
</script>

<style scoped lang="less">

</style>
