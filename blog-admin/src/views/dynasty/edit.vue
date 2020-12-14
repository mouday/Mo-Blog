<template>
  <div class="app-container">
    <el-form ref="form"
      :model="form"
      :rules="rules"
      label-width="80px">

      <el-form-item label="朝代名称"
        prop="name"
        style="width:300px;">
        <el-input v-model="form.name" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary"
          @click="onSubmit">保 存</el-button>
        <el-button @click="onCancel">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      form: {
        id: "",
        name: "",
      },

      rules: {
        name: [{ required: true, message: "请输入名称", trigger: "blur" }],
      },
    };
  },

  computed: {
    ...mapGetters(["dynastyList"]),
  },

  created() {
    this.form.id = this.$route.query.id;

    if (this.form.id) {
      this.getData();
    }
  },

  methods: {
    async getData() {
      const res = await this.$Http.getDynasty({
        id: this.form.id,
      });
      this.form = res.data;
    },

    onSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.submit();
        }
      });
    },

    async submit() {
      const res = await this.$Http.postDynasty(this.form);

      if (res.code == 0) {
        this.$message.success("操作成功");

        this.$router.push({
          name: "dynasty-edit",
          query: { id: res.data.id },
        });
      } else {
        this.$message.error(res.message);
      }
    },

    onCancel() {
      this.$router.push({
        name: "dynasty-list",
      });
    },
  },
};
</script>

<style scoped>
.line {
  text-align: center;
}
</style>

