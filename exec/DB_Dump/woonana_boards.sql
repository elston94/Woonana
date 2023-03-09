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
-- Table structure for table `boards`
--

DROP TABLE IF EXISTS `boards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `boards` (
  `board_id` bigint NOT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `last_modified_date` datetime(6) DEFAULT NULL,
  `board_allowed_number` int DEFAULT NULL,
  `board_content` varchar(255) DEFAULT NULL,
  `board_image_url` varchar(255) DEFAULT NULL,
  `board_max_number` int DEFAULT NULL,
  `board_meet_end_date` datetime(6) DEFAULT NULL,
  `board_meet_start_date` datetime(6) DEFAULT NULL,
  `board_participation_option` int DEFAULT NULL,
  `board_place` varchar(255) DEFAULT NULL,
  `board_status` int DEFAULT NULL,
  `board_title` varchar(255) DEFAULT NULL,
  `exercise_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  KEY `FKjy7hi6kn3wm1mdq4mmp967one` (`exercise_id`),
  KEY `FK7kt8hby5livgmjj15f79e9t6v` (`user_id`),
  CONSTRAINT `FK7kt8hby5livgmjj15f79e9t6v` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKjy7hi6kn3wm1mdq4mmp967one` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`exercise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `boards`
--

LOCK TABLES `boards` WRITE;
/*!40000 ALTER TABLE `boards` DISABLE KEYS */;
INSERT INTO `boards` VALUES (4,'2022-05-06 12:45:56.000000','2022-05-06 12:45:56.000000',2,'글러브있는분','urlurlrulr',2,'2022-05-09 18:45:56.000000','2022-05-09 20:06:01.000000',1,'seoul',0,'title',4,1),(52,'2022-05-16 20:14:37.477813','2022-05-16 20:14:37.477813',1,'공원 산책 ㄱㄱ','https://woonana.s3.ap-northeast-2.amazonaws.com/static/2993b60a-6c65-4ff6-a59f-6ab8e759e0f0cb.jpg',3,'2022-05-21 16:00:00.000000','2022-05-21 14:00:00.000000',1,'seoul',0,'일요일 저녁 산책하실분 구해요!',3,2),(54,'2022-05-16 20:21:58.775781','2022-05-16 20:21:58.775781',1,'글러브있는분','https://woonana.s3.ap-northeast-2.amazonaws.com/static/56b8e9ed-7cbf-44a2-b84e-8725d74574c7cb.jpg',2,'2022-05-22 16:00:00.000000','2022-05-22 14:00:00.000000',1,'seoul',0,'캐치볼 하실',3,2),(56,'2022-05-16 20:24:04.644498','2022-05-16 20:24:04.644498',1,'산책하실분','https://woonana.s3.ap-northeast-2.amazonaws.com/static/3faddfb3-9525-4a58-9634-5ee00cecc8dfKakaoTalk_20220516_151539954.jpg',2,'2017-06-01 09:30:00.000000','2017-06-01 08:30:00.000000',1,'asdf',0,'asdf',3,3),(58,'2022-05-16 21:31:54.191667','2022-05-16 21:31:54.191667',1,'가볍게 운동해요','https://woonana.s3.ap-northeast-2.amazonaws.com/static/ec9ff03e-bb03-4485-a305-1dcbe6708c18Woonana_small.png',2,'2022-05-17 21:31:00.000000','2022-05-16 21:31:00.000000',0,'',0,'탁구하실분',1,3),(60,'2022-05-16 21:36:28.186538','2022-05-16 21:36:28.186538',1,'공원 산책 ㄱㄱ','https://woonana.s3.ap-northeast-2.amazonaws.com/static/b8d2a340-1a61-4f68-a29e-8e59c3b60a34Woonana_small.png',3,'2022-05-27 21:33:00.000000','2022-05-26 21:33:00.000000',0,'충남 천안시 서북구 불당26로 80 ',0,'일요일 저녁 산책하실분 구해요!',1,3),(62,'2022-05-16 21:38:10.248705','2022-05-16 21:38:10.248705',1,'글러브있는분','https://woonana.s3.ap-northeast-2.amazonaws.com/static/0d550146-17d1-4021-a8d4-6aeb2b68d838Woonana_Dark.jpg',2,'2022-05-31 21:37:00.000000','2022-05-30 21:37:00.000000',1,'',0,'산책하실분',4,3),(64,'2022-05-16 21:38:54.313614','2022-05-16 21:38:54.313614',1,'글러브있는분','https://woonana.s3.ap-northeast-2.amazonaws.com/static/7804ab66-57f8-4c56-905e-997949de1f5aKakaoTalk_20220516_150414285.jpg',4,'2022-06-10 21:38:00.000000','2022-06-09 21:38:00.000000',0,' ',0,'산책하실분',4,3),(66,'2022-05-16 21:42:48.122214','2022-05-16 21:42:48.122214',1,'글러브있는분','https://woonana.s3.ap-northeast-2.amazonaws.com/static/cfe8715d-2e65-4d05-b0be-e4649f199795KakaoTalk_20220516_151539954.jpg',3,'2022-05-04 21:42:00.000000','2022-05-03 21:42:00.000000',1,'',0,'캐치볼하실분',3,3),(68,'2022-05-16 21:53:56.976424','2022-05-16 22:41:48.132658',2,'글러브있는분','https://woonana.s3.ap-northeast-2.amazonaws.com/static/8380559e-eeb6-4a53-8a07-b944f292cf49KakaoTalk_20220516_151539954.jpg',2,'2022-06-10 23:54:00.000000','2022-06-10 22:53:00.000000',0,'',1,'테스트',1,3),(70,'2022-05-16 21:58:34.439448','2022-05-16 21:58:34.439448',1,'글러브있는분','https://woonana.s3.ap-northeast-2.amazonaws.com/static/bd67756e-9583-4628-8329-8d55e1665dc6KakaoTalk_20220516_151539954.jpg',2,'2022-06-27 22:58:00.000000','2022-06-27 21:58:00.000000',0,'',0,'탁구조지실분',1,3),(72,'2022-05-16 22:00:26.364460','2022-05-16 22:00:26.364460',1,'글러브있는분','https://woonana.s3.ap-northeast-2.amazonaws.com/static/0e489bb5-d97a-4737-a824-4e6a07162b2dKakaoTalk_20220516_101050906.png',2,'2022-03-29 22:59:00.000000','2022-03-29 21:59:00.000000',0,'',0,'배드민턴 조지실분',2,3),(74,'2022-05-16 22:04:05.762712','2022-05-16 22:04:05.762712',1,'글러브있는분','https://woonana.s3.ap-northeast-2.amazonaws.com/static/fa2e111b-9a9f-46b7-a0a8-ba55093bbfcbKakaoTalk_20220516_151539954.jpg',2,'2022-05-07 23:03:00.000000','2022-05-07 22:03:00.000000',1,'',0,'운동 아무거나 조지실분',1,3),(77,'2022-05-16 23:25:07.057096','2022-05-16 23:25:07.057096',1,'글러브있는분','https://woonana.s3.ap-northeast-2.amazonaws.com/static/7ead9b42-07f9-4292-b07f-d3f0fbe97aaaKakaoTalk_20220516_151539954.jpg',3,'2022-05-14 23:24:00.000000','2022-05-13 23:24:00.000000',1,'',0,'테스트2',3,3),(79,'2022-05-17 01:53:43.946652','2022-05-17 01:53:43.946652',1,'이거 왜 고정됨?','https://woonana.s3.ap-northeast-2.amazonaws.com/static/16a20368-0b96-44ab-b439-69108860bb49KakaoTalk_20220516_151539954.jpg',2,'2022-02-28 01:53:00.000000','2022-02-27 01:53:00.000000',0,'',0,'글작성 테스트',2,3),(81,'2022-05-17 01:59:35.527985','2022-05-17 01:59:35.527985',1,'이젠 잘 오나?','https://woonana.s3.ap-northeast-2.amazonaws.com/static/a173e234-7aea-41a6-892b-66c22be38136KakaoTalk_20220516_101050906.png',2,'2022-10-04 02:59:00.000000','2022-10-04 01:59:00.000000',0,'충남 천안시 서북구 불당26로 80 ',0,'글작성 테스트',2,3),(83,'2022-05-16 23:46:43.686432','2022-05-16 23:46:43.686432',1,'2시간 산책만 하고 바로 헤어질 분 구합니다','https://woonana.s3.ap-northeast-2.amazonaws.com/static/187b4715-99c3-4b39-a1ff-8d71db0bf9dd20220217_101846.jpg',3,'2022-05-18 10:42:00.000000','2022-05-18 08:41:00.000000',0,'충북 청주시 서원구 서부로 1473 ',0,'2시간 산책 하실 분',4,6),(85,'2022-05-17 11:37:37.995893','2022-05-17 11:37:37.995893',1,'ㅁㄴㅇㄹ','https://woonana.s3.ap-northeast-2.amazonaws.com/static/e743ce25-c323-434b-af58-d6fd53c0a060noimage.png',2,'2022-01-13 00:30:00.000000','2022-01-13 11:30:00.000000',0,'충남 천안시 서북구 불당26로 80 ',0,'ㅁㄴㅇㄹ',1,3);
/*!40000 ALTER TABLE `boards` ENABLE KEYS */;
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
