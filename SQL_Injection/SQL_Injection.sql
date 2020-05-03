-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: SQL_Injection
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
-- Table structure for table `user_details`
--

DROP TABLE IF EXISTS `user_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_details` (
  `userid` varchar(16) NOT NULL,
  `password` varchar(16) DEFAULT NULL,
  `fname` varchar(100) DEFAULT NULL,
  `lname` varchar(100) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `dtob` date DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `user_rating` varchar(20) DEFAULT NULL,
  `emailid` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_details`
--

LOCK TABLES `user_details` WRITE;
/*!40000 ALTER TABLE `user_details` DISABLE KEYS */;
INSERT INTO `user_details` VALUES ('bakersimon','teafav@100','Simon','Baker','M','1985-08-28','AUSTRALIA','100','bakersimon@gmail.com'),('Lisbon90','TT@9998','Teresa','Lisbon','F','1988-09-28','USA','95',' tlisbon@gmail.com'),('MansiS98','MS@98','Mansi','Shinde','F','1998-12-14','INDIA','70','mansishinde@gmail.com'),('meher028','meher.gj@1996','Meher','Gujral','F','1996-05-28','INDIA','90','Meher.Gujral@gmail.com'),('PJane15','pizza@90','Patrick','Jane','M','1989-07-09','AUSTRALIA','55','patrickj0709@gmail.com'),('rnanda99','footballmessy','Rohan','Nanda','M','1999-11-14','INDIA','80','r.nanda@gmail.com'),('SH@9','we_1234','Suzie','Heth','F','1991-09-23','USA','60','heth23s@gmail.com'),('Shubz_14','meandcricket@98','Shubham','Agarwal','M','1998-02-14','INDIA','40','Shub14agarwal@gmail.com'),('Shugar9805','Shugar@Nis_Lab','Shubhangi','Gaherwar','F','1998-05-08','INDIA','76','Shubhgaherwar@gmail.com'),('smithj48','jsm@48','John','Smith','M','1992-08-19','USA','75','jsmith@yahoo.com'),('WayneR@12','CBI@94','Wayne','Rigsby','M','1990-12-12','GERMANY','55','waynerigsby@yahoo.com');
/*!40000 ALTER TABLE `user_details` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-26 14:43:58
