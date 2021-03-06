-- ----------------------------
-- Table structure for p_user_role
-- ----------------------------
DROP TABLE IF EXISTS `p_user_role`;
CREATE TABLE `p_user_role` (
  `p_user_role_id` varchar(40) NOT NULL DEFAULT '0' COMMENT '主键',
  `p_user_id` varchar(40) NOT NULL DEFAULT '0' COMMENT '外键用户id',
  `p_role_id` varchar(40) NOT NULL COMMENT '外键角色id',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`p_user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
