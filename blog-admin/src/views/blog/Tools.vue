<template>
  <div class="">
    <!-- 筛选功能 -->
    <el-radio-group v-model="value"
      v-on="$listeners"
      size="small">
      <el-radio-button label="">全部</el-radio-button>
      <template v-for="item in dynastyList">
        <el-radio-button :label="item.id">{{item.name}}</el-radio-button>
      </template>
    </el-radio-group>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "",

  props: [],

  components: {},

  data() {
    return {
      value: "",
    };
  },

  computed: {
    ...mapGetters(["dynastyList"]),
  },

  methods: {},

  created() {},
};
</script>

<style lang="scss" scoped>
</style>