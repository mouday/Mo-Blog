<template>
  <div class="app-container">
    <h2>颜色选择器</h2>

    <div class="item">
      el-color-picker: <el-color-picker size="small" v-model="color"></el-color-picker>
    </div>

    <div class="item">
      colorPicker: <colorPicker v-model="color" />
    </div>

    <div class="item">
      {{color}}
    </div>

  </div>
</template>

<script>
/**
 * 文档
 * http://vue-color-picker.rxshc.com/
 */

import colorPicker from 'vcolorpicker';

export default {
  name: '',

  props: [],

  components: {
    colorPicker: colorPicker.colorPicker,
  },

  data() {
    return {
      color: '#ff0000',
    };
  },

  computed: {},

  methods: {},

  created() {},
};
</script>

<style lang="scss" scoped>
.item{
  margin-bottom: 20px;
}
</style>