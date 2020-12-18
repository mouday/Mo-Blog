<template>
  <div class="app-container">
    <h2>CKEditor</h2>
    
    <!-- 引入编辑框 -->
    <CkEditor v-model="content" />

    <div style="margin-top:20px;">
      <mo-button
        type="primary"
        @click="handleSubmit"
      >保 存</mo-button>
    </div>

    <el-dialog
      title="保存的内容"
      :visible.sync="dialogVisible"
      width="50%"
      center
    >
      <div>{{content}}</div>

      <span slot="footer">
        <mo-button @click="dialogVisible = false">取 消</mo-button>
        <mo-button
          type="primary"
          @click="dialogVisible = false"
        >确 定</mo-button>
      </span>
    </el-dialog>
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