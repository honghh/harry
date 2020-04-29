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

 Date: 29/04/2020 14:45:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_captcha
-- ----------------------------
DROP TABLE IF EXISTS `sys_captcha`;
CREATE TABLE `sys_captcha` (
  `uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'uuid',
  `code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='图片验证码';

-- ----------------------------
-- Records of sys_captcha
-- ----------------------------
BEGIN;
INSERT INTO `sys_captcha` VALUES ('7269456bb43f4e9880af79d742cdb02a', '4ppy7', '2020-04-29 01:48:49');
INSERT INTO `sys_captcha` VALUES ('c2dbb328475643e7b6a7b71572245bcf', 'xc7gw', '2020-04-29 01:46:49');
COMMIT;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `param_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '系统内置: Y N ',
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'key',
  `param_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'value',
  `param_name` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'name',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '状态   0：禁用   1：启用',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='系统配置信息表/枚举值表';

-- ----------------------------
-- Records of sys_config
-- ----------------------------
BEGIN;
INSERT INTO `sys_config` VALUES (1, 'Y', 'sys.index.skinName', 'skin-blue', '主框架页-默认皮肤样式名称', '1', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow\r', '2020-04-28 10:42:34', '2020-04-28 10:42:34', 1);
INSERT INTO `sys_config` VALUES (2, 'Y', 'sys.user.initPassword', '123456', '用户管理-账号初始密码', '1', '初始化密码 123456\r', '2020-04-28 10:42:34', '2020-04-28 10:42:34', 1);
INSERT INTO `sys_config` VALUES (3, 'Y', 'sys.index.sideTheme', 'theme-dark', '主框架页-侧边栏主题', '1', '深色主题theme-dark，浅色主题theme-light', '2020-04-28 10:42:34', '2020-04-28 10:42:34', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NOT NULL COMMENT '上级部门',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '祖级列表',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `sort` int(4) DEFAULT '0' COMMENT '排序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (1, 0, '0', 'Harry技术', 0, 'Harry', '17777777777', 'honghh1217@163.com', '1', '2020-04-14 17:25:45', '2020-04-14 17:25:45', 1);
INSERT INTO `sys_dept` VALUES (2, 1, '0,1', '研发部', 0, 'yanfa', '18888888888', '188@163.com', '1', '2020-04-14 17:38:23', '2020-04-14 17:38:23', 1);
INSERT INTO `sys_dept` VALUES (3, 4, '0,1,4', '营销A组', 1, NULL, NULL, NULL, '0', '2020-04-27 17:27:30', '2020-04-29 11:18:23', 1);
INSERT INTO `sys_dept` VALUES (4, 1, '0,1', '营销部', 1, 'ahah', '17777777377', NULL, '1', '2020-04-29 09:39:44', '2020-04-29 11:18:15', 1);
INSERT INTO `sys_dept` VALUES (5, 1, '0,1', '产品部', 1, '张三', NULL, NULL, '1', '2020-04-29 09:40:14', '2020-04-29 11:18:31', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '状态（1正常 0停用）',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` VALUES (1, '用户性别', 'sys_user_sex', '1', '用户性别', '2020-04-27 15:18:28', '2020-04-27 15:18:28', 1);
INSERT INTO `sys_dict` VALUES (2, '菜单状态', 'sys_show_hide', '1', '菜单状态', '2020-04-27 15:18:28', '2020-04-27 15:18:28', 1);
INSERT INTO `sys_dict` VALUES (3, '系统开关', 'sys_normal_disable', '1', '系统开关', '2020-04-27 15:18:28', '2020-04-27 15:18:28', 1);
INSERT INTO `sys_dict` VALUES (4, '任务状态', 'sys_job_status', '1', '任务状态', '2020-04-27 15:18:28', '2020-04-27 15:18:28', 1);
INSERT INTO `sys_dict` VALUES (5, '任务分组', 'sys_job_group', '1', '任务分组', '2020-04-27 15:18:28', '2020-04-27 15:18:28', 1);
INSERT INTO `sys_dict` VALUES (6, '系统是否', 'sys_yes_no', '1', '系统是否', '2020-04-27 15:18:28', '2020-04-27 15:18:28', 1);
INSERT INTO `sys_dict` VALUES (7, '通知类型', 'sys_notice_type', '1', '通知类型', '2020-04-27 15:18:28', '2020-04-27 15:18:28', 1);
INSERT INTO `sys_dict` VALUES (8, '通知状态', 'sys_notice_status', '1', '通知状态', '2020-04-27 15:18:28', '2020-04-27 15:18:28', 1);
INSERT INTO `sys_dict` VALUES (9, '操作类型', 'sys_oper_type', '1', '操作类型', '2020-04-27 15:18:28', '2020-04-27 15:18:28', 1);
INSERT INTO `sys_dict` VALUES (10, '系统状态', 'sys_common_status', '1', '系统状态', '2020-04-27 15:18:28', '2020-04-27 15:18:28', 1);
INSERT INTO `sys_dict` VALUES (11, '用户岗位', 'sys_user_post', '1', '用户岗位', '2020-04-27 15:23:28', '2020-04-27 15:23:28', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_detail
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_detail`;
CREATE TABLE `sys_dict_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '状态（1正常 0停用）',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典数据表';

-- ----------------------------
-- Records of sys_dict_detail
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_detail` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (4, 1, '显示', '1', 'sys_show_hide', '', 'primary', 'Y', '1', '2020-04-27 15:20:24', '2020-04-29 08:43:48', 1);
INSERT INTO `sys_dict_detail` VALUES (5, 2, '隐藏', '0', 'sys_show_hide', '', 'danger', 'N', '1', '2020-04-27 15:20:24', '2020-04-29 08:43:50', 1);
INSERT INTO `sys_dict_detail` VALUES (6, 1, '正常', '1', 'sys_normal_disable', '', 'primary', 'Y', '1', '2020-04-27 15:20:24', '2020-04-29 08:43:59', 1);
INSERT INTO `sys_dict_detail` VALUES (7, 2, '停用', '0', 'sys_normal_disable', '', 'danger', 'N', '1', '2020-04-27 15:20:24', '2020-04-29 08:43:37', 1);
INSERT INTO `sys_dict_detail` VALUES (8, 1, '正常', '1', 'sys_job_status', '', 'primary', 'Y', '1', '2020-04-27 15:20:24', '2020-04-29 08:43:39', 1);
INSERT INTO `sys_dict_detail` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '1', '2020-04-27 15:20:24', '2020-04-27 15:20:24', 1);
INSERT INTO `sys_dict_detail` VALUES (27, 1, '成功', '1', 'sys_common_status', '', 'primary', 'N', '1', '2020-04-27 15:20:24', '2020-04-29 09:31:30', 1);
INSERT INTO `sys_dict_detail` VALUES (28, 2, '失败', '0', 'sys_common_status', '', 'danger', 'N', '1', '2020-04-27 15:20:24', '2020-04-29 09:31:31', 1);
INSERT INTO `sys_dict_detail` VALUES (29, 1, '董事长', '1', 'sys_user_post', NULL, NULL, 'N', '1', '2020-04-27 15:24:27', '2020-04-27 15:24:27', 1);
INSERT INTO `sys_dict_detail` VALUES (30, 2, '项目经理', '2', 'sys_user_post', NULL, NULL, 'N', '1', '2020-04-27 15:24:52', '2020-04-27 15:25:33', 1);
INSERT INTO `sys_dict_detail` VALUES (31, 3, '人力资源', '3', 'sys_user_post', NULL, NULL, 'N', '1', '2020-04-27 15:24:57', '2020-04-27 15:25:33', 1);
INSERT INTO `sys_dict_detail` VALUES (32, 4, '产品经理', '4', 'sys_user_post', NULL, NULL, 'N', '1', '2020-04-27 15:24:58', '2020-04-27 15:26:12', 1);
INSERT INTO `sys_dict_detail` VALUES (33, 5, '普通员工', '5', 'sys_user_post', NULL, NULL, 'Y', '1', '2020-04-27 15:25:56', '2020-04-27 15:26:34', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `value` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '图标',
  `type` int(1) DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '前端资源路径',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `outer_link` int(1) DEFAULT '0' COMMENT '是否为外链（1 是 0否）',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '路由地址',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1061 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='后台用户权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, 0, '系统管理', '', 'system', 0, '', 1, 0, 'system', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (2, 0, '系统监控', '', 'monitor', 0, '', 2, 0, 'monitor', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (3, 0, '系统工具', '', 'tool', 0, '', 3, 0, 'tool', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (4, 0, 'Harry官网', '', 'guide', 0, '', 4, 1, 'http://honghh.top', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (100, 1, '用户管理', 'system:user:list', 'user', 1, 'system/user/index', 1, 0, 'user', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (101, 1, '角色管理', 'system:role:list', 'peoples', 1, 'system/role/index', 2, 0, 'role', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (102, 1, '菜单管理', 'system:menu:list', 'tree-table', 1, 'system/menu/index', 3, 0, 'menu', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (103, 1, '部门管理', 'system:dept:list', 'tree', 1, 'system/dept/index', 4, 0, 'dept', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (105, 1, '字典管理', 'system:dict:list', 'dict', 1, 'system/dict/index', 6, 0, 'dict', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (106, 1, '参数设置', 'system:config:list', 'edit', 1, 'system/config/index', 7, 0, 'config', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (108, 1, '日志管理', '', 'log', 0, 'system/log/index', 9, 0, 'log', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (109, 2, '在线用户', 'monitor:online:list', 'online', 1, 'monitor/online/index', 1, 0, 'online', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (113, 3, '表单构建', 'tool:build:list', 'build', 1, 'tool/build/index', 1, 0, 'build', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (114, 3, '代码生成', 'tool:gen:list', 'code', 1, 'tool/gen/index', 2, 0, 'gen', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (115, 3, '系统接口', 'tool:swagger:list', 'swagger', 1, 'tool/swagger/index', 3, 0, 'swagger', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (500, 108, '操作日志', 'monitor:operlog:list', 'form', 1, 'monitor/operlog/index', 1, 0, 'operlog', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (501, 108, '登录日志', 'monitor:logininfor:list', 'logininfor', 1, 'monitor/logininfor/index', 2, 0, 'logininfor', '1', '2020-04-27 14:37:37');
INSERT INTO `sys_menu` VALUES (1001, 100, '用户查询', 'system:user:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1002, 100, '用户新增', 'system:user:add', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1003, 100, '用户修改', 'system:user:edit', '', 2, '', 3, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1004, 100, '用户删除', 'system:user:remove', '', 2, '', 4, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1005, 100, '用户导出', 'system:user:export', '', 2, '', 5, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1006, 100, '用户导入', 'system:user:import', '', 2, '', 6, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1007, 100, '重置密码', 'system:user:resetPwd', '', 2, '', 7, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1008, 101, '角色查询', 'system:role:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1009, 101, '角色新增', 'system:role:add', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1010, 101, '角色修改', 'system:role:edit', '', 2, '', 3, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1011, 101, '角色删除', 'system:role:remove', '', 2, '', 4, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1012, 101, '角色导出', 'system:role:export', '', 2, '', 5, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1013, 102, '菜单查询', 'system:menu:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1014, 102, '菜单新增', 'system:menu:add', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1015, 102, '菜单修改', 'system:menu:edit', '', 2, '', 3, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1016, 102, '菜单删除', 'system:menu:remove', '', 2, '', 4, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1017, 103, '部门查询', 'system:dept:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1018, 103, '部门新增', 'system:dept:add', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1019, 103, '部门修改', 'system:dept:edit', '', 2, '', 3, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1020, 103, '部门删除', 'system:dept:remove', '', 2, '', 4, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1021, 104, '岗位查询', 'system:post:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1022, 104, '岗位新增', 'system:post:add', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1023, 104, '岗位修改', 'system:post:edit', '', 2, '', 3, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1024, 104, '岗位删除', 'system:post:remove', '', 2, '', 4, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1025, 104, '岗位导出', 'system:post:export', '', 2, '', 5, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1026, 105, '字典查询', 'system:dict:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1027, 105, '字典新增', 'system:dict:add', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1028, 105, '字典修改', 'system:dict:edit', '', 2, '', 3, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1029, 105, '字典删除', 'system:dict:remove', '', 2, '', 4, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1030, 105, '字典导出', 'system:dict:export', '', 2, '', 5, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1031, 106, '参数查询', 'system:config:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1032, 106, '参数新增', 'system:config:add', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1033, 106, '参数修改', 'system:config:edit', '', 2, '', 3, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1034, 106, '参数删除', 'system:config:remove', '', 2, '', 4, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1035, 106, '参数导出', 'system:config:export', '', 2, '', 5, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1040, 500, '操作查询', 'monitor:operlog:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1041, 500, '操作删除', 'monitor:operlog:remove', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1042, 500, '日志导出', 'monitor:operlog:export', '', 2, '', 4, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1043, 501, '登录查询', 'monitor:logininfor:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1044, 501, '登录删除', 'monitor:logininfor:remove', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1045, 501, '日志导出', 'monitor:logininfor:export', '', 2, '', 3, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1046, 109, '在线查询', 'monitor:online:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1047, 109, '批量强退', 'monitor:online:batchLogout', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1048, 109, '单条强退', 'monitor:online:forceLogout', '', 2, '', 3, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1055, 114, '生成查询', 'tool:gen:query', '', 2, '', 1, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1056, 114, '生成修改', 'tool:gen:edit', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1057, 114, '生成删除', 'tool:gen:remove', '', 2, '', 3, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1058, 114, '导入代码', 'tool:gen:import', '', 2, '', 2, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1059, 114, '预览代码', 'tool:gen:preview', '', 2, '', 4, 0, '', '1', '2020-04-28 16:42:10');
INSERT INTO `sys_menu` VALUES (1060, 114, '生成代码', 'tool:gen:code', '', 2, '', 5, 0, '', '1', '2020-04-28 16:42:10');
COMMIT;

-- ----------------------------
-- Table structure for sys_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '返回参数',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '错误消息',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='操作日志记录';

-- ----------------------------
-- Records of sys_operation_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色权限字符',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 ）',
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='后台用户角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', '1', '超级管理员 admin', NULL, '2019-10-28 14:09:00', '1', 1);
INSERT INTO `sys_role` VALUES (2, '测试', 'test', '3', '测试', NULL, '2020-02-19 11:44:23', '1', 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `dept_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色部门关联';

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
BEGIN;
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='后台用户角色和权限关系表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 2, 1008);
INSERT INTO `sys_role_menu` VALUES (3, 2, 1013);
INSERT INTO `sys_role_menu` VALUES (4, 2, 1017);
INSERT INTO `sys_role_menu` VALUES (5, 2, 1026);
INSERT INTO `sys_role_menu` VALUES (6, 2, 1031);
INSERT INTO `sys_role_menu` VALUES (7, 2, 1040);
INSERT INTO `sys_role_menu` VALUES (8, 2, 1043);
INSERT INTO `sys_role_menu` VALUES (9, 2, 1046);
INSERT INTO `sys_role_menu` VALUES (10, 2, 1);
INSERT INTO `sys_role_menu` VALUES (11, 2, 100);
INSERT INTO `sys_role_menu` VALUES (12, 2, 101);
INSERT INTO `sys_role_menu` VALUES (13, 2, 102);
INSERT INTO `sys_role_menu` VALUES (14, 2, 103);
INSERT INTO `sys_role_menu` VALUES (15, 2, 105);
INSERT INTO `sys_role_menu` VALUES (16, 2, 106);
INSERT INTO `sys_role_menu` VALUES (17, 2, 108);
INSERT INTO `sys_role_menu` VALUES (18, 2, 500);
INSERT INTO `sys_role_menu` VALUES (19, 2, 501);
INSERT INTO `sys_role_menu` VALUES (20, 2, 2);
INSERT INTO `sys_role_menu` VALUES (21, 2, 109);
COMMIT;

-- ----------------------------
-- Table structure for sys_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_log`;
CREATE TABLE `sys_sms_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '短信记录id',
  `mobiles` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `param` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '参数',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '短信内容',
  `type` tinyint(3) DEFAULT NULL COMMENT '短信类型 10 单发 20 群发',
  `type_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '短信类型名称',
  `template_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模板号',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '结果成功失败 0 失败 1 成功',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '第三方返回结果',
  `source` tinyint(3) DEFAULT NULL COMMENT '来源平台',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='短信日志表';

-- ----------------------------
-- Records of sys_sms_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_sms_template`;
CREATE TABLE `sys_sms_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `value` bigint(20) NOT NULL COMMENT '编号',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '作用',
  `sign_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '短信签名',
  `source` tinyint(3) DEFAULT NULL COMMENT '来源平台',
  `template_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '短信模板CODE',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '模板内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `value` (`value`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='短信模板';

-- ----------------------------
-- Records of sys_sms_template
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '所属部门名称',
  `post_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '岗位组',
  `icon` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `nick_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别: 0 男,1 女,2 未知',
  `note` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '最后登陆IP',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='后台用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$x3bWW9r16kkukdCZdrVV9exxRerY7R.tf4tAi6flzTyfCCaBg.baS', 1, 'Harry科技', 'null', '', '183865800@qq.com', '17777777777', 'Harry', '0', 'assa的收到dfsd s', '1', '2019-09-29 13:55:39', NULL, '2019-09-29 13:55:30', '2020-04-27 16:10:46', 1);
INSERT INTO `sys_user` VALUES (101, 'test', '$2a$10$1xY1gbC84uP71SH0AwzSIeKjfqYzhUP0Bff2YgNaQkXbCk2/wkhVC', 2, '研发部', '[\"2\"]', '', '183865800@qq.com', '17777777777', 'Harry测试', '1', 'assa的收到dfsd', '1', '2019-09-29 13:55:39', NULL, '2019-09-29 13:55:30', '2020-04-29 14:04:06', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_login_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_login_log`;
CREATE TABLE `sys_user_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'key',
  `user_id` bigint(20) DEFAULT NULL,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
  `nick_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '昵称',
  `ip` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登陆IP',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登陆地点',
  `user_agent` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '浏览器登录类型',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `valid` int(1) DEFAULT '1' COMMENT '有效状态：0->无效；1->有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='后台用户登录日志表';

-- ----------------------------
-- Records of sys_user_login_log
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_login_log` VALUES (1, 'online:token:eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE1ODgxNDI1MTg0MDksImV4cCI6MTU4ODc0NzMxOH0.89CNCPRU_vDJXXVacWvq3A12Pn_pl8K8-SYyZP9JviMMoMe18SOSHV_OoJhHokLRVfVEn4hcYX45bPpZFlUIzA', 1, 'admin', 'Harry', '127.0.0.1', '内网IP', 'Chrome 8', '2020-04-29 01:41:58', '2020-04-29 14:43:49', 0);
INSERT INTO `sys_user_login_log` VALUES (2, 'online:token:eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiY3JlYXRlZCI6MTU4ODE0MjYzNzM2NCwiZXhwIjoxNTg4NzQ3NDM3fQ.ESBYrbYNzHI9ul2u0t5AztdeK09lVVz_8sTbD1Y1bk9at7yWjAOkoc2WqNzBo3eAAi38B_-z3ZUlex6pUQuTjg', 101, 'test', 'Harry测试', '192.168.1.52', '内网IP', 'Chrome 8', '2020-04-29 01:43:57', '2020-04-29 01:43:57', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 101, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
