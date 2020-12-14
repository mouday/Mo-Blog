<template>
  <router-link v-bind="$attrs"
    v-on="$listeners">
    <el-button type="text"
      class="el-icon-edit-outline"></el-button>
  </router-link>

</template>

<script>
export default {
  name: "MoEdit",
};
</script>

<style lang="scss" scoped>
</style>