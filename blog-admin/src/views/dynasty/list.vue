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

        <mo-edit :to="{name: 'dynasty-edit', query: {id: scope.row.id}}" />

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
          label: "名称",
          "header-align": "center",
        },
  
        {
          prop: "userCount",
          label: "作者数量",
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
      const res = await this.$Http.getDynastyWithUserCount();
      this.list = res.data;
      this.loading = false;
    },

    async handleDelete(id) {
      const res = await this.$Http.deleteDynasty({
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
