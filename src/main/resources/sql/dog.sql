/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : localhost:3306
 Source Schema         : study

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 29/01/2019 15:48:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dog
-- ----------------------------
DROP TABLE IF EXISTS `dog`;
CREATE TABLE `dog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `owner` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dog
-- ----------------------------
INSERT INTO `dog` VALUES (1, 'kimi', 2, 'Lucy');
INSERT INTO `dog` VALUES (2, 'hapa', 3, 'Lily');
INSERT INTO `dog` VALUES (3, 'buji', 4, 'Mike');
INSERT INTO `dog` VALUES (4, 'laughy', 5, 'Rose');

SET FOREIGN_KEY_CHECKS = 1;
