<template>
  <div style="width: 1000px;margin-left: 18%">
    <search-item class="search" v-for="(item,index) in list" :name="item.name" :type="item.type" :key="index"
                 :keys="item.key"
                 :option="item.option" @change="changeValue" :ref="'search'+index"></search-item>
    <el-button type="primary" icon="el-icon-search" style="margin-left: 30px" @click="search">搜索</el-button>
    <el-button type="primary" icon="el-icon-refresh-left" @click="clear">重置</el-button>

  </div>
</template>

<script>
  import SearchItem from "./search-item"
  import bus from '@/bus.js'

  export default {
    name: "search",
    components: {SearchItem},
    data() {
      return {

        searchItem: {}
      }
    },
    props: {
      list: {
        default: [
          {type: 'string', name: '用户名', key: 'username'},
          {type: 'string', name: '联系方式', key: 'phone'},
        ],
        type:Array
      },
      modal:''
    },
    methods: {
      changeValue(key, value) {
        if (value) {
          this.searchItem[key] = value
        } else {
          delete this.searchItem[key]
        }
        console.log(this.searchItem)
      },
      search() {
        this.$store.dispatch('search', this.searchItem)
        if(this.modal=='order'){
          bus.$emit('onSearchOrder')
        } else {
          bus.$emit('onSearch')

        }

      },
      clear() {
        bus.$emit('clear')
        this.searchItem = {}
        this.search()
      }
    }

  }
</script>

<style scoped lang="less">
  div {
    margin-top: 20px;

    .search {
      margin-left: 40px;
      display: inline;
      line-height: 50px;
    }
  }

</style>
