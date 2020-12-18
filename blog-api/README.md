# MoBlog的后端接口子项目

完成后台管理的增删改查管理

技术路线
```
spring-boot
mybatis-plus
mybatis-plus-generator
bcrypt
jwt
```

## 已完成

1. 接口返回数据格式统一封装
2. 全局异常处理
3. 用户密码bcrypt加密及校验
4. 用户登录及JWT权限控制
5. p6spy打印sql

## Todo:
1. RBAC基于角色的访问控制权限


为了统一，获取数据用GET, 修改数据用POST