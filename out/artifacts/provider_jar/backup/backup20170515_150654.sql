-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: provider
-- ------------------------------------------------------
-- Server version	5.5.42

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
-- Current Database: `provider`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `provider` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `provider`;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contract_num` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `contract_num_UNIQUE` (`contract_num`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Джон Дориант','+375 33 434-34-34','jd@gmail.com','улица Пушкина, Дом Колотушкина',12345678),(2,'Михаил Евреймов','+375 33 945-21-44','','',123456789),(3,'Геннадий Иванович Перс','+375 33 331-44-52','pers@gmail.com','Ул. Пушкина, д. 34',123456),(4,'Алина Михайлована Жумерина','+375 33 137-53-22','humerin@gmail.com','Ул. Пушкина, д. 76',223456),(5,'Игнат Кириллович Енекан','+375 33 956-22-54','emekan@gmail.com','Ул. Пушкина, д. 67',323456),(6,'Евгений Игнатович Мирин','+375 33 945-00-23','mirin@gmail.com','Ул. Пушкина, д. 65',423456),(7,'Александр Евгеньевич Туриц','+375 33 961-47-33','turic@gmail.com','Ул. Пушкина, д. 56',523456),(8,'Алексей Александрович Матрешкин','+375 33 123-75-32','matresh@gmail.com','Ул. Пушкина, д. 84',623456),(9,'Аксенчик Вадим Дмитриевич','+375 33 987-13-75','aksend@gmail.com','Ул. Моряка, д. 45',723456),(10,'Баёк Илья Александрович','+375 33 495-33-89','baek@gmail.com','Ул. Моряка, д. 39',823456),(11,'Владысик Максим Сергеевич','+375 33 127-55-39','vladiski@gmail.com','Ул. Моряка, д. 27',923456),(12,'Волчецкий Глеб Романович','+375 33 936-76-12','volcez@gmail.com','Ул. Моряка, д. 32',113456),(13,'Воропаев Артём Андреевич','+375 33 946-13-78','voropaed@gmail.com','Ул. Моряка, д. 34',133456),(14,'Гавриленко Андрей Сергеевич','+375 33 195-73-93','gavr@gmail.com','Ул. Моряка, д. 44',143456),(15,'Глебов Павел Олегович','+375 33 750-01-99','glepov@gmail.com','Ул. Лопецкая, д. 45',153456),(16,'Дробыш Андрей Викторович','+375 33 750-04-74','drobish@gmail.com','Ул. Лопецкая, д. 15',163456),(17,'Журавский Алексей Сергеевич','+375 33 750-15-81','guravis@gmail.com','Ул. Лопецкая, д. 14',173456),(18,'Ильин Станислав Александрович','+375 33 751-45-39','ilin@gmail.com','Ул. Лопецкая, д. 1',183456),(19,'Кабаков Даниил Михайлович','+375 33 751-52-09','kabakov@gmail.com','Ул. Лопецкая, д. 13',193456),(20,'Карнаух Илья Иванович','+375 29 752-64-54','karnauh@gmail.com','Ул. Лопецкая, д. 12',233456),(21,'Котиков Валентин Дмитриевич','+375 29 752-67-30','kotikov@gmail.com','Ул. Лопецкая, д. 11',333456),(22,'Краснер Евгений Львович','+375 29 752-61-71','krasner@gmail.com','Ул. Млечная, д. 49',433456),(23,'Крищик Александр Николаевич','+375 29 753-67-08','krishik@gmail.com','Ул. Млечная, д. 43',533456),(24,'Кудрявцев Дмитрий Игоревич','+375 29 753-55-61','kurd@gmail.com','Ул. Млечная, д. 41',633456),(25,'Латушкина Александра Дмитриевна','+375 29 754-19-43','latush@gmail.com','Ул. Млечная, д.48 ',733456),(26,'Лешик Максим Игоревич','+375 29 585-00-12','leshik@gmail.com','Ул. Млечная, д. 45',833456),(27,'Лихушко Дмитрий Николаевич','+375 29 585-00-39','lihush@gmail.com','Ул. Млечная, д. 44',933456),(28,'Миклашевский Даниил Валерьевич','+375 29 439-54-23','miklash@gmail.com','Ул. Млечная, д. 29',133356),(29,'Мисько Никита Владимирович','+375 29 346-23-67','misko@gmail.com','Ул. Млечная, д. 28',233356),(30,'Паращенко Ян Викторович','+375 29 653-64-34','parash@gmail.com','Ул. Подзорная, д. 27',333356),(31,'Русакевич Владислва Дмитриевич','+375 29 235-65-35','rsukas@gmail.com','Ул. Подзорная, д. 26',433356),(32,'Рымша Арсений Юрьевич','+375 29 135-65-32','rimsha@gmail.com','Ул. Подзорная, д. 25',533356),(33,'Савко Вероника Валерьевна','+375 29 654-36-78','saseko@gmail.com','Ул. Подзорная, д. 24',633356),(34,'Сащеко Валерий Александрович','+375 29 768-34-22','savko@gmail.com','Ул. Подзорная, д. 23',733356),(35,'Шейна Александр Андреевич','+375 29 567-34-77','seina@gmail.com','Ул. Подзорная, д. 22',833356),(36,'Янковский Дмитрий Олегович','+375 29 455-32-45','yankod@gmail.com','Ул. Подзорная, д. 17',933356),(37,'Горошков Денис Александрович','+375 25 676-78-34','goroh@gmail.com','Ул. Красная, д. 16',133336),(38,'Лукашенко Андрей Леонидович','+375 25 345-67-23','lukash@gmail.com','Ул. Красная, д. 15',233336),(39,'Лещеня Александр Владимирович','+375 25 123-56-99','lesh@gmail.com','Ул. Красная, д. 14',333336),(40,'Гугалев  Максим Сергеевич','+375 25 964-67-23','gugaga@gmail.com','Ул. Красная, д. 13',433336),(41,'Пинчуков Никита Александрович','+375 25 657-88-23','pincu@gmail.com','Ул. Красная, д. 11',533336),(42,'Кудрицкий Михаил Владимирович','+375 25 943-24-66','kudri13@gmail.com','Ул. Красная, д. 12',633336),(43,'Авхименко Антон Сергеевич','+375 25 958-34-11','avmisi94@gmail.com','Ул. Синяя, д. 5',733336),(44,'Русова Дарья Николаевна','+375 25 854-56-77','rus412@gmail.com','Ул. Синяя, д. 4',833336),(45,'Леонович Анастасия Сергеевна','+375 25 953-78-44','lone@gmail.com','Ул. Синяя, д. 3',933336),(46,'Черканов Артур Сергеевич','+375 25 457-53-34','cirik@gmail.com','Ул. Синяя, д. 2',144433),(47,'Воробьев Илья Юрьевич','+375 44 785-34-67','vorobey@gmail.com','Ул. Синяя, д. 1',155533),(48,'Самуйлик Антон Анатольевич','+375 44 234-56-73','smslus@gmail.com','Ул. Петра Жлобка, д. 17',166633),(49,'Ильющиц Андрей Викторович','+375 44 855-32-56','iliasdqw@gmail.com','Ул. Петра Жлобка, д. 123',177733),(50,'Макоед Иван Николаевич','+375 44 953-45-66','modkea@gmail.com','Ул. Петра Жлобка,, д. 43',188833),(51,'Дедкова Анастасия Вадимовна','+375 44 957-33-22','leldoka@gmail.com','Ул. Петра Жлобка,, д. 22',199933),(52,'Лосик Юрий Сергеевич','+375 44 964-77-43','losik@gmail.com','Ул. Петра Жлобка,, д. 13',993355);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `document_statuses`
--

DROP TABLE IF EXISTS `document_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_statuses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `value_confirm` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_statuses`
--

LOCK TABLES `document_statuses` WRITE;
/*!40000 ALTER TABLE `document_statuses` DISABLE KEYS */;
INSERT INTO `document_statuses` VALUES (1,'Новый'),(3,'Одобрен'),(4,'Отклонен'),(2,'Проходит проверку');
/*!40000 ALTER TABLE `document_statuses` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `statuses_upper_ins` BEFORE INSERT ON `document_statuses`
FOR EACH ROW
BEGIN
SET NEW.name = CONCAT(UCASE(LEFT(NEW.name, 1)),LCASE(SUBSTRING(NEW.name, 2)));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `statuses_upper_upd` BEFORE UPDATE ON `document_statuses`
FOR EACH ROW
BEGIN
SET NEW.name = CONCAT(UCASE(LEFT(NEW.name, 1)),LCASE(SUBSTRING(NEW.name, 2)));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `document_types`
--

DROP TABLE IF EXISTS `document_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `document_types` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `document_types`
--

LOCK TABLES `document_types` WRITE;
/*!40000 ALTER TABLE `document_types` DISABLE KEYS */;
INSERT INTO `document_types` VALUES (3,'Акт'),(4,'Докладная'),(5,'Объяснительная записка'),(1,'Приказ'),(2,'Протокол');
/*!40000 ALTER TABLE `document_types` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `types_upper_ins` BEFORE INSERT ON `document_types`
FOR EACH ROW
BEGIN
SET NEW.name = CONCAT(UCASE(LEFT(NEW.name, 1)),LCASE(SUBSTRING(NEW.name, 2)));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `types_upper_upd` BEFORE UPDATE ON `document_types`
FOR EACH ROW
BEGIN
SET NEW.name = CONCAT(UCASE(LEFT(NEW.name, 1)),LCASE(SUBSTRING(NEW.name, 2)));
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` varchar(255) NOT NULL,
  `date_of_creation` int(11) DEFAULT NULL,
  `client_id` int(11) NOT NULL,
  `managed_by` int(11) DEFAULT NULL,
  `managed_date` int(11) DEFAULT NULL,
  `managed_status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `client_key_idx` (`client_id`),
  KEY `manager_key_idx` (`managed_by`),
  KEY `type_key_idx` (`type`),
  KEY `managed_status_key_idx` (`managed_status`),
  CONSTRAINT `managed_status_key` FOREIGN KEY (`managed_status`) REFERENCES `document_statuses` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `client_key` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `manager_key` FOREIGN KEY (`managed_by`) REFERENCES `users` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `type_key` FOREIGN KEY (`type`) REFERENCES `document_types` (`id`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documents`
--

LOCK TABLES `documents` WRITE;
/*!40000 ALTER TABLE `documents` DISABLE KEYS */;
INSERT INTO `documents` VALUES (2,'test1',1494754634,1,1,1494754689,3,3),(4,'linker',1494758278,1,2,1494758278,1,3),(5,'https://vk.com/doc83646306_443635215',1494773695,3,1,1494773695,1,1),(6,'https://vk.com/doc83646306_443635215',1494773695,3,2,1494773695,2,2),(7,'https://vk.com/doc83646306_443635215',1494773695,2,3,1494773695,3,3),(8,'https://vk.com/doc83646306_443635215',1494773695,6,4,1494773695,4,4),(9,'https://vk.com/doc83646306_443635215',1494773695,7,5,1494773695,1,5),(10,'https://vk.com/doc83646306_443635215',1494773695,4,16,1494773695,1,1),(11,'https://vk.com/doc83646306_443635215',1494773695,3,7,1494773695,2,2),(12,'https://vk.com/doc83646306_443635215',1494773695,3,8,1494773695,3,3),(13,'https://vk.com/doc83646306_443635215',1494773695,7,9,1494773695,4,4),(14,'https://vk.com/doc83646306_443635215',1494773695,8,1,1494773695,1,5),(15,'https://vk.com/doc83646306_443635215',1494773695,10,2,1494773695,1,1),(16,'https://vk.com/doc83646306_443635215',1494773695,11,3,1494773695,2,2),(17,'https://vk.com/doc83646306_443635215',1494773695,43,4,1494773695,3,3),(18,'https://vk.com/doc83646306_443635215',1494773695,22,5,1494773695,4,4),(19,'https://vk.com/doc83646306_443635215',1494773695,32,6,1494773695,1,5),(20,'https://vk.com/doc83646306_443635215',1494773695,31,7,1494773695,1,1),(21,'https://vk.com/doc83646306_443635215',1494773695,33,8,1494773695,2,2),(22,'https://vk.com/doc83646306_443635215',1494773695,34,9,1494773695,3,3),(23,'https://vk.com/doc83646306_443635215',1494773695,35,1,1494773695,4,4),(24,'https://vk.com/doc83646306_443635215',1494773695,36,2,1494773695,1,5),(25,'https://vk.com/doc83646306_443635215',1494773695,37,3,1494773695,1,1),(26,'https://vk.com/doc83646306_443635215',1494773695,38,4,1494773695,2,2),(27,'https://vk.com/doc83646306_443635215',1494773695,39,5,1494773695,3,3),(28,'https://vk.com/doc83646306_443635215',1494773695,45,6,1494773695,4,4),(29,'https://vk.com/doc83646306_443635215',1494773695,3,7,1494773695,1,5),(30,'https://vk.com/doc83646306_443635215',1494773695,2,8,1494773695,1,1),(31,'https://vk.com/doc83646306_443635215',1494773695,39,9,1494773695,2,2),(32,'https://vk.com/doc83646306_443635215',1494773695,31,1,1494773695,3,3),(33,'https://vk.com/doc83646306_443635215',1494773695,1,2,1494773695,4,4),(34,'https://vk.com/doc83646306_443635215',1494773695,2,3,1494773695,1,5),(35,'https://vk.com/doc83646306_443635215',1494773695,3,4,1494773695,1,1),(36,'https://vk.com/doc83646306_443635215',1494773695,4,5,1494773695,2,2),(37,'https://vk.com/doc83646306_443635215',1494773695,5,6,1494773695,3,3),(38,'https://vk.com/doc83646306_443635215',1494773695,6,7,1494773695,4,4),(39,'https://vk.com/doc83646306_443635215',1494773695,7,8,1494773695,1,5),(40,'https://vk.com/doc83646306_443635215',1494773695,8,9,1494773695,1,1),(41,'https://vk.com/doc83646306_443635215',1494773695,9,1,1494773695,2,2),(42,'https://vk.com/doc83646306_443635215',1494773695,50,2,1494773695,3,3),(43,'https://vk.com/doc83646306_443635215',1494773695,49,3,1494773695,4,4),(44,'https://vk.com/doc83646306_443635215',1494773695,43,4,1494773695,1,5),(45,'https://vk.com/doc83646306_443635215',1494773695,33,5,1494773695,1,1),(46,'https://vk.com/doc83646306_443635215',1494773695,23,1,1494773695,2,2),(47,'https://vk.com/doc83646306_443635215',1494773695,13,6,1494773695,3,3),(48,'https://vk.com/doc83646306_443635215',1494773695,31,8,1494773695,4,4),(49,'https://vk.com/doc83646306_443635215',1494773695,32,8,1494773695,1,5),(50,'https://vk.com/doc83646306_443635215',1494773695,33,9,1494773695,1,1),(51,'https://vk.com/doc83646306_443635215',1494773695,34,3,1494773695,2,2),(52,'https://vk.com/doc83646306_443635215',1494773695,35,2,1494773695,3,3),(53,'https://vk.com/doc83646306_443635215',1494773695,36,2,1494773695,4,4),(54,'https://vk.com/doc83646306_443635215',1494773695,37,3,1494773695,1,5);
/*!40000 ALTER TABLE `documents` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `document_date_ins` BEFORE INSERT ON documents
FOR EACH ROW
BEGIN
SET NEW.date_of_creation = unix_timestamp(now());
SET NEW.managed_date = unix_timestamp(now());
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `managed_date_upd` BEFORE UPDATE ON documents
FOR EACH ROW
BEGIN
IF (OLD.managed_status <> NEW.managed_status) THEN
SET NEW.managed_date = UNIX_TIMESTAMP(NOW());
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `sudo` tinyint(1) unsigned zerofill DEFAULT '0',
  `deleted` tinyint(1) unsigned zerofill DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','63a9f0ea7bb98050796b649e85481845','Данил Фут','+375 25 937 10 86','twist1996@gmail.com',1,0),(2,'manager','25d55ad283aa400af464c76d713c07ad','Игорь Лавров','+375 33 134-56-77','pers@mail.ru',0,0),(3,'fandik','098f6bcd4621d373cade4e832627b4f6','Евгений Фандоров',NULL,NULL,0,1),(4,'hrom','25d55ad283aa400af464c76d713c07ad','Хромин Евгений Олегович','','hrom@gmail',0,0),(5,'novik','25d55ad283aa400af464c76d713c07ad','Новик Кирилл Владимирович','','novik@gmail',0,0),(6,'zalegai','25d55ad283aa400af464c76d713c07ad','Залегай Владислав Игоревич','','zalegi@gmail',0,0),(7,'krot','25d55ad283aa400af464c76d713c07ad','Крот Алексей Анатольевич','+375 33 873-44-56','krot@gmail',0,0),(8,'bosov','25d55ad283aa400af464c76d713c07ad','Босов Анатолий Владимирович','+375 33 873-33-44','bosov@gmail',0,0),(9,'litvenko','25d55ad283aa400af464c76d713c07ad','Литвинко Владислав Вячеславович','+375 33 954-34-66','litvenko@gmail',0,0),(10,'aleksbabe','25d55ad283aa400af464c76d713c07ad','Александрова Анастасия Викторовна','+375 33 645-32-45','alaksaefdae@gmail',0,0),(11,'savchenko','25d55ad283aa400af464c76d713c07ad','Савченко Виталий Александрович','+375 33 145-99-66','savfs1q@gmail',0,0),(12,'turenko','25d55ad283aa400af464c76d713c07ad','Туренок Татьяна Сергеевна','+375 33 345-68-56','turena34@gmail',0,0),(13,'bobkov','25d55ad283aa400af464c76d713c07ad','Бобков Игорь Игоревич','+375 33 954-54-33','boba134@gmail',0,0),(14,'ciz','25d55ad283aa400af464c76d713c07ad','Чиж Константин Андреевич','+375 33 784-45-33','cizdddd@gmail',0,0),(15,'delikator','25d55ad283aa400af464c76d713c07ad','Деликатор Татьяна Станиславовна','','duplickat@gmail',0,0),(16,'zubarik','25d55ad283aa400af464c76d713c07ad','Зубарик Андрей Юрьевич','','zibaerq@gmail',0,0),(17,'bogushev','25d55ad283aa400af464c76d713c07ad','Богушевич Андрей Михайлович','','boguiiis13@gmail',0,0),(18,'askisi','25d55ad283aa400af464c76d713c07ad','Аксинчиц Павел Владимирович','','lalskf02@gmail',0,0),(19,'anapcuk','25d55ad283aa400af464c76d713c07ad','Анапчук Кристина Дмитриевна','','anapa33@gmail',0,0),(20,'evlano','25d55ad283aa400af464c76d713c07ad','Евланов Алексей Андреевич','','evikaee13@gmail',0,0),(21,'dodik','25d55ad283aa400af464c76d713c07ad','Жодик Максим Сергеевич','','dodik@gmail',0,0),(22,'zimin','25d55ad283aa400af464c76d713c07ad','Зимин Никита Игоревич','','zimin@gmail',0,0),(23,'kapustin','25d55ad283aa400af464c76d713c07ad','Капустин Игорь Анатольевич','','kapusta@gmail',0,0),(24,'kovalev','25d55ad283aa400af464c76d713c07ad','Ковалёв Антон Сергеевич','','kavalek@gmail',0,0),(25,'kovalenko','25d55ad283aa400af464c76d713c07ad','Коваленко Юлия Дмитриевна','','kalerqw@gmail',0,0),(26,'kovrah','25d55ad283aa400af464c76d713c07ad','Коврах Янина Юрьевна','','kover@gmail',0,0),(27,'komarovic','25d55ad283aa400af464c76d713c07ad','Комарович Кирилл Андреевич','+375 44 456-44-88','komar@gmail',0,0),(28,'kostuk','25d55ad283aa400af464c76d713c07ad','Костюк Никита Александрович','+375 44 958-34-56','kostuk@gmail',0,0),(29,'kocik','25d55ad283aa400af464c76d713c07ad','Кочик Андрей Александрович','+375 44 886-34-22','kopcik@gmail',0,0),(30,'lushinki','25d55ad283aa400af464c76d713c07ad','Люшинский Игорь Андреевич','+375 44 987-56-32','lusha@gmail',0,0),(31,'matven','25d55ad283aa400af464c76d713c07ad','Матвиевич Егор Сергеевич','+375 44 495-75-34','matveedi@gmail',0,0),(32,'matush','25d55ad283aa400af464c76d713c07ad','Матюшкина Ирина Светославовна','+375 44 854-34-56','matushan@gmail',0,0),(33,'ponamarev','25d55ad283aa400af464c76d713c07ad','Пономарёв Иван Сергеевич','+375 44 456-76-34','ponomarev467@gmail',0,0),(34,'tushink','25d55ad283aa400af464c76d713c07ad','Тушинская Евгения Павловна','+375 44 566-77-88','tushno314@gmail',0,0),(35,'hotko','25d55ad283aa400af464c76d713c07ad','Хотько Егор Александрович','+375 44 564-34-56','hotkawanna@gmail',0,0),(36,'cibulko','25d55ad283aa400af464c76d713c07ad','Цыбулько Артём Александрович','','cibula94@gmail',0,0),(37,'shutov','25d55ad283aa400af464c76d713c07ad','Шутов Владислав Игоревич','','shutes13@gmail',0,0),(38,'avsievic','25d55ad283aa400af464c76d713c07ad','Авсиевич Владислав Александрович','','avsiieq@gmail',0,0),(39,'baslik','25d55ad283aa400af464c76d713c07ad','Баслык Ирина Сергеевна','','barsgqw@gmail',0,0),(40,'brusnev','25d55ad283aa400af464c76d713c07ad','Бруснёв Егор Михайлович','','bruseneq@gmail',0,0),(41,'dudarenko','25d55ad283aa400af464c76d713c07ad','Дударенко Кирилл Игоревич','','dudarenko@gmail',0,0),(42,'konoplic','25d55ad283aa400af464c76d713c07ad','Коноплич Кирилл Юрьевич','+375 44 245-67-43','konoplic@gmail',0,0),(43,'kokovic','25d55ad283aa400af464c76d713c07ad','Кохович Александр Васильевич','+375 44 943-23-66','kokohope@gmail',0,0),(44,'kravcova','25d55ad283aa400af464c76d713c07ad','Кравцова Анастасия Александровна','+375 44 942-34-63','krapeq@gmail',0,0),(45,'lisov','25d55ad283aa400af464c76d713c07ad','Лисовский Максим Алексеевич','+375 25 456-23-87','lisova@gmail',0,0),(46,'beerman','25d55ad283aa400af464c76d713c07ad','Пивченко Станислав Вадимович','+375 25 452-34-67','beera@gmail',0,0),(47,'razuman','25d55ad283aa400af464c76d713c07ad','Разумович Ирина Андреевна','+375 25 345-43-11','razumovic@gmail',0,0),(48,'rusian','25d55ad283aa400af464c76d713c07ad','Российцев Вячеслав Геннадьевич','+375 25 552-34-12','rossicev@gmail',0,0),(49,'sokolik','25d55ad283aa400af464c76d713c07ad','Соколова Вероника Юрьевна','','sokolova@gmail',0,0),(50,'titovik','25d55ad283aa400af464c76d713c07ad','Титович Максим Вечеславович','','titovic@gmail',0,0),(51,'shvadron','25d55ad283aa400af464c76d713c07ad','Швадронова Валерия Сергеевна','','shadrona@gmail',0,0),(52,'serbuk','25d55ad283aa400af464c76d713c07ad','Щербук Анастасия Владимировна','','scherbuk@gmail',0,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `user_pass_md5_ins` BEFORE INSERT ON `provider`.`users`
FOR EACH ROW
BEGIN
SET NEW.password = md5(NEW.password);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `user_pass_md5_upd` BEFORE UPDATE ON `provider`.`users`
FOR EACH ROW
BEGIN
IF NEW.password <> OLD.password THEN
SET NEW.password = md5(NEW.password);
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `user_deletion` BEFORE DELETE ON `provider`.`users`
FOR EACH ROW
BEGIN
UPDATE `provider`.`users` SET deleted = 1 WHERE id = OLD.id;
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Пользователь удалён.';
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping routines for database 'provider'
--
/*!50003 DROP FUNCTION IF EXISTS `divide` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `divide`(a INT, b INT) RETURNS double
BEGIN
RETURN a / b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `minus` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `minus`(a INT, b INT) RETURNS int(11)
BEGIN
RETURN a - b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `moding` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `moding`(a INT, b INT) RETURNS double
BEGIN
RETURN a % b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `multiple` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `multiple`(a INT, b INT) RETURNS int(11)
BEGIN
RETURN a * b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `plus` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `plus`(a INT, b INT) RETURNS int(11)
BEGIN
RETURN a + b;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_clients` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_clients`()
BEGIN
	SELECT count(*) as count FROM clients;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_documents` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_documents`()
BEGIN
	SELECT count(*) as count FROM documents;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_document_statuses` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_document_statuses`()
BEGIN
	SELECT count(*) as count FROM document_statuses;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_document_types` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_document_types`()
BEGIN
	SELECT count(*) as count FROM document_types;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `get_users` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `get_users`()
BEGIN
	SELECT count(*) as count FROM users;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-15 15:06:55
