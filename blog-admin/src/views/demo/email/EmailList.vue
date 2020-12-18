<template>
  <div class="app-container">

    <mo-table
      v-loading="loading"
      :data="list"
    >
      <el-table-column
        header-align="center"
        label="收件人"
        prop="toUser"
      ></el-table-column>

      <el-table-column
        header-align="center"
        label="邮件主题"
        prop="subject"
      ></el-table-column>

      <el-table-column
        header-align="center"
        align="center"
        label="发送状态"
        prop="send"
        width="80"
      >
      <template slot-scope="scope">
        <mo-status :type=" scope.row.isSend ? 'success': 'warning'"></mo-status>
      </template>
      </el-table-column>

      <el-table-column
        header-align="center"
        label="邮件内容"
        prop="content"
      >
        <template slot-scope="scope">
          <div v-html="scope.row.content"></div>
        </template>
      </el-table-column>
    </mo-table>

    <mo-pagination
      :current-page.sync="page"
      :page-size="size"
      :total="total"
      @current-change="getData"
    >
    </mo-pagination>

  </div>
</template>

<script>
import { getList } from '@/api/table';

export default {
  data() {
    return {
      list: null,
      total: 0,
      page: 1,
      size: 10,
      loading: true,
    };
  },

  created() {
    this.getData();
  },

  methods: {
    async getData() {
      this.loading = true;
      const res = await this.$Http.getEmailList({
        page: this.page,
        size: this.size,
      });
      this.list = res.data.list;
      this.total = res.data.total;
      this.loading = false;
    },
  },
};
</script>
