<template>

  <mo-table v-bind="$attrs"
    v-on="$listeners">

    <el-table-column align="center"
      label="ID"
      width="60">
      <template slot-scope="scope">
        {{ scope.row.id }}
      </template>
    </el-table-column>

    <el-table-column label="标题">
      <template slot-scope="scope">
        {{ scope.row.title }}
      </template>
    </el-table-column>

    <el-table-column label="作者"
      width="110"
      align="center">
      <template slot-scope="scope">
        <span>{{ scope.row.user.name }}</span>
      </template>
    </el-table-column>

    <el-table-column label="朝代"
      width="110"
      align="center">
      <template slot-scope="scope">
        <span>{{ scope.row.user.dynastyId | dynastyFilter}}</span>
      </template>
    </el-table-column>

    <el-table-column label="发布时间"
      width="160"
      align="center">
      <template slot-scope="scope">
        {{ scope.row.publishTime }}
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

    <el-table-column label="显示"
      width="65"
      align="center">
      <template slot-scope="scope">
        <mo-switch v-model="scope.row.isShow"
          action="postBlogStatus"
          :id="scope.row.id" />
      </template>
    </el-table-column>

    <el-table-column label="操作"
      width="80"
      align="center">
      <template slot-scope="scope">

        <mo-edit :to="{name: 'blog-edit', query: {id: scope.row.id}}" />

        <mo-delete style="margin-left:10px;"
          action="deleteBlog"
          :id="scope.row.id"
          v-on="$listeners" />

      </template>
    </el-table-column>

  </mo-table>

</template>

<script>
export default {
  name: "",
};
</script>

<style lang="scss" scoped>
</style>