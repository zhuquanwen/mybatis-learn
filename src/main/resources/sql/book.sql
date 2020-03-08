/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : mybatis_learn

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-03-08 14:23:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `library_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `book_ibfk_1` (`library_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`library_id`) REFERENCES `library` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', 'C语言程序设计V2', '789', '1');
INSERT INTO `book` VALUES ('2', '泰戈尔诗集', '234', null);
INSERT INTO `book` VALUES ('3', '朝花夕拾', '435', null);
INSERT INTO `book` VALUES ('5', '离散数学', '400', '1');
INSERT INTO `book` VALUES ('6', '大数据时代', '300', '1');
