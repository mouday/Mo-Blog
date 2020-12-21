<template>
  <div class="app-container">
    <h2>CKEditor</h2>
    
    <!-- 引入编辑框 -->
    <CkEditor v-model="content" />

    <ShowContent :content="content" />
  </div>
</template>

<script>
import CkEditor from '@/components/ck-editor/index.vue';
import ShowContent from '@/views/demo/common/ShowContent.vue';

export default {
  name: '',

  props: [],

  components: {
    CkEditor,
    ShowContent
  },

  data() {
    return {
      content: '<div><p>在这里书写文字</p></div>',
      dialogVisible: false,
    };
  },

  computed: {},

  methods: {
    handleSubmit() {
      console.log(this.content);
      this.dialogVisible = true;
    },
  },

  created() {},
};
</script>

<style lang="scss" scoped>
</style>