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
-- Table structure for table `participations`
--

DROP TABLE IF EXISTS `participations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participations` (
  `participation_id` bigint NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `participation_allowed` bit(1) DEFAULT NULL,
  `participation_participated_check` bit(1) DEFAULT NULL,
  `board_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`participation_id`),
  KEY `FK8hflbvf140gcw9p615a6vr96v` (`board_id`),
  KEY `FK166yf958qjqf8uoslyuk9e19p` (`user_id`),
  CONSTRAINT `FK166yf958qjqf8uoslyuk9e19p` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FK8hflbvf140gcw9p615a6vr96v` FOREIGN KEY (`board_id`) REFERENCES `boards` (`board_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participations`
--

LOCK TABLES `participations` WRITE;
/*!40000 ALTER TABLE `participations` DISABLE KEYS */;
INSERT INTO `participations` VALUES (53,'2022-05-16 20:14:37.527307','2022-05-16 20:14:37.527307',_binary '',_binary '\0',52,2),(55,'2022-05-16 20:21:58.815706','2022-05-16 20:21:58.815706',_binary '',_binary '\0',54,2),(57,'2022-05-16 20:24:04.724503','2022-05-16 20:24:04.724503',_binary '',_binary '\0',56,3),(59,'2022-05-16 21:31:54.248669','2022-05-16 21:31:54.248669',_binary '',_binary '\0',58,3),(61,'2022-05-16 21:36:28.234537','2022-05-16 21:36:28.234537',_binary '',_binary '\0',60,3),(63,'2022-05-16 21:38:10.295696','2022-05-16 21:38:10.295696',_binary '',_binary '\0',62,3),(65,'2022-05-16 21:38:54.361615','2022-05-16 21:38:54.361615',_binary '',_binary '\0',64,3),(67,'2022-05-16 21:42:48.169214','2022-05-16 21:42:48.169214',_binary '',_binary '\0',66,3),(69,'2022-05-16 21:53:57.023424','2022-05-16 21:53:57.023424',_binary '',_binary '\0',68,3),(71,'2022-05-16 21:58:34.489449','2022-05-16 21:58:34.489449',_binary '',_binary '\0',70,3),(73,'2022-05-16 22:00:26.412460','2022-05-16 22:00:26.412460',_binary '',_binary '\0',72,3),(75,'2022-05-16 22:04:05.819706','2022-05-16 22:04:05.819706',_binary '',_binary '\0',74,3),(76,'2022-05-16 22:40:45.142984','2022-05-16 22:41:48.126661',_binary '',_binary '\0',68,6),(78,'2022-05-16 23:25:07.108095','2022-05-16 23:25:07.108095',_binary '',_binary '\0',77,3),(80,'2022-05-17 01:53:43.993652','2022-05-17 01:53:43.993652',_binary '',_binary '\0',79,3),(82,'2022-05-17 01:59:35.585984','2022-05-17 01:59:35.585984',_binary '',_binary '\0',81,3),(84,'2022-05-16 23:46:43.712173','2022-05-16 23:46:43.712173',_binary '',_binary '\0',83,6),(86,'2022-05-17 11:37:38.084893','2022-05-17 11:37:38.084893',_binary '',_binary '\0',85,3);
/*!40000 ALTER TABLE `participations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-17 13:49:04
