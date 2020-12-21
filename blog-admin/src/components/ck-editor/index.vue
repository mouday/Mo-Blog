<template>
  <div id="ck-editer">
    <ckeditor
      :editor="editor"
      v-bind="$attrs"
      v-on="$listeners"
      @ready="onReady"
      :config="editorConfig"
    >
    </ckeditor>
  </div>
</template>

<script>
/**
 * 富文本编辑框
 * 安装依赖， 注意vue2项目的版本号
 * cnpm i @ckeditor/ckeditor5-vue@1.0.1 \
 * @ckeditor/ckeditor5-build-decoupled-document@21.0.0 -S
 *
 * 文档
 * https://ckeditor.com/docs/ckeditor5/latest/builds/guides/integration/frameworks/vuejs-v2.html
 */
import DecoupledEditor from '@ckeditor/ckeditor5-build-decoupled-document';
// import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import CKEditor from '@ckeditor/ckeditor5-vue';
import '@ckeditor/ckeditor5-build-decoupled-document/build/translations/zh-cn';
import MyUploadAdapterPlugin from './UploadAdapter.js';

export default {
  name: 'MoCkEditor',
  components: {
    ckeditor: CKEditor.component,
  },

  data() {
    return {
      editor: DecoupledEditor,

      editorConfig: {
        // The configuration of the editor.
        language: 'zh-cn',
        // toolbar: [ 'heading', '|', 'bold', 'italic', 'link', 'bulletedList', 'numberedList', 'blockQuote' ],
        extraPlugins: [MyUploadAdapterPlugin],
      },
    };
  },

  methods: {
    // DecoupledEditor 需要手动处理显示工具栏
    onReady(editor) {
      editor.ui
        .getEditableElement()
        .parentElement.insertBefore(
          editor.ui.view.toolbar.element,
          editor.ui.getEditableElement()
        );
    },
  },
};
</script>

<style lang="scss">
/* 全局修改生效 */
#ck-editer {
  .ck-content {
    line-height: 1.5;
    font-size: 14px;
    min-height: 300px;
    border: 1px solid #ccc !important;
    background-color: white;
  }
}
</style>
