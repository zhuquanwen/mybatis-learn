/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : mybatis_learn

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-03-16 23:06:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bird
-- ----------------------------
DROP TABLE IF EXISTS `bird`;
CREATE TABLE `bird` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `sex` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of bird
-- ----------------------------
INSERT INTO `bird` VALUES ('1', '小黄', '1');
INSERT INTO `bird` VALUES ('2', '小绿', '0');
INSERT INTO `bird` VALUES ('3', '小黑', '1');
INSERT INTO `bird` VALUES ('4', '小紫', '0');
