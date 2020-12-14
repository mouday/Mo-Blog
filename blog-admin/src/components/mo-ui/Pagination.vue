<template>
  <el-pagination class="mo-pagination"
    background
    hide-on-single-page
    layout="total, prev, pager, next"
    v-bind="$attrs"
    v-on="$listeners"></el-pagination>
</template>


<script>
export default {
  name: "MoPagination",
};
</script>

<style lang="scss">
.mo-pagination {
  text-align: center;
  margin-top: 20px;
}
</style>