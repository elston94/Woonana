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
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation` (
  `evaluation_id` bigint NOT NULL,
  `evaluation_rating_score` int DEFAULT NULL,
  `evaluation_target_id` bigint DEFAULT NULL,
  `evaluation_user_id` bigint DEFAULT NULL,
  `board_id` bigint DEFAULT NULL,
  PRIMARY KEY (`evaluation_id`),
  KEY `FK5q02pronq5w140ybjudi3r4eb` (`evaluation_target_id`),
  KEY `FKo9iv42eiil3byvtbkrobxu1ly` (`evaluation_user_id`),
  KEY `FKbv0h0kfmd3ojk414a169v04ta` (`board_id`),
  CONSTRAINT `FK5q02pronq5w140ybjudi3r4eb` FOREIGN KEY (`evaluation_target_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKbv0h0kfmd3ojk414a169v04ta` FOREIGN KEY (`board_id`) REFERENCES `boards` (`board_id`),
  CONSTRAINT `FKo9iv42eiil3byvtbkrobxu1ly` FOREIGN KEY (`evaluation_user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` VALUES (13,1,2,6,NULL),(14,3,2,6,NULL),(16,3,2,1,NULL),(21,6,2,1,NULL),(22,4,6,1,NULL),(23,2,6,1,NULL),(24,5,6,1,NULL),(25,3,6,1,NULL),(26,5,6,1,NULL),(27,5,6,1,NULL),(28,4,6,1,NULL),(29,4,6,1,NULL),(30,5,6,1,NULL),(31,5,6,1,NULL),(32,5,6,1,NULL),(33,5,6,1,NULL),(34,5,6,1,NULL),(35,5,6,1,NULL),(36,5,6,1,NULL),(37,5,6,1,NULL),(38,5,6,1,NULL),(39,3,2,6,NULL),(40,3,2,6,NULL),(41,3,2,6,NULL),(42,3,2,6,NULL),(43,5,2,6,NULL),(44,4,2,6,NULL),(45,4,2,6,NULL),(46,7,2,6,NULL),(47,7,2,6,NULL),(48,7,2,6,NULL),(49,7,2,6,NULL);
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
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
