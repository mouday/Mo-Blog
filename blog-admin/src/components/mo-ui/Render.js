/**
 * 参考
 * https://www.jianshu.com/p/d6b9a0bf1912
 */
export default {
  functional: true,

  props: {
    // 配置行
    render: {
      type: Function
    },
    // 数据行
    data: {
      type: Object,
      default: () => {
        return {};
      }
    }
  },

  render: (h, context) => {
    return context.props.render(h, context.props.data);
  }
};
