<template>
  <el-table class="mo-table"
    border
    fit
    stripe
    header-cell-class-name="mo-table-header-cell"
    highlight-current-row
    v-bind="$attrs"
    v-on="$listeners">
    <slot></slot>
  </el-table>
</template>

<script>
export default {
  name: "MoTable",

  props: [],

  components: {},

  data() {
    return {};
  },

  computed: {},

  methods: {
    async getData() {},
  },

  created() {
    this.getData();
  },
};
</script>

<style lang="scss">
.mo-table {
  width: 100%;

  .mo-table-header-cell {
    background: #f5f7fa;
  }
}
</style>