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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


-- 用户表
drop table if EXISTS mo_user;

CREATE TABLE `mo_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',

  `name` varchar(20) unique NOT NULL COMMENT '用户名',
  `dynasty_id` int(11) DEFAULT NULL COMMENT '朝代_id',
  `last_login_time` datetime DEFAULT null COMMENT '最后登录时间',

  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci