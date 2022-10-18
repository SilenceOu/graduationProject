<template>
  <div>
    <search></search>
    <z-table ref="table" :column="column" :modal="'user'"></z-table>
  </div>
</template>

<script>
export default {
  name: "user",
  data() {
    return {
      column: [
        { prop: 'username', label: '用户名' },
        { prop: 'phone', label: '联系方式' },

      ],

    }
  },
  methods: {

  }
}
</script>

<style scoped>

</style>
