-- 问题：utf8mb4 使用like查询不到数据，修改为utf8可以

-- 创建数据库
CREATE DATABASE `mo` DEFAULT CHARSET utf8;

-- 朝代
drop table if EXISTS mo_dynasty;

CREATE TABLE `mo_dynasty` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',

  `name` varchar(20) DEFAULT NULL COMMENT '朝代名称',

  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT '朝代';


-- 用户表
drop table if EXISTS mo_user;

CREATE TABLE `mo_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',

  `name` varchar(20) unique NOT NULL COMMENT '用户名',
  `dynasty_id` int(11) DEFAULT NULL COMMENT '朝代_id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `last_login_time` datetime DEFAULT null COMMENT '最后登录时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT '用户';



-- 角色表
drop table if EXISTS mo_role;

CREATE TABLE `mo_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',

  `name` varchar(20) unique NOT NULL COMMENT '角色名',
  
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT '角色';

insert into mo_role (name) values ("普通会员");
insert into mo_role (name) values ("管理员");
insert into mo_role (name) values ("超级管理员");


-- 权限表
drop table if EXISTS mo_permission;

CREATE TABLE `mo_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',

  `name` varchar(20) unique NOT NULL COMMENT '权限名',
  
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT '权限';

insert into mo_permission (`name`) values ("查看");
insert into mo_permission (`name`) values ("添加");
insert into mo_permission (`name`) values ("修改");
insert into mo_permission (`name`) values ("删除");


-- 角色-权限表
drop table if EXISTS mo_role_permission;

CREATE TABLE `mo_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',

  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',

  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT '角色-权限';


-- 用户密码表
drop table if EXISTS mo_user_password;

CREATE TABLE `mo_user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',

  `password` varchar(100) NOT NULL COMMENT '用户密码',

  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT '用户密码表';


-- 文章表
drop table if EXISTS mo_blog;

CREATE TABLE `mo_blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',

  `user_id` int(11) DEFAULT NULL COMMENT '作者_id',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  `is_show` TINYINT(1) DEFAULT 1 COMMENT '显示状态',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',

  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT '文章';


-- 邮件
drop table if EXISTS mo_email;

CREATE TABLE `mo_email` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',

  `to_user` varchar(50) not NULL COMMENT '收件人',
  `subject` varchar(100) not NULL COMMENT '邮件主题',
  `content` text not NULL COMMENT '邮件内容',
  `is_send` TINYINT(1) DEFAULT 0 COMMENT '发送状态',

  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci COMMENT '邮件';
