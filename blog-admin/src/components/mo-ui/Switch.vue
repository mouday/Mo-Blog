<template>
  <el-switch v-bind="$attrs"
    v-on="$listeners"
    @change="handleChange" />
</template>

<script>
export default {
  name: "MoSwitch",

  props: {
    id: {
      type: Number,
    },
    action: {
      type: String,
    },
  },

  components: {},

  data() {
    return {};
  },

  computed: {},

  methods: {
    async handleChange(event) {
      const res = await this.$Http[this.action]({
        id: this.id,
        isShow: event,
      });

      if (res.code == 0) {
        this.$message.success("操作成功");
      } else {
        this.$message.error(res.message);
      }
    },
  },

  created() {},
};
</script>

<style lang="scss" scoped>
</style>