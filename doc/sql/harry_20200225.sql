/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : harry

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 25/02/2020 08:38:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片验证码';

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'type 如：order_cancel_reason',
  `param_key` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'key',
  `param_name` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'name',
  `status` tinyint(1) DEFAULT '0' COMMENT '状态   0：禁用   1：启用',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='系统配置信息表/枚举值表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1, 'order_cancel_reason', '1', '质量问题', 1, '质量问题', '2020-02-18 14:07:48', '2020-02-18 15:16:13', 1);
INSERT INTO `sys_config` VALUES (2, 'order_cancel_reason', '2', '尺码太大', 1, '尺码太大le', '2020-02-18 14:07:48', '2020-02-18 15:16:07', 1);
INSERT INTO `sys_config` VALUES (3, 'order_cancel_reason', '3', '颜色不喜欢', 1, '颜色不喜欢', '2020-02-18 14:07:48', '2020-02-18 14:08:33', 1);
INSERT INTO `sys_config` VALUES (4, 'order_cancel_reason', '4', '7天无理由退货', 1, '7天无理由退货', '2020-02-18 14:07:48', '2020-02-18 14:08:33', 1);
INSERT INTO `sys_config` VALUES (5, 'order_cancel_reason', '5', '价格问题', 1, '价格问题', '2020-02-18 14:07:48', '2020-02-18 14:08:34', 1);
INSERT INTO `sys_config` VALUES (6, 'order_cancel_reason', '6', '发票问题', 1, '发票问题', '2020-02-18 14:07:48', '2020-02-18 14:08:36', 1);
INSERT INTO `sys_config` VALUES (7, 'order_cancel_reason', '7', '其他问题', 1, '其他问题', '2020-02-18 14:07:48', '2020-02-18 14:08:35', 1);
INSERT INTO `sys_config` VALUES (8, 'order_cancel_reason', '8', '物流问题', 0, '物流问题', '2020-02-18 14:07:48', '2020-02-21 14:42:10', 1);
INSERT INTO `sys_config` VALUES (9, 'order_cancel_reason', '9', '售后问题', 1, '售后问题', '2020-02-18 14:07:48', '2020-02-21 14:41:09', 1);
INSERT INTO `sys_config` VALUES (10, 'order_cancel_reason', '10', '其他问题', 1, '其他问题', '2020-02-18 14:07:48', '2020-02-18 15:15:11', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `value` varchar(200) DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) DEFAULT NULL COMMENT '图标',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) DEFAULT NULL COMMENT '前端资源路径',
  `status` int(1) DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='后台用户权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', NULL, 'sys', 0, '/sys', 1, '2018-09-29 16:15:14', 5);
INSERT INTO `sys_menu` VALUES (11, 1, '角色管理', 'sys:role:read', 'qq', 1, '/sys/role', 1, '2018-09-29 16:18:51', 1);
INSERT INTO `sys_menu` VALUES (12, 1, '菜单管理', 'sys:menu:read', NULL, 1, '/sys/menu', 1, '2018-09-29 16:23:07', 2);
INSERT INTO `sys_menu` VALUES (13, 1, '用户管理', 'sys:user:read', NULL, 1, '/sys/user', 1, '2018-09-29 16:17:01', 0);
INSERT INTO `sys_menu` VALUES (14, 1, '参数配置', 'sys:config:read', '', 1, '/sys/config', NULL, '2020-02-19 11:42:00', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', '超级管理员 admin', NULL, '2019-10-28 14:09:00', 1, 0);
INSERT INTO `sys_role` VALUES (2, '测试', '测试', NULL, '2020-02-19 11:44:23', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `menu_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8 COMMENT='后台用户角色和权限关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (114, 1, 11);
INSERT INTO `sys_role_menu` VALUES (115, 1, 12);
INSERT INTO `sys_role_menu` VALUES (116, 1, -666666);
INSERT INTO `sys_role_menu` VALUES (117, 1, 1);
INSERT INTO `sys_role_menu` VALUES (118, 2, 14);
INSERT INTO `sys_role_menu` VALUES (119, 2, -666666);
INSERT INTO `sys_role_menu` VALUES (120, 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_log`;
CREATE TABLE `sys_sms_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '短信记录id',
  `mobiles` varchar(500) DEFAULT NULL COMMENT '手机号',
  `param` varchar(500) DEFAULT NULL COMMENT '参数',
  `content` varchar(255) DEFAULT NULL COMMENT '短信内容',
  `type` tinyint(3) DEFAULT NULL COMMENT '短信类型 10 单发 20 群发',
  `type_name` varchar(20) DEFAULT NULL COMMENT '短信类型名称',
  `template_code` varchar(64) DEFAULT NULL COMMENT '模板号',
  `status` tinyint(1) DEFAULT NULL COMMENT '结果成功失败 0 失败 1 成功',
  `result` varchar(255) DEFAULT NULL COMMENT '第三方返回结果',
  `source` tinyint(3) DEFAULT NULL COMMENT '来源平台',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='短信日志表';

-- ----------------------------
-- Table structure for sys_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_template`;
CREATE TABLE `sys_sms_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `value` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(64) DEFAULT NULL COMMENT '作用',
  `sign_name` varchar(64) DEFAULT NULL COMMENT '短信签名',
  `source` tinyint(3) DEFAULT NULL COMMENT '来源平台',
  `template_code` varchar(64) DEFAULT NULL COMMENT '短信模板CODE',
  `content` varchar(255) DEFAULT NULL COMMENT '模板内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `value` (`value`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='短信模板';

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `company_id` bigint(20) DEFAULT NULL COMMENT '所属公司ID',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '所属公司名称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '最后登陆IP',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=108 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$x3bWW9r16kkukdCZdrVV9exxRerY7R.tf4tAi6flzTyfCCaBg.baS', 1, 'Harry科技', 6, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '183865800@qq.com', 'Harry', 'assa的收到dfsd s', 1, '2019-09-29 13:55:39', NULL, '2019-09-29 13:55:30', '2020-02-18 13:33:57', 1);
INSERT INTO `sys_user` VALUES (107, 'test', '$2a$10$x3bWW9r16kkukdCZdrVV9exxRerY7R.tf4tAi6flzTyfCCaBg.baS', 1, 'Harry科技', 6, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '183865800@qq.com', 'Harry测试', 'assa的收到dfsd', 1, '2019-09-29 13:55:39', NULL, '2019-09-29 13:55:30', '2020-02-21 14:59:24', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_log`;
CREATE TABLE `sys_user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台用户登录日志表';

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (13, 1, 1);
INSERT INTO `sys_user_role` VALUES (15, 107, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
