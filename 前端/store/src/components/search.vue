<template>
  <div style="width: 1350px">
    <search-item class="search" v-for="(item,index) in list" :name="item.name" :type="item.type" :key="index"
                 :keys="item.key"
                 :option="item.option" @change="changeValue"></search-item>
    <el-button type="primary" icon="el-icon-search" style="margin-left: 30px" @click="search">搜索</el-button>
    <el-button type="primary" icon="el-icon-refresh-left">重置</el-button>

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
        list: [
          {type: 'string', name: '用户名', key: 'username'},
          {type: 'string', name: '联系方式', key: 'phone'},
          {
            type: 'select', name: 'hhh', option: [{
              value: '选项1',
              label: '黄金糕'
            }, {
              value: '选项2',
              label: '双皮奶'
            }, {
              value: '选项3',
              label: '蚵仔煎'
            }]
          },
        ],
        searchItem: {}
      }
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
        bus.$emit('onSearch')
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
