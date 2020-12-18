<template>
  <el-button v-bind="$attrs"
    v-on="$listeners"
    size="small">
    <slot></slot>
  </el-button>
</template>

<script>
export default {
  name: "MoButton",

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

<style lang="scss" scoped>
</style>