<template>
  <div class="app-container">
    <h2>MavonEditor</h2>

    <MavonEditor v-model="content"/>

    <ShowContent :content="content" />
  </div>
</template>

<script>
import MavonEditor from '@/components/mavon-editor.vue';
import ShowContent from '@/views/demo/common/ShowContent.vue';

export default {
  name: '',

  props: [],

  components: {
    MavonEditor,
    ShowContent
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