<template>
  <div class="app-container">
    <el-form ref="form"
      :model="form"
      :rules="rules"
      label-width="50px">

      <el-form-item label="姓名" prop="name"
        style="width:300px;">
        <el-input v-model="form.name" />
      </el-form-item>

      <el-form-item label="朝代">

        <el-select v-model="form.dynastyId"
          placeholder="请选择">
          <el-option v-for="item in dynastyList"
            :key="item.id"
            :label="item.name"
            :value="item.id">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <mo-button type="primary"
          @click="onSubmit">保 存</mo-button>
        <mo-button @click="onCancel">取 消</mo-button>
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
        dynastyId: "",
      },

      rules: {
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }],
      },
    };
  },

  computed: {
    ...mapGetters(["dynastyList"]),
  },

  created() {
    this.form.id = this.$route.query.id;
    if(this.form.id){
      this.getData();
    }
    
  },

  methods: {
    async getData() {
      const res = await this.$Http.getUser({
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
      const res = await this.$Http.postUser(this.form);

      if (res.code == 0) {
        this.$message.success("操作成功");

        this.form.id = res.data.id;
        
        this.$router.push({
          name: "user-edit",
          query: { id: res.data.id },
        });
      } else {
        this.$message.error(res.message);
      }
    },

    onCancel() {
      this.$router.push({
        name: "user-list",
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

