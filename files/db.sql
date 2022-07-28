-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: db_benningtone
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `comment_info`
--

DROP TABLE IF EXISTS `comment_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_time` datetime DEFAULT NULL,
  `music_comment` varchar(255) DEFAULT NULL,
  `music_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_info`
--

LOCK TABLES `comment_info` WRITE;
/*!40000 ALTER TABLE `comment_info` DISABLE KEYS */;
INSERT INTO `comment_info` VALUES (1,'2022-07-25 05:28:14','Good Song Indeed','Music_180722131955','prabir1956@gmail.com'),(2,'2022-07-25 05:30:08','Can relate with me','Music_220722151921','prabir1956@gmail.com'),(3,'2022-07-25 05:32:46','Chester will be with us forever','Music_180722131955','prabir1956@gmail.com'),(4,'2022-07-25 06:14:54','In the End it does n\'t even Matter','Music_180722131955','sudeshna1999@gmail.com');
/*!40000 ALTER TABLE `comment_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) NOT NULL,
  `next_val` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('default',0);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_dislike_info`
--

DROP TABLE IF EXISTS `like_dislike_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `like_dislike_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `disliked` bit(1) DEFAULT NULL,
  `liked` bit(1) DEFAULT NULL,
  `music_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_dislike_info`
--

LOCK TABLES `like_dislike_info` WRITE;
/*!40000 ALTER TABLE `like_dislike_info` DISABLE KEYS */;
INSERT INTO `like_dislike_info` VALUES (1,_binary '\0',_binary '','Music_180722131955','prabir1956@gmail.com'),(2,_binary '',_binary '\0','Music_180722131955','sudeshna1999@gmail.com');
/*!40000 ALTER TABLE `like_dislike_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music_album_info`
--

DROP TABLE IF EXISTS `music_album_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music_album_info` (
  `music_album_id` varchar(255) NOT NULL,
  `album_img` varchar(255) DEFAULT NULL,
  `album_name` varchar(255) NOT NULL,
  `publish_year` int(11) DEFAULT NULL,
  PRIMARY KEY (`music_album_id`),
  UNIQUE KEY `UK_ra4nsy415s1f1gpkj05sxvnm5` (`album_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_album_info`
--

LOCK TABLES `music_album_info` WRITE;
/*!40000 ALTER TABLE `music_album_info` DISABLE KEYS */;
INSERT INTO `music_album_info` VALUES ('Album_180722131840','i1.jpg','Hybrid Theory',2000),('Album_220722151109','i2.jpg','Minutes to Midnight',2007),('Album_220722151206','i3.jpg','Living Things',2012),('Album_220722151245','i4.jpg','Meteora',2003);
/*!40000 ALTER TABLE `music_album_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `music_info`
--

DROP TABLE IF EXISTS `music_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `music_info` (
  `music_id` varchar(255) NOT NULL,
  `admin_id` varchar(255) NOT NULL,
  `artist_name` varchar(255) DEFAULT NULL,
  `music_name` varchar(255) NOT NULL,
  `music_url` varchar(255) NOT NULL,
  `music_album_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`music_id`),
  UNIQUE KEY `UK_qd8ialglb1ylt2nldc72xts6j` (`music_name`),
  KEY `FK8lie8x64uan4pc25plgdilxp2` (`music_album_id`),
  CONSTRAINT `FK8lie8x64uan4pc25plgdilxp2` FOREIGN KEY (`music_album_id`) REFERENCES `music_album_info` (`music_album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_info`
--

LOCK TABLES `music_info` WRITE;
/*!40000 ALTER TABLE `music_info` DISABLE KEYS */;
INSERT INTO `music_info` VALUES ('Music_180722131955','rohit1988@bennington.music','Chester Bennington','In The End','eVTXPUF4Oz4','Album_180722131840'),('Music_220722151645','rohit1988@bennington.music','Chester Bennington, Mike Shinoda','Somewhere I Benlong','zsCD5XCu6CM','Album_220722151245'),('Music_220722151921','sankar1997@bennington.music','Linkin Park','Numb','kXYiU_JCYtU','Album_220722151245'),('Music_220722152411','sankar1997@bennington.music','Mike Shinoda','What I\'ve Done','8sgycukafqQ','Album_220722151109'),('Music_220722152520','rohit1988@bennington.music','Mike Shinoda','No More Sorrow','rW4uBvP2Dqc','Album_220722151109'),('Music_220722152643','sankar1997@bennington.music','Chester Bennington','One Step Closer','rW4uBvP2Dqc','Album_180722131840');
/*!40000 ALTER TABLE `music_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_info` (
  `user_email` varchar(255) NOT NULL,
  `user_isactive` bit(1) NOT NULL,
  `user_mobileno` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_role` varchar(255) NOT NULL,
  `user_age` int(11) NOT NULL,
  `user_gender` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES ('prabir1956@gmail.com',_binary '','8652301475','$2a$10$f2DjfzoXRVXfF9vkBaD83OzXBZ3U1WS/lbncrUExffKu9iRh/zy6O','ROLE_USER',56,'Male','Prabir Roy'),('rohit1988@bennington.music',_binary '','9632014587','$2a$10$CkaimivrD9ba8Q2wKJIC6uUmiXcbf85AP4frzmjkY1vpBqOejFk3m','ROLE_ADMIN',34,'Male','Rohit Roy'),('sankar1997@bennington.music',_binary '','7563021496','$2a$10$/T2KkJ/WdCLmtNThtXHZsuBd5FwradNTjDthF0WQxbP.HQu66wjvS','ROLE_ADMIN',25,'Male','Sankar Manna'),('sudeshna1999@gmail.com',_binary '','9803645210','$2a$10$Ii0MN.0KUYqRzCspqv..cOh3WoPM4MB7bl8uQlEjjbaZtfh.m9IWC','ROLE_USER',22,'Female','Sudehna Paul'),('sumona1987@bennington.music',_binary '','85632014730','$2a$10$weJK8PNWoTKdsm38QXw/Pe.qtUCEwWh2A5ObES57RcydDC5O9HZdG','ROLE_ADMIN',35,'Female','Sumona Dey');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_benningtone'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-25 11:59:


-- Use Following SQL quries for generating and viewing the database

-- create database db_benningtone;
-- use `db_benningtone`;
-- show tables;

-- select * from `user_info`;
-- select * from `music_album_info`;
-- select * from `music_info`;
-- select * from `like_dislike_info`;
-- select * from `comment_info`;
