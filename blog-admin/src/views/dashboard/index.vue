<template>
  <div class="dashboard-container">
    <div class="dashboard-text">name: {{ name }}</div>

    <ChartBar />

    <ChartLine />
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import ChartBar from './chart-bar.vue';
import ChartLine from './chart-line.vue';

export default {
  name: 'Dashboard',

  components: { ChartBar, ChartLine },

  computed: {
    ...mapGetters(['name']),
  },
};
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
