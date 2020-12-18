<template>
  <mavonEditor
    ref="md"
    v-bind="$attrs"
    v-on="$listeners"
    @imgAdd="handleImgAdd"
    style="min-height: 600px"
  />
</template>

<script>
/**
 * https://www.npmjs.com/package/mavon-editor
 * 
 * npm install mavon-editor --save
 */
import { mavonEditor } from 'mavon-editor';
import 'mavon-editor/dist/css/index.css';

/**
 * 
 * 事件
 * save
 * change
 */
export default {
  name: '',

  props: [],

  components: {
    mavonEditor,
  },

  data() {
    return {
      value: '',
    };
  },

  computed: {},

  methods: {
    // 将图片上传到服务器，返回地址替换到md中
    async handleImgAdd(pos, file) {
      let form = new FormData();
      form.append('file', file);

      const res = await this.$Http.postUploadFile(form);
      // console.log(res);
      this.$refs.md.$img2Url(pos, res.data.url);
    },
  },

  created() {},
};
</script>

<style lang="scss" scoped>
</style>