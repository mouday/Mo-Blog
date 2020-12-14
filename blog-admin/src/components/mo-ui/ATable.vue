<template>
  <mo-table v-bind="$attrs"
    v-on="$listeners">

    <template v-for="column in columns">
      <el-table-column v-bind="column">
        <template slot-scope="scope">
          <!-- 支持具名插槽 -->
          <slot :name="column.prop"
            v-bind="scope">
            
            <!-- 支持render函数 -->
            <mo-render v-if="column.render"
              :data="scope"
              :render="column.render"></mo-render>
            <!-- 默认显示 -->
            <template v-else>{{scope.row[column.prop]}}</template>
          </slot>
        </template>
      </el-table-column>
    </template>

  </mo-table>
</template>

<script>
import MoTable from "./Table.vue";
import MoRender from "./Render.js";

export default {
  name: "MoATable",

  props: {
    // 列配置，可以传递 Table-column Attributes
    columns: {
      tyle: Array,
    },
  },

  components: { MoTable, MoRender },

  data() {
    return {};
  },

  computed: {},

  methods: {
    async getData() {},
  },

  created() {
    this.getData();
  },
};
</script>

<style lang="scss" scoped>
</style>