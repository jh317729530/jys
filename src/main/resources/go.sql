CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `salt` varchar(60) DEFAULT NULL COMMENT '盐值',
  `name` varchar(255) DEFAULT NULL COMMENT '名字',
  `sex` int(11) DEFAULT NULL COMMENT '性别 1-男 2-女',
  `is_admin` int(11) DEFAULT NULL COMMENT '是否管理员 0-否 1-是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_user`
ADD COLUMN `avatar`  varchar(500) NULL COMMENT '头像url' AFTER `sex`;

ALTER TABLE `t_user`
DROP COLUMN `name`,
DROP COLUMN `sex`;

CREATE TABLE `t_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '关联用户id',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `is_director` int(11) DEFAULT NULL COMMENT '是否主任',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_teacher`
ADD COLUMN `work_number`  varchar(50) NULL COMMENT '工号' AFTER `user_id`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL,
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(128) DEFAULT NULL COMMENT '角色描述',
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_role` (
`id`  int(11) NOT NULL ,
`user_id`  int(11) NULL COMMENT '用户id' ,
`role_id`  int(11) NULL COMMENT '角色id' ,
`create_date`  datetime NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`id`)
);

CREATE TABLE `t_permis` (
  `id` int(11) NOT NULL,
  `permis_name` varchar(64) DEFAULT NULL COMMENT '权限名称',
  `permis_desc` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `permis_module_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_role_permis` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `permis_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL,
  `url` varchar(64) DEFAULT NULL,
  `name` varchar(63) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_permis_resource` (
  `id` int(11) NOT NULL,
  `permis_id` int(11) DEFAULT NULL COMMENT '权限id',
  `resource_id` int(11) DEFAULT NULL COMMENT '接口id',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_permis_module` (
  `id` int(11) NOT NULL,
  `module_name` varchar(32) DEFAULT NULL COMMENT '模块名称',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `t_permis`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `t_permis_module`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `t_permis_resource`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `t_resource`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `t_role`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `t_role_permis`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT FIRST ;

ALTER TABLE `t_user_role`
MODIFY COLUMN `id`  int(11) NOT NULL AUTO_INCREMENT FIRST ;










