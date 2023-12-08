<<<<<<< HEAD
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
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
-- Table structure for table `memoires`
--

DROP TABLE IF EXISTS `memoires`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memoires` (
  `idmemoires` int NOT NULL AUTO_INCREMENT,
  `idetudiant` int DEFAULT NULL,
  `idencadreur` int DEFAULT NULL,
  `year` int DEFAULT NULL,
  `title` varchar(256) DEFAULT NULL,
  `desc` varchar(256) DEFAULT NULL,
  `level` varchar(256) DEFAULT NULL,
  `resume` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`idmemoires`),
  KEY `etudiantid_idx` (`idetudiant`),
  KEY `encadreurid_idx` (`idencadreur`),
  CONSTRAINT `encadreurid` FOREIGN KEY (`idencadreur`) REFERENCES `users` (`idUSERS`),
  CONSTRAINT `etudiantid` FOREIGN KEY (`idetudiant`) REFERENCES `users` (`idUSERS`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memoires`
--

LOCK TABLES `memoires` WRITE;
/*!40000 ALTER TABLE `memoires` DISABLE KEYS */;
INSERT INTO `memoires` VALUES (1,1,2,2020,'lorem ipsum','lorem ipsum','l3','lorem ipsum'),(2,2,6,2023,'rand','rand','l3','rand'),(3,2,6,2023,'rand','rand','l3','rand'),(7,8,11,2011,'aaaa','aaaa','l3a','a');
/*!40000 ALTER TABLE `memoires` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-08 16:34:01
=======
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
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
-- Table structure for table `memoires`
--

DROP TABLE IF EXISTS `memoires`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `memoires` (
  `idmemoires` int NOT NULL AUTO_INCREMENT,
  `idetudiant` int DEFAULT NULL,
  `idencadreur` int DEFAULT NULL,
  `year` int DEFAULT NULL,
  `title` varchar(256) DEFAULT NULL,
  `desc` varchar(256) DEFAULT NULL,
  `level` varchar(256) DEFAULT NULL,
  `resume` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`idmemoires`),
  KEY `etudiantid_idx` (`idetudiant`),
  KEY `encadreurid_idx` (`idencadreur`),
  CONSTRAINT `encadreurid` FOREIGN KEY (`idencadreur`) REFERENCES `users` (`idUSERS`),
  CONSTRAINT `etudiantid` FOREIGN KEY (`idetudiant`) REFERENCES `users` (`idUSERS`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `memoires`
--

LOCK TABLES `memoires` WRITE;
/*!40000 ALTER TABLE `memoires` DISABLE KEYS */;
INSERT INTO `memoires` VALUES (1,1,2,2020,'lorem ipsum','lorem ipsum','l3','lorem ipsum'),(2,2,6,2023,'rand','rand','l3','rand'),(3,2,6,2023,'rand','rand','l3','rand'),(7,8,11,2011,'aaaa','aaaa','l3a','a');
/*!40000 ALTER TABLE `memoires` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-08 16:34:01
>>>>>>> 4a4b121ab5feabbb92db90c8b4a706422da921f4
