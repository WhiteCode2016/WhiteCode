/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : whitecode2016
Target Host     : localhost:3306
Target Database : whitecode2016
Date: 2017-10-19 15:08:24
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for task_detail
-- ----------------------------
DROP TABLE IF EXISTS `task_detail`;
CREATE TABLE `task_detail` (
  `job_id` varchar(100) NOT NULL COMMENT '任务Id',
  `job_name` varchar(200) DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(100) DEFAULT NULL COMMENT '任务分组',
  `cron_expression` varchar(200) DEFAULT NULL COMMENT '任务运行时间表达式',
  `bean_class` varchar(300) DEFAULT NULL COMMENT '任务执行类',
  `create_time` date DEFAULT NULL COMMENT '任务创建时间',
  `update_time` date DEFAULT NULL COMMENT '任务更新时间',
  `execute_method` varchar(100) DEFAULT NULL COMMENT '任务执行方法',
  `job_type` varchar(100) DEFAULT NULL COMMENT '任务类型 ASYNC(异步),SYNC(同步)',
  `job_status` varchar(5) DEFAULT NULL COMMENT '任务状态 0禁用 1启用 2删除',
  `job_desc` varchar(500) DEFAULT NULL COMMENT '任务描述',
  PRIMARY KEY (`job_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_detail
-- ----------------------------
INSERT INTO `task_detail` VALUES ('1', 'test123', 'testQuartzTask', '0/10 * * * * ? ', 'com.whitecode.factory.AsyncQuartzJobFactory', '2017-06-22', '2017-06-22', 'execute', 'ASYNC', '1', '打印一句话');
