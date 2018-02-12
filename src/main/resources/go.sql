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


