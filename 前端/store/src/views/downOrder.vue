<template>
    <div>
      <h2 style="margin-left: 150px">所购商品信息</h2>
      <z-table :column="column" :modal="'downOrder'" ref="downOrderTable">

      </z-table>
      <h2 style="margin-left: 150px;margin-top: 50px">请选择收货信息</h2>
      <z-table :column="addressColumn" :modal="'downOrderAddress'" @edit="newEdit" ref="downOrderAddressTable">
      </z-table>
      <el-button style="margin-left: 1000px;margin-top: 50px" @click="saveOrder">确认下单</el-button>
    </div>
</template>

<script>
import {postJson} from '@/request/axios.js';
  export default {
    name: "downOrder",
    data(){
      return{
        addressColumn: [
          {prop: 'name', label: '收货人'},
          {prop: 'phone', label: '联系电话'},
          {prop: 'city', label: '省市区'},
          {prop: 'detailAddress', label: '详细地址'},
        ],
        column:[
          {prop:'name',label:'商品名称'},
          {prop:'number',label:'商品数量'},
          {prop:'money',label:'价格'}
        ],

      }
    },
    methods:{
      saveOrder(){
        let comm=this.$route.query.data.map((e,i)=>{
          return {name:e.name,number:e.number}
        })
        let param={}
        param.nums=this.$route.query.data.map((e,i)=>{
          return e.number
        })
        param.names=this.$route.query.data.map((e,i)=>{
          return e.name
        })
        param.cartIdList=[]
        this.$refs.downOrderTable.tableData.forEach((e,i)=>{
          param.cartIdList.push(e.id)
        })
        param.addressId=this.$refs.downOrderAddressTable.addressRow.id
        param.allMoney=this.$refs.downOrderTable.allMoney
        param.status='等待发货'
        param.userId = JSON.parse(localStorage.getItem('user')).id
        console.log(param)
        postJson('/commodity/changeNumber', JSON.stringify(comm), res => {

        })

        this.$post('/order/create', param, res => {
          this.$message({
            message: '下单成功',
            type: 'success'
          });
          this.$router.push({path:'/order'})

        })
      }
    }
  }
</script>

<style scoped>

</style>
