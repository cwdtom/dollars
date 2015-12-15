-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: dollars
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `d_user`
--

DROP TABLE IF EXISTS `d_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `d_user` (
  `u_id` bigint(8) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `u_username` varchar(45) NOT NULL COMMENT '用户名',
  `u_password` varchar(45) NOT NULL COMMENT '密码',
  `u_accesscode` varchar(45) NOT NULL DEFAULT '0' COMMENT '激活码',
  `u_unique` varchar(45) NOT NULL DEFAULT '0' COMMENT '唯一效验码',
  `u_headimgurl` varchar(100) NOT NULL DEFAULT '0' COMMENT '头像图片地址',
  `u_txt1` varchar(45) DEFAULT NULL COMMENT '保留字段',
  `u_txt2` varchar(45) DEFAULT NULL COMMENT '保留字段',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_user`
--

LOCK TABLES `d_user` WRITE;
/*!40000 ALTER TABLE `d_user` DISABLE KEYS */;
INSERT INTO `d_user` VALUES (1,'server','e10adc3949ba59abbe56e057f20f883e','0','0','0',NULL,NULL),(2,'tom','e10adc3949ba59abbe56e057f20f883e','0','1e7121125aae5cb47ab26e407fc58bbe','localhost:8080/dollars/res/img/headimg/15.png',NULL,NULL);
/*!40000 ALTER TABLE `d_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-15 17:29:42
