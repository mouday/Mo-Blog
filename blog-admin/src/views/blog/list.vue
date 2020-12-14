<template>
  <div class="app-container">

    <Tools @change="handleDanastyChange"
      style="margin-bottom: 20px;" />

    <DataTable v-loading="loading"
      :data="list"
      @success="getData" />

    <mo-pagination :current-page.sync="page"
      :page-size="size"
      :total="total"
      @current-change="getData">
    </mo-pagination>
  </div>
</template>

<script>
import DataTable from "./DataTable.vue";
import Tools from "./Tools";

export default {
  name: "",

  props: [],

  components: { Tools, DataTable },

  data() {
    return {
      list: null,
      total: 0,
      page: 1,
      size: 10,
      loading: true,
      dynastyId: null,
      dynasty: {},
    };
  },

  computed: {},

  methods: {
    async getData() {
      this.loading = true;
      const res = await this.$Http.getBlogs({
        page: this.page,
        size: this.size,
        dynastyId: this.dynastyId,
      });
      this.list = res.data.list;
      this.total = res.data.total;
      this.loading = false;
    },

    handleDanastyChange(dynastyId) {
      this.page = 1;
      this.dynastyId = dynastyId;
      this.getData();
    },
  },

  created() {
    this.getData();
  },
};
</script>

<style lang="scss" scoped>
</style>