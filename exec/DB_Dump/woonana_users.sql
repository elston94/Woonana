-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: k6b104.p.ssafy.io    Database: woonana
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `access_token` varchar(255) DEFAULT NULL,
  `kakao_id` bigint DEFAULT NULL,
  `user_birthday` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_nickname` varchar(255) DEFAULT NULL,
  `user_profile_url` varchar(255) DEFAULT NULL,
  `user_rating_score` int DEFAULT NULL,
  `user_sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'QSd-34iCPsXmskcOoT0_eudR9Ce6KkkslYoSjQorDNQAAAGAmFsrIA',2230773129,'0815','tjrdnjs1609@hanmail.net','김석원','http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg',0,NULL),(2,'tuzKG2ou3MWh4Z9D5Yc5fZ-8FsWetuwxkAy2ngopb7gAAAGAmGTN1w',2220561222,'0620','ddj03340@naver.com','정채은','http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg',4,NULL),(3,'vN0njRiJKzPQNNsWJSBbMo4K7Iq2mYa5MFlaxAopb1UAAAGAp01eqg',2235075231,'0526','ufoyws@naver.com','유우식','http://k.kakaocdn.net/dn/Mji6a/btrahhFjLzK/mDSx60CSNpwkk4eFScYkKk/img_640x640.jpg',3,NULL),(6,'C6aypBcjW-kOqXFBCAjSWCuR9wJ7ohRn5hnXDQo9dZwAAAGAtsdRnA',2217353168,'0928','naronghi@naver.com','배나영','http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg',0,NULL),(7,'TdQA2XRbdZqkz4-dbfj53S7IQmAsau6NrDs4kgo9dNkAAAGAtyb52g',2228370287,'0425','elston0425@daum.net','허범','http://k.kakaocdn.net/dn/J1gDp/btryQqstmGH/E7cm8t8rOVKgM1ToR6LNHK/img_640x640.jpg',0,NULL),(8,'gLVo8eV4-iDVvrNeL4OGjH3k0wKH3X5M4AYyLwopyNoAAAGAy47AeA',2244527867,'0808','zbqlr456@naver.com','김응철','http://k.kakaocdn.net/dn/bK0tx5/btrv2FE3pvF/22ksgKU0tXBSZXGmrbdC2k/img_640x640.jpg',0,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-17 13:49:03
