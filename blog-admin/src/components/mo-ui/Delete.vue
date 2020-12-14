<template>
  <el-popconfirm title="确定删除吗？"
    v-bind="$attrs"
    @confirm="handleDelete">
    <el-button slot="reference"
      type="text"
      class="el-icon-delete"></el-button>
  </el-popconfirm>
</template>

<script>
export default {
  name: "MoDelete",

  props: {
    id: {
      type: Number,
    },
    action: {
      type: String,
    },
  },

  methods: {
    async handleDelete() {
      const res = await this.$Http[this.action]({
        id: this.id,
      });

      if (res.code == 0) {
        this.$message.success("操作成功");
        this.$emit("success")
      } else {
        this.$message.error(res.message);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.el-icon-delete {
  color: #f56c6c;
}
</style>