<template>

  <vue-editor
    class="vue-editor"
    v-bind="$attrs"
    v-on="$listeners"
    useCustomImageHandler
    @image-added="handleImageAdded"
  >
  </vue-editor>

</template>

<script>
/**
 * 文档
 * https://www.vue2editor.com/guide.html
 */
import { VueEditor } from 'vue2-editor';

export default {
  components: {
    VueEditor,
  },

  data() {
    return {};
  },

  methods: {
    /**
     * 自定义上传图片
     */
    async handleImageAdded(file, Editor, cursorLocation, resetUploader) {
      let form = new FormData();
      form.append('file', file);

      const res = await this.$Http.postUploadFile(form);

      Editor.insertEmbed(cursorLocation, 'image', res.data.url);
      resetUploader();
    },
  },
};
</script>

<style lang="scss">
.vue-editor {
  // min-height: 400px;
  background-color: #ffffff;

  .ql-editor {
    min-height: 400px;
  }
  p {
    line-height: 1.5;
  }
}
</style>