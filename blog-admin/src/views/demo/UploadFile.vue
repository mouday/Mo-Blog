<template>
  <div class="app-container">
    <h2>文件上传</h2>

    <el-upload
      action="action"
      :show-file-list="false"
      :http-request="uploadFile"
    >
      <mo-button>上 传</mo-button>
    </el-upload>

    <img style="width: 600px;"
      v-if="imageUrl"
      :src="imageUrl"
      alt=""
    >
  </div>
</template>

<script>
export default {
  data() {
    return {
      imageUrl: '',
    };
  },

  methods: {
    async uploadFile(params) {
      console.log(params);

      let form = new FormData();
      form.append('file', params.file);

      const res = await this.$Http.postUploadFile(form);
      console.log(res);

      this.imageUrl = res.data.url;
    },
  },
};
</script>

