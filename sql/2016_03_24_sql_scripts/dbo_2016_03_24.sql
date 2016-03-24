-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: dbo
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
-- Table structure for table `appuser`
--

DROP TABLE IF EXISTS `appuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuser` (
  `appUserId` int(11) NOT NULL AUTO_INCREMENT,
  `appUserDetailsId` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varbinary(100) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`appUserId`),
  UNIQUE KEY `appUserId_UNIQUE` (`appUserId`),
  KEY `FK_AppUser_AppUserDetails` (`appUserDetailsId`),
  CONSTRAINT `FK_AppUser_AppUserDetails` FOREIGN KEY (`appUserDetailsId`) REFERENCES `appuserdetails` (`appUserDetailsId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuser`
--

LOCK TABLES `appuser` WRITE;
/*!40000 ALTER TABLE `appuser` DISABLE KEYS */;
INSERT INTO `appuser` VALUES (1,1,'username','username',1);
/*!40000 ALTER TABLE `appuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appuserdetails`
--

DROP TABLE IF EXISTS `appuserdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuserdetails` (
  `appUserDetailsId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `emailAddress` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`appUserDetailsId`),
  UNIQUE KEY `appUserDetailsId_UNIQUE` (`appUserDetailsId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuserdetails`
--

LOCK TABLES `appuserdetails` WRITE;
/*!40000 ALTER TABLE `appuserdetails` DISABLE KEYS */;
INSERT INTO `appuserdetails` VALUES (1,'Theo','Bad','theo@gmail.com');
/*!40000 ALTER TABLE `appuserdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appuserrole`
--

DROP TABLE IF EXISTS `appuserrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `appuserrole` (
  `appUserRoleId` int(11) NOT NULL AUTO_INCREMENT,
  `appUserId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`appUserRoleId`),
  UNIQUE KEY `appUserRoleId_UNIQUE` (`appUserRoleId`),
  KEY `FK_AppUserRole_AppUser` (`appUserId`),
  KEY `FK_AppUserRole_Role` (`roleId`),
  CONSTRAINT `FK_AppUserRole_AppUser` FOREIGN KEY (`appUserId`) REFERENCES `appuser` (`appUserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_AppUserRole_Role` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appuserrole`
--

LOCK TABLES `appuserrole` WRITE;
/*!40000 ALTER TABLE `appuserrole` DISABLE KEYS */;
INSERT INTO `appuserrole` VALUES (1,1,1);
/*!40000 ALTER TABLE `appuserrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `challenge` (
  `challengeId` int(11) NOT NULL AUTO_INCREMENT,
  `solution` longblob NOT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `solutionFilePath` varchar(1024) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `endDate` datetime NOT NULL,
  `numberOfLinesToCompare` int(11) DEFAULT '0',
  `isDeleted` bit(1) DEFAULT b'0',
  `codeOffNumber` int(11) NOT NULL,
  PRIMARY KEY (`challengeId`),
  UNIQUE KEY `challengeId_UNIQUE` (`challengeId`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge`
--

LOCK TABLES `challenge` WRITE;
/*!40000 ALTER TABLE `challenge` DISABLE KEYS */;
/*!40000 ALTER TABLE `challenge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entry`
--

DROP TABLE IF EXISTS `entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entry` (
  `entryId` int(11) NOT NULL AUTO_INCREMENT,
  `solution` longblob,
  `date` datetime NOT NULL,
  `personId` int(11) NOT NULL,
  `techId` int(11) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `challengeId` int(11) NOT NULL,
  `result` int(11) DEFAULT NULL,
  PRIMARY KEY (`entryId`),
  UNIQUE KEY `entryId_UNIQUE` (`entryId`),
  KEY `FK_Entry_Challenge` (`challengeId`),
  KEY `FK_Entry_Person` (`personId`),
  KEY `FK_Entry_Technology` (`techId`),
  CONSTRAINT `FK_Entry_Challenge` FOREIGN KEY (`challengeId`) REFERENCES `challenge` (`challengeId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Entry_Person` FOREIGN KEY (`personId`) REFERENCES `person` (`personId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Entry_Technology` FOREIGN KEY (`techId`) REFERENCES `technology` (`techId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=407 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entry`
--

LOCK TABLES `entry` WRITE;
/*!40000 ALTER TABLE `entry` DISABLE KEYS */;
/*!40000 ALTER TABLE `entry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `personId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `score` int(11) NOT NULL,
  `url` varchar(1024) DEFAULT NULL,
  `variance` int(11) NOT NULL,
  PRIMARY KEY (`personId`),
  UNIQUE KEY `personId_UNIQUE` (`personId`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=516 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_SIMPLE');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysdiagrams`
--

DROP TABLE IF EXISTS `sysdiagrams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysdiagrams` (
  `name` varchar(160) NOT NULL,
  `principal_id` int(11) NOT NULL,
  `diagram_id` int(11) NOT NULL AUTO_INCREMENT,
  `version` int(11) DEFAULT NULL,
  `definition` longblob,
  PRIMARY KEY (`diagram_id`),
  UNIQUE KEY `UK_principal_name` (`principal_id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysdiagrams`
--

LOCK TABLES `sysdiagrams` WRITE;
/*!40000 ALTER TABLE `sysdiagrams` DISABLE KEYS */;
/*!40000 ALTER TABLE `sysdiagrams` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `technology`
--

DROP TABLE IF EXISTS `technology`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `technology` (
  `techId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`techId`),
  UNIQUE KEY `techId_UNIQUE` (`techId`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `technology`
--

LOCK TABLES `technology` WRITE;
/*!40000 ALTER TABLE `technology` DISABLE KEYS */;
INSERT INTO `technology` VALUES (1,'JavaScript'),(2,'Java'),(3,'Ruby'),(4,'PHP'),(5,'Python'),(6,'Haskell'),(7,'C++'),(8,'C#'),(9,'C'),(10,'Clojure'),(11,'Objective-C'),(12,'Shell'),(13,'Perl'),(14,'Erlang'),(15,'Scala'),(16,'VimL'),(17,'VB .NET'),(18,'Lisp'),(19,'Go'),(20,'TCL'),(21,'Lua'),(22,'Bash'),(23,'R');
/*!40000 ALTER TABLE `technology` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-24 10:46:23
