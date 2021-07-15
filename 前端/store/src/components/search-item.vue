<template>
  <div>
    {{name}}
    <el-input style="width: 200px" v-if="type=='string'" v-model="value" @input="change"></el-input>
    <el-select v-if="type=='select'" v-model="value" placeholder="请选择">
      <el-option
        v-for="item in option"
        :key="item.value"
        :label="item.label"
        :value="item.value">
      </el-option>
    </el-select>
  </div>
</template>

<script>
  export default {
    name: "search-item",
    data() {
      return {
        value: ''
      }
    },
    props: {
      keys:'',
      name: '',
      type: '',
      option: []
    },
    methods:{
      change(){
        this.$emit('change',this.keys,this.value)
      }
    }
  }
</script>

<style scoped>

</style>
