-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: acmeplexdb
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `showings`
--

DROP TABLE IF EXISTS `showings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `showings` (
  `ShowingID` varchar(20) NOT NULL,
  `MovieTitle` varchar(50) NOT NULL,
  `TheatreName` varchar(15) NOT NULL,
  `RoomNum` int NOT NULL,
  `ShowingTime` varchar(30) NOT NULL,
  `SeatMapID` varchar(15) NOT NULL,
  PRIMARY KEY (`ShowingID`),
  KEY `MovieTitle` (`MovieTitle`),
  KEY `TheatreName` (`TheatreName`),
  KEY `SeatMapID` (`SeatMapID`),
  CONSTRAINT `showings_ibfk_1` FOREIGN KEY (`MovieTitle`) REFERENCES `movies` (`MovieTitle`),
  CONSTRAINT `showings_ibfk_2` FOREIGN KEY (`TheatreName`) REFERENCES `theatres` (`TheatreName`),
  CONSTRAINT `showings_ibfk_3` FOREIGN KEY (`SeatMapID`) REFERENCES `seatmaps` (`SeatMapID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `showings`
--

LOCK TABLES `showings` WRITE;
/*!40000 ALTER TABLE `showings` DISABLE KEYS */;
INSERT INTO `showings` VALUES ('00','Bonhoeffer: Pastor. Spy. Assassin.','West Valleys',1,'2024-11-30 12:00:00','00'),('01','Bonhoeffer: Pastor. Spy. Assassin.','West Valleys',1,'2024-12-01 12:00:00','01'),('02','Red One','West Valleys',2,'2024-11-27 12:00:00','02'),('03','Red One','West Valleys',2,'2024-11-27 17:00:00','03'),('04','Red One','West Valleys',3,'2024-11-27 14:00:00','04'),('05','Red One','West Valleys',3,'2024-11-27 19:00:00','05');
/*!40000 ALTER TABLE `showings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-27 11:55:47
