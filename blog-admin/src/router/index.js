import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

/* Layout */
import Layout from "@/layout";

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true
  },

  {
    path: "/404",
    component: () => import("@/views/404"),
    hidden: true
  },

  {
    path: "/",
    component: Layout,
    redirect: "/dashboard",
    children: [
      {
        path: "dashboard",
        name: "Dashboard",
        component: () => import("@/views/dashboard/index"),
        meta: { title: "Dashboard", icon: "dashboard" }
      }
    ]
  },

  {
    path: "/blog",
    component: Layout,
    redirect: "/blog/list",
    name: "blog",
    meta: { title: "文章", icon: "table" },
    children: [
      {
        path: "list",
        name: "blog-list",
        component: () => import("@/views/blog/list.vue"),
        meta: { title: "文章", icon: "table" }
      },
      {
        path: "edit",
        name: "blog-edit",
        component: () => import("@/views/blog/edit.vue"),
        meta: { title: "文章编辑", icon: "form" }
      }
    ]
  },

  {
    path: "/user",
    component: Layout,
    redirect: "/user/list",
    name: "user",
    meta: { title: "用户", icon: "user" },
    children: [
      {
        path: "list",
        name: "user-list",
        component: () => import("@/views/user/list"),
        meta: { title: "用户", icon: "user" }
      },
      {
        path: "edit",
        name: "user-edit",
        component: () => import("@/views/user/edit.vue"),
        meta: { title: "添加用户", icon: "form" }
      }
    ]
  },

  {
    path: "/dynasty",
    component: Layout,
    redirect: "/dynasty/list",
    name: "dynasty",
    meta: { title: "朝代", icon: "nested" },
    children: [
      {
        path: "list",
        name: "dynasty-list",
        component: () => import("@/views/dynasty/list"),
        meta: { title: "朝代", icon: "nested" }
      },
      {
        path: "edit",
        name: "dynasty-edit",
        component: () => import("@/views/dynasty/edit.vue"),
        meta: { title: "添加朝代", icon: "form" }
      }
    ]
  },

  {
    path: "/email",
    component: Layout,
    redirect: "/email/list",
    name: "email",
    meta: { title: "邮件", icon: "mail" },
    children: [
      {
        path: "edit",
        name: "email-edit",
        component: () => import("@/views/demo/email-page/EmailEdit.vue"),
        meta: { title: "发送邮件", icon: "mail-send" }
      },
      {
        path: "list",
        name: "email-list",
        component: () => import("@/views/demo/email-page/EmailList.vue"),
        meta: { title: "邮件列表", icon: "mail" }
      }
    ]
  },

  {
    path: "/editor",
    component: Layout,
    redirect: "/editor/ck-editor",
    name: "editor",
    meta: { title: "编辑器", icon: "nested" },
    children: [
      {
        path: "ck-editor",
        name: "editor-ck-editor",
        component: () => import("@/views/demo/ck-editor-page/index.vue"),
        meta: { title: "CKEditor", icon: "example" }
      },

      {
        path: "mavon-editor",
        name: "editor-mavon-editor",
        component: () => import("@/views/demo/mavon-editor-page/index.vue"),
        meta: { title: "MavonEditor", icon: "example" }
      },

      {
        path: "vue2-editor",
        name: "editor-vue2-editor",
        component: () => import("@/views/demo/vue2-editor-page/index.vue"),
        meta: { title: "Vue2Editor", icon: "example" }
      },
    ]
  },

  {
    path: "/demo",
    component: Layout,
    redirect: "/demo/ck-editor",
    name: "demo",
    meta: { title: "Demo", icon: "nested" },
    children: [
      {
        path: "vcolorpicker",
        name: "demo-vcolorpicker",
        component: () => import("@/views/demo/vcolorpicker-page/index.vue"),
        meta: { title: "Vcolorpicker", icon: "example" }
      },

      {
        path: "upload-file",
        name: "demo-upload-file",
        component: () => import("@/views/demo/UploadFile.vue"),
        meta: { title: "文件上传", icon: "example" }
      }
    ]
  },

  // {
  //   path: '/nested',
  //   component: Layout,
  //   redirect: '/nested/menu1',
  //   name: 'Nested',
  //   meta: {
  //     title: 'Nested',
  //     icon: 'nested'
  //   },
  //   children: [
  //     {
  //       path: 'menu1',
  //       component: () => import('@/views/nested/menu1/index'), // Parent router-view
  //       name: 'Menu1',
  //       meta: { title: 'Menu1' },
  //       children: [
  //         {
  //           path: 'menu1-1',
  //           component: () => import('@/views/nested/menu1/menu1-1'),
  //           name: 'Menu1-1',
  //           meta: { title: 'Menu1-1' }
  //         },
  //         {
  //           path: 'menu1-2',
  //           component: () => import('@/views/nested/menu1/menu1-2'),
  //           name: 'Menu1-2',
  //           meta: { title: 'Menu1-2' },
  //           children: [
  //             {
  //               path: 'menu1-2-1',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
  //               name: 'Menu1-2-1',
  //               meta: { title: 'Menu1-2-1' }
  //             },
  //             {
  //               path: 'menu1-2-2',
  //               component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
  //               name: 'Menu1-2-2',
  //               meta: { title: 'Menu1-2-2' }
  //             }
  //           ]
  //         },
  //         {
  //           path: 'menu1-3',
  //           component: () => import('@/views/nested/menu1/menu1-3'),
  //           name: 'Menu1-3',
  //           meta: { title: 'Menu1-3' }
  //         }
  //       ]
  //     },
  //     {
  //       path: 'menu2',
  //       component: () => import('@/views/nested/menu2/index'),
  //       name: 'Menu2',
  //       meta: { title: 'menu2' }
  //     }
  //   ]
  // },

  {
    path: "external-link",
    component: Layout,
    children: [
      {
        path: "/",
        meta: { title: "主页", icon: "link" }
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: "*", redirect: "/404", hidden: true }
];

const createRouter = () =>
  new Router({
    // mode: 'history', // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
  });

const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // reset router
}

export default router;
