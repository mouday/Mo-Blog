<template>
  <div class="app-container">

    <h2>VueEditor</h2>

    <vue-editor v-model="content"></vue-editor>

    <ShowContent :content="content" />

  </div>

</template>

<script>
import VueEditor from '@/components/vue-editor.vue';
import ShowContent from '@/views/demo/common/ShowContent.vue';

export default {
  components: {
    VueEditor,
    ShowContent,
  },

  data() {
    return {
      content: '',
      dialogVisible: false,
    };
  },

  methods: {},
};
</script>

<style lang="scss">
</style>