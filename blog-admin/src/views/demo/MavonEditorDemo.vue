<template>
  <div class="app-container">
    <h2>MavonEditor</h2>

    <MavonEditor v-model="content"/>

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
import MavonEditor from '@/components/mavon-editor.vue';

export default {
  name: '',

  props: [],

  components: {
    MavonEditor,
  },

  data() {
    return {
      content: '',
      dialogVisible: false
    };
  },

  computed: {},

  methods: {
    async getData() {},

    handleSubmit() {
      console.log(this.content);
      this.dialogVisible = true;
    },

  },

  created() {
    this.getData();
  },
};
</script>

<style lang="scss" scoped>
</style>