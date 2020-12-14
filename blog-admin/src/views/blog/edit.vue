<template>
  <div class="app-container">
    <el-form ref="form"
      :model="form"
      :rules="rules"
      label-width="80px">

      <el-form-item label="标题"
        prop="title">
        <el-input v-model="form.title" />
      </el-form-item>

      <el-form-item label="正文"
        prop="content">
        <el-input v-model="form.content"
          type="textarea"
          rows="10" />
      </el-form-item>

      <el-form-item label="作者"
        prop="user">

        <el-autocomplete v-model="form.user.name"
          :fetch-suggestions="querySearch"
          placeholder="请搜索关联用户"
          @select="handleSelect"
          @change="handleChange">

          <template slot-scope="{ item }">
            <span>{{ item.name }}</span>
            <span style="color:#97a8be;font-size:12px;margin-left:10px;">{{ item.dynastyId | dynastyFilter }}</span>
          </template>

          <i class="el-icon-circle-check"
            slot="suffix"
            v-show="form.user.id">
          </i>
        </el-autocomplete>

        <span style="margin-left:20px;">{{form.user.dynastyId | dynastyFilter}}</span>
      </el-form-item>

      <el-form-item label="发布时间"
        prop="publishTime">
        <el-date-picker v-model="form.publishTime"
          type="datetime"
          value-format="yyyy-MM-dd HH:mm:ss" />
      </el-form-item>

      <el-form-item label="显示">
        <el-switch v-model="form.isShow" />
      </el-form-item>

      <!-- <el-form-item label="Activity type">
        <el-checkbox-group v-model="form.type">
          <el-checkbox label="Online activities"
            name="type" />
          <el-checkbox label="Promotion activities"
            name="type" />
          <el-checkbox label="Offline activities"
            name="type" />
          <el-checkbox label="Simple brand exposure"
            name="type" />
        </el-checkbox-group>
      </el-form-item> -->
      <!-- <el-form-item label="Resources">
        <el-radio-group v-model="form.resource">
          <el-radio label="Sponsor" />
          <el-radio label="Venue" />
        </el-radio-group>
      </el-form-item> -->

      <el-form-item>
        <el-button type="primary"
          @click="onSubmit">保 存</el-button>
        <el-button @click="onCancel">取 消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {

    var validateUser = (rule, user, callback) => {
      console.log('validateUser', user);

      if (! user.id) {
        callback(new Error("请选择作者"));
      } else {
        callback();
      }
    };

    return {
      keywords: "",
      form: {
        id: "",
        title: "",
        content: "",
        publishTime: "",
        isShow: false,
        user: {
          id: "",
          name: "",
          dynastyId: ''
        },
      },

      rules: {
        title: [{ required: true, message: "请输入标题", trigger: "blur" }],
        content: [
          { required: true, message: "请输入正文内容", trigger: "blur" },
        ],
        publishTime: [
          { required: true, message: "请选择发布时间", trigger: "blur" },
        ],
        
        // 自定义数据校验规则
        user: [
          {
            required: true,
            validator: validateUser,
            trigger: "blur",
          },
        ],
      },
    };
  },

  created() {
    this.form.id = this.$route.query.id;
    if (this.form.id) {
      this.getData();
    }
  },

  methods: {
    async getData() {
      this.loading = true;

      const res = await this.$Http.getBlog({
        id: this.form.id,
      });

      this.form = res.data;
      this.loading = false;
    },

    onSubmit() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.submit();
        }
      });
    },

    async submit() {
      const res = await this.$Http.postBlog(this.form);

      if (res.code == 0) {
        this.$message.success("操作成功");

        this.$router.push({
          name: "blog-edit",
          query: { id: res.data.id },
        });
      } else {
        this.$message.error(res.message);
      }
    },

    onCancel() {
      this.$router.push({
        name: "blog-list",
      });
    },

    async querySearch(queryString, cb) {
      const res = await this.$Http.getUsers({
        keywords: queryString,
      });

      cb(res.data.list);
    },

    handleSelect(item) {
      console.log("handleSelect", item);
      this.form.user.id = item.id;
      this.form.user.name = item.name;
      this.form.user.dynastyId = item.dynastyId;
    },

    handleChange(item) {
      console.log("handleChange", item);
      this.form.user.id = null;
      this.form.user.dynastyId = null;
    },
  },
};
</script>

<style scoped>
.line {
  text-align: center;
}
</style>

