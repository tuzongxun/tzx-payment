CREATE TABLE `pay_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(50) DEFAULT NULL,
  `seller_id` varchar(50) DEFAULT NULL,
  `pay_type` varchar(10) DEFAULT NULL,
  `channel` varchar(10) DEFAULT NULL,
  `param_name` varchar(50) DEFAULT NULL,
  `param_value` text NOT NULL,
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `param_desc` varchar(30) DEFAULT NULL,
  `thirdMerId` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8