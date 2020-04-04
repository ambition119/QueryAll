
CREATE TABLE `user_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL COMMENT '登录账号',
  `user_password` varchar(128) NOT NULL COMMENT '登录密码',
  `user_role` int(11) NOT NULL DEFAULT '1' COMMENT '登录角色，3超管，2查看修改，1查看',
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;