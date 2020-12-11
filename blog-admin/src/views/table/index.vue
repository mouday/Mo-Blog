<template>
  <div class="app-container">
    <el-table v-loading="loading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column align="center"
        label="ID"
        width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="用户名">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>

      <el-table-column label="朝代"
        width="110"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.dynasty }}</span>
        </template>
      </el-table-column>

      <el-table-column label="文章数量"
        width="110"
        align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.count }}</span>
        </template>
      </el-table-column>

      <el-table-column label="创建时间"
        width="160"
        align="center">
        <template slot-scope="scope">
          {{ scope.row.createTime }}
        </template>
      </el-table-column>

      <el-table-column label="修改时间"
        width="160"
        align="center">
        <template slot-scope="scope">
          {{ scope.row.updateTime }}
        </template>
      </el-table-column>

      <!-- <el-table-column class-name="status-col"
        label="Status"
        width="110"
        align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column> -->

      <el-table-column prop="created_at"
        label="最后登录时间"
        width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.lastLoginTime }}</span>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination background
      layout="prev, pager, next"
      :current-page.sync="page"
      :page-size="size"
      :total="total"
      @current-change="getData"
      >
    </el-pagination>

  </div>
</template>

<script>
import { getList } from "@/api/table";

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: "success",
        draft: "gray",
        deleted: "danger",
      };
      return statusMap[status];
    },
  },
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
      const res = await this.$Http.getUsers({
        page: this.page,
        size: this.size
      });
      this.list = res.data.list;
      this.total = res.data.total;
      this.loading = false;
    },
  },
};
</script>
