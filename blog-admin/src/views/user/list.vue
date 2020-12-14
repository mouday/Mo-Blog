<template>
  <div class="app-container">

    <mo-a-table v-loading="loading"
      :data="list"
      :columns="columns">

      <!-- 复杂显示，可以覆盖默认 -->
      <template slot="dynasty"
        slot-scope="scope">
        <span>{{ scope.row.dynastyId | dynastyFilter }}</span>
      </template>

      <template slot="lastLoginTime"
        slot-scope="scope">
        <i class="el-icon-time" />
        <span>{{ scope.row.lastLoginTime }}</span>
      </template>

      <template slot="operate"
        slot-scope="scope">

        <mo-edit :to="{name: 'user-edit', query: {id: scope.row.id}}" />

        <mo-delete style="margin-left:10px;"
          @confirm="handleDelete(scope.row.id)" />

      </template>

    </mo-a-table>

    <mo-pagination :current-page.sync="page"
      :page-size="size"
      :total="total"
      @current-change="getData">
    </mo-pagination>

  </div>
</template>

<script>
import { getList } from "@/api/table";

export default {
  data() {
    return {
      list: null,
      total: 0,
      page: 1,
      size: 10,
      loading: true,

      columns: [
        {
          label: "ID",
          prop: "id",
          width: "60",
          align: "center",
        },
        {
          prop: "name",
          label: "姓名",
          "header-align": "center",
        },
        {
          prop: "dynasty",
          label: "朝代",
          width: 110,
          "header-align": "center",
        },
        {
          prop: "count",
          label: "文章数量",
          width: 110,
          "header-align": "center",
        },
        {
          prop: "createTime",
          label: "创建时间",
          width: "160",
          "header-align": "center",
        },
        {
          prop: "updateTime",
          label: "修改时间",
          width: "160",
          "header-align": "center",
        },
        {
          prop: "lastLoginTime",
          label: "最后登录时间",
          width: "200",
          "header-align": "center",
        },
        {
          prop: "operate",
          label: "操作",
          width: "80",
          "header-align": "center",
          align: "center",
        },
      ],
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
        size: this.size,
      });
      this.list = res.data.list;
      this.total = res.data.total;
      this.loading = false;
    },

    async handleDelete(id) {
      const res = await this.$Http.deleteUser({
        id: id,
      });

      if (res.code == 0) {
        this.$message.success("操作成功");
        this.getData();
      } else {
        this.$message.error(res.message);
      }
    },
  },
};
</script>
