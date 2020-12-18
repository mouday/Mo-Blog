<template>
  <i
    v-if="type == 'success'"
    class="el-icon-circle-check success"
  ></i>
  <i
    v-else-if="type == 'warning'"
    class="el-icon-warning-outline warning"
  ></i>
</template>

<script>
export default {
  name: 'MoStatus',

  props: {
    // primary, success, warning, danger, info
    type: {
      type: String,
    },
  },

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
.warning {
  color: #e6a23c;
}

.success {
  color: #67c23a;
}
</style>