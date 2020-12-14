import Pagination from "./Pagination.vue";
import Table from "./Table.vue";
import ATable from "./ATable.vue";
import Delete from "./Delete.vue";
import Edit from "./Edit.vue";
import Switch from "./Switch.vue";

const components = [Pagination, Table, ATable, Delete, Edit, Switch];

const install = function(Vue, options) {
  components.forEach(component => {
    Vue.component(component.name, component);
  });
};

export default {
  install,
  ...components
};
