<template>
  <div class="app-container">
    <h2>发送邮件</h2>

    <el-form
      label-width="80px"
      ref="form"
      :rules="rules"
      :model="form"
    >
      <el-form-item
        label="收件人"
        prop="toUser"
      >
        <el-input v-model="form.toUser"></el-input>
      </el-form-item>

      <el-form-item
        label="标 题"
        prop="subject"
      >
        <el-input v-model="form.subject"></el-input>
      </el-form-item>

      <el-form-item
        label="内 容"
        prop="content"
      >
        <!-- 引入编辑框 -->
        <CkEditor v-model="form.content" />
      </el-form-item>

      <el-form-item>
        <mo-button
          type="primary"
          @click="onSubmit"
        >发 送</mo-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import CkEditor from '@/components/ck-editor/index.vue';

export default {
  name: '',

  props: [],

  components: {
    CkEditor,
  },

  data() {
    return {
      form: {
        toUser: '1940607002@qq.com',
        subject: '邮件主题',
        content: '邮件内容',
      },

      rules: {
        toUser: [{ required: true, message: '请输入收件人', trigger: 'blur' }],
        subject: [{ required: true, message: '请输入标题', trigger: 'blur' }],
        content: [
          { required: true, message: '请输入正文内容', trigger: 'blur' },
        ],
      },
    };
  },

  computed: {},

  methods: {
    // 数据校验
    onSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.comfirm();
        }
      });
    },

    // 发送确认
    comfirm() {
      this.$confirm('确认发送邮件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      })
        .then(() => {
          this.submit();
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消发送',
          });
        });
    },

    // 提交发送
    async submit() {
      const res = await this.$Http.postSendMail(this.form);

      if (res.code == 0) {
        this.$message.success('发送成功');
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