/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : spring

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-01-22 16:05:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cost`
-- ----------------------------
DROP TABLE IF EXISTS `cost`;
CREATE TABLE `cost` (
  `cost_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `base_duration` int(10) DEFAULT NULL,
  `base_cost` double(7,2) DEFAULT NULL,
  `unit_cost` double(7,4) DEFAULT NULL,
  `status` char(1) DEFAULT NULL,
  `descr` varchar(100) DEFAULT NULL,
  `creatime` timestamp NULL DEFAULT NULL,
  `startime` timestamp NULL DEFAULT NULL,
  `cost_type` char(1) DEFAULT NULL,
  PRIMARY KEY (`cost_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cost
-- ----------------------------
INSERT INTO `cost` VALUES ('1', '5.9元套餐', '20', '5.90', '0.4000', '0', '5.9元20小时/月,超出部分0.4元/时', '2018-01-09 09:51:28', '2018-01-09 09:51:28', '2');
INSERT INTO `cost` VALUES ('2', '6.9元套餐', '40', '6.90', '0.3000', '0', '6.9元40小时/月,超出部分0.3元/时', '2018-01-09 09:51:28', '2018-01-09 09:51:28', '2');
INSERT INTO `cost` VALUES ('3', '8.5元套餐', '100', '8.50', '0.2000', '0', '8.5元100小时/月,超出部分0.2元/时', '2018-01-09 09:51:28', '2018-01-09 09:51:28', '2');
INSERT INTO `cost` VALUES ('4', '10.5元套餐', '200', '10.50', '0.1000', '0', '10.5元200小时/月,超出部分0.1元/时', '2018-01-09 09:51:28', '2018-01-09 09:51:28', '2');
INSERT INTO `cost` VALUES ('5', '计时收费', null, null, '0.5000', '0', '0.5元/时,不使用不收费', '2018-01-09 09:51:28', '2018-01-09 09:51:28', '3');
INSERT INTO `cost` VALUES ('6', '包月', null, '20.00', null, '0', '每月20元,不限制使用时间', '2018-01-09 09:51:28', '2018-01-09 09:51:28', '1');
INSERT INTO `cost` VALUES ('7', 'mm', '10', '5.00', null, '0', '', '2018-01-09 09:51:28', '2018-01-09 09:51:28', '2');
INSERT INTO `cost` VALUES ('8', 'nn', '10', '7.00', null, '0', '', '2018-01-09 09:51:28', '2018-01-09 09:51:28', '2');
INSERT INTO `cost` VALUES ('9', 'qq', '10', '4.00', null, '0', '', '2018-01-09 09:51:28', '2018-01-09 09:51:28', '2');
INSERT INTO `cost` VALUES ('11', 'Tarena套餐', '80', '80.00', '0.8000', '0', 'Tarena', '2018-01-12 16:42:55', '2018-01-12 16:42:55', '2');

-- ----------------------------
-- Table structure for `t_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `deptno` int(4) NOT NULL AUTO_INCREMENT,
  `dname` varchar(50) DEFAULT NULL,
  `loc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('10', 'ACCOUNTING', 'NEWYORK');
INSERT INTO `t_dept` VALUES ('20', 'RESEARCH', 'DALLAS');
INSERT INTO `t_dept` VALUES ('30', 'SALES', 'CHICAGO');
INSERT INTO `t_dept` VALUES ('40', 'OPERATIONS', 'BOSTON');

-- ----------------------------
-- Table structure for `t_emp`
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `empno` int(4) NOT NULL AUTO_INCREMENT,
  `ename` varchar(20) DEFAULT NULL,
  `job` varchar(10) DEFAULT NULL,
  `mgr` int(4) DEFAULT NULL,
  `hiredate` date DEFAULT NULL,
  `sal` double(9,2) DEFAULT NULL,
  `comm` double(9,2) DEFAULT NULL,
  `deptno` int(4) DEFAULT NULL,
  PRIMARY KEY (`empno`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES ('1', 'SMITH', 'CLERK', '3', '1980-05-12', '800.00', null, '20');
INSERT INTO `t_emp` VALUES ('2', 'ALLEN', 'SALESMAN', '3', '1981-06-03', '1600.00', '300.00', '30');
INSERT INTO `t_emp` VALUES ('3', 'WARD', 'SALESMAN', '4', '1990-03-15', '1250.00', '500.00', '30');
INSERT INTO `t_emp` VALUES ('4', 'JONES', 'MANAGER', '5', '1985-04-08', '2975.00', null, '20');
INSERT INTO `t_emp` VALUES ('5', 'MARTIN', 'SALESMAN', '7', '1986-03-08', '1250.00', '1400.00', '30');
INSERT INTO `t_emp` VALUES ('6', 'BLAKE', 'MANAGER', '9', '1989-06-01', '2850.00', null, '30');
INSERT INTO `t_emp` VALUES ('7', 'CLARK', 'MANAGER', '9', '1995-10-01', '2450.00', null, '10');
INSERT INTO `t_emp` VALUES ('8', 'SCOTT', 'ANALYST', '9', '1993-05-01', '3000.00', null, '20');
INSERT INTO `t_emp` VALUES ('9', 'KING', 'PRESIDENT', null, '1988-08-08', '5000.00', null, '10');
INSERT INTO `t_emp` VALUES ('10', 'TURNER', 'SALESMAN', '5', '1983-02-01', '1500.00', '0.00', '30');
INSERT INTO `t_emp` VALUES ('11', 'ADAMS', 'CLERK', '5', '1992-07-03', '1100.00', null, '20');
INSERT INTO `t_emp` VALUES ('12', 'JAMES', 'CLERK', '1', '1996-09-10', '950.00', null, '30');
INSERT INTO `t_emp` VALUES ('13', 'FORD', 'ANALYST', '1', '1993-01-01', '3000.00', null, '20');
INSERT INTO `t_emp` VALUES ('14', 'MILLER', 'CLERK', '3', '1983-10-09', '1300.00', null, '10');
INSERT INTO `t_emp` VALUES ('16', 'tom', null, null, null, null, null, null);
