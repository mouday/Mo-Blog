<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',

  created(){
    // 初始化一些数据
    this.$store.dispatch("dynasty/initData")
  }
  
}
</script>
