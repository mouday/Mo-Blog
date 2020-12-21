<template>
  <div
    class="bar-main"
    id="box"
    ref="dom"
  ></div>
</template>

<script>
import echarts from 'echarts';
import './theme/macarons.js';
import { on, off } from './util.js';

console.log(echarts);

// echarts.registerTheme('tdTheme', tdTheme);

export default {
  mounted() {
    this.initChart();
  },
  methods: {
    resize() {
      this.dom.resize();
    },
    initChart() {
      this.$nextTick(() => {
        let option = {
          xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
          },
          yAxis: {
            type: 'value',
          },
          series: [
            {
              data: [820, 932, 901, 934, 1290, 1330, 1320],
              type: 'line',
            },
          ],
        };

        this.dom = echarts.init(this.$refs.dom, 'macarons');
        this.dom.setOption(option);
        on(window, 'resize', this.resize);
      });
    },
  },
};
</script>

<style>
.bar-main {
  width: 100%;
  height: 360px;
  padding: 28px;
  background: #fff;
}
</style>
