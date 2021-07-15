<template>
  <div id="app">
    <layout v-if="$route.path!=='/index' && $route.path!=='/register' && $route.path!=='/'"></layout>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style lang="less">
#app{
  margin: 0;
  padding: 0;

}
body,html{
  height: 100%;
}

</style>
