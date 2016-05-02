CREATE DATABASE  IF NOT EXISTS `mantenimientodb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mantenimientodb`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: mantenimientodb
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `id_area` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_area` varchar(45) NOT NULL COMMENT 'Nombre de las areas',
  PRIMARY KEY (`id_area`),
  UNIQUE KEY `nombre_area_UNIQUE` (`nombre_area`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='Tabla con las areas de PANAM';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (5,'ALMACEN DE CLINKER'),(1,'CALCINACION'),(3,'EMPAQUE Y DESPACHO'),(4,'LABORATORIO'),(2,'MOLIENDA  DE CEMENTO'),(7,'PLANTA DE TRATAMIENTO'),(6,'SUBESTACIONES');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `causa`
--

DROP TABLE IF EXISTS `causa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `causa` (
  `id_causa` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_causa` varchar(255) NOT NULL,
  `descripcion_adicional` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_causa`),
  KEY `fk_op_causa_idx` (`id_usuario`),
  CONSTRAINT `fk_usuario_causa` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=252 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `causa`
--

LOCK TABLES `causa` WRITE;
/*!40000 ALTER TABLE `causa` DISABLE KEYS */;
INSERT INTO `causa` VALUES (1,'Paro por seccion anterior (Ajeno)',NULL,1),(2,'Paro por seccion posterior (Ajeno)',NULL,1),(3,'Paro por protecciones electricas',NULL,1),(4,'Paro por protecciones electronicas',NULL,1),(5,'Paro por soltura mecanica (Mec)',NULL,1),(6,'Paro por rotura de piezas (Mec)',NULL,1),(7,'Paro por desgaste excesivo (Mec)',NULL,1),(8,'Paro por fallas de lubricacion (Lubr)',NULL,1),(9,'Paro por falla de cadena, acople o correa (Mec)',NULL,1),(10,'Paro por falla de reductor (Mec)',NULL,1),(11,'Paro por falla de eje (Mec)',NULL,1),(12,'Paro por calentamiento excesivo (Mec)',NULL,1),(13,'Paro por falla en rodamientos o chumaceras (Mec)',NULL,1),(14,'Paro por fatiga de materiales (Mec)',NULL,1),(15,'Paro por falla de sistemas de refrigeracion (Mec)',NULL,1),(16,'Paro por descarrilamiento (Mec)',NULL,1),(17,'Paro por vibraciones altas (Mec)',NULL,1),(18,'Paro por vibraciones altas (Pro)',NULL,1),(19,'Paro por falla de presion (Mec)',NULL,1),(20,'Paro por causa desconocida',NULL,1),(21,'Paro por falla sistema electrico externo (Ajeno)',NULL,1),(22,'Paro por falla en subestacion electrica principal (Elc)',NULL,1),(23,'Paro por falla en subestacion electrica (Elc)',NULL,1),(24,'Paro por falla en acometida electrica (Elc)',NULL,1),(25,'Paro por falla de arrancador electrico (Elc)',NULL,1),(26,'Paro por falla en paneles de distribucion de potencia MCC (Elc)',NULL,1),(27,'Paro por falla en paneles de distribucion electrica (Elc)',NULL,1),(28,'Paro por falla de motor (Elc)',NULL,1),(29,'Paro por falla en instrumentacion (Eln)',NULL,1),(30,'Paro por falla acometida de instrumentacion (Eln)',NULL,1),(31,'Paro por falla sistema de control (Eln)',NULL,1),(32,'Paro por falsas maniobras',NULL,1),(33,'Paro por atasque de materiales (Prd)',NULL,1),(34,'Paro por falta de materiales (Prd)',NULL,1),(35,'Paro por materiales extranos o encondiciones sub normales (Prd)',NULL,1),(36,'Paro por falla de operacion (Prd)',NULL,1),(37,'Paro por falla de combustible (Prd)',NULL,1),(38,'Paro por falla humana',NULL,1),(39,'Paro por sobrecarga de maquina (Prd)',NULL,1),(40,'Presencia Metales en Alimentacion',NULL,1),(41,'Paro por falla de eje (Mec)',NULL,4),(42,'Paro por causa desconocida',NULL,4),(47,'Paro por protecciones electricas',NULL,4),(48,'Paro por falla de motor (Elc)',NULL,4),(49,'Paro por rotura de piezas (Mec)',NULL,4),(50,'Paro por protecciones electronicas',NULL,4),(51,'Paro por protecciones electronicas',NULL,4),(52,'Paro por protecciones electricas',NULL,4),(58,'Paro por protecciones electronicas',NULL,4),(59,'Paro por vibraciones altas (Pro)',NULL,4),(61,'Paro por rotura de piezas (Mec)',NULL,4),(62,'Paro por causa desconocida',NULL,4),(63,'Paro por protecciones electronicas',NULL,4),(64,'Paro por protecciones electronicas',NULL,4),(65,'Paro Programado',NULL,1),(66,'Otro',NULL,1),(67,'Paro por fallas de lubricacion (Lubr)',NULL,4),(68,'Paro por protecciones electronicas',NULL,4),(69,'Paro por atasque de materiales (Prd)',NULL,4),(81,'Paro por vibraciones altas (Mec)',NULL,1),(82,'Paro por falla en paneles de distribucion electrica (Elc)',NULL,1),(83,'Paro por soltura mecanica (Mec)',NULL,26),(86,'Paro por materiales extranos o encondiciones sub normales (Prd)',NULL,4),(88,'Paro por atasque de materiales (Prd)',NULL,24),(89,'Otro','Salio el motor principal por alarma \'system fault\'',4),(90,'Otro','falta de material',26),(91,'Otro','alto consumo de la trituradora por material humedo',26),(92,'Otro','alto consumo de la trituradora por material humedo',26),(93,'Otro','tapon del shuter 211.07',26),(94,'Paro por protecciones electricas',NULL,26),(95,'Otro','se tapo el shuter,material muy humedo',26),(96,'Paro Programado',NULL,26),(97,'Paro por falta de materiales (Prd)',NULL,26),(98,'Otro','alto consumo de la trituradora por material humedo',26),(99,'Otro','alto consumo de la trituradora material humedo',26),(100,'Otro','alto consumo de la trituradora por material humedo',26),(101,'Otro','alto consumo de la trituradora',26),(102,'Otro','tapon de shuter de en la 211.07',26),(103,'Otro','alto consumo de la trituradora por material humedo',26),(104,'Otro','tape en el shuter 211-07',26),(107,'Otro','tape en el shuter ',26),(108,'Otro','tape en el shuter',26),(109,'Paro Programado',NULL,26),(110,'Otro','limpieza de tolva de descarga por material adherido a las paredes',26),(111,'Otro','se formo un anillo de material en la descarga y ocaciono el tape.',26),(112,'Otro','tape en el shuter',26),(113,'Otro','tape en el shuter',26),(115,'Paro por atasque de materiales (Prd)',NULL,24),(116,'Paro por atasque de materiales (Prd)',NULL,24),(118,'Paro por seccion anterior (Ajeno)',NULL,24),(119,'Paro Programado',NULL,24),(120,'Paro por protecciones electronicas',NULL,24),(121,'Paro por vibraciones altas (Mec)',NULL,24),(122,'Paro por vibraciones altas (Mec)',NULL,24),(123,'Paro por vibraciones altas (Mec)',NULL,24),(124,'Paro por vibraciones altas (Pro)',NULL,24),(126,'Paro por atasque de materiales (Prd)',NULL,24),(127,'Paro por vibraciones altas (Pro)',NULL,24),(128,'Paro por atasque de materiales (Prd)',NULL,24),(129,'Paro por fallas de lubricacion (Lubr)',NULL,4),(130,'Paro por atasque de materiales (Prd)',NULL,24),(131,'Paro por protecciones electricas',NULL,24),(132,'Paro por vibraciones altas (Pro)',NULL,24),(134,'Paro por atasque de materiales (Prd)',NULL,24),(139,'Paro por atasque de materiales (Prd)',NULL,24),(141,'Paro por vibraciones altas (Mec)',NULL,24),(143,'Paro por vibraciones altas (Mec)',NULL,24),(144,'Paro por atasque de materiales (Prd)',NULL,24),(145,'Paro por vibraciones altas (Mec)',NULL,24),(146,'Paro por vibraciones altas (Mec)',NULL,24),(147,'Paro por vibraciones altas (Mec)',NULL,24),(148,'Paro por vibraciones altas (Mec)',NULL,24),(149,'Paro Programado',NULL,24),(150,'Paro Programado',NULL,24),(152,'Paro Programado','',24),(153,'Otro','Salio el principal por alarma de grasa de los rolos.',4),(154,'Paro Programado','',24),(155,'Paro Programado','',24),(157,'Paro por vibraciones altas (Pro)','tuvimos varios paros del molino de crudo por\nvibraciones , y el ultimo paro fue por causas del sistema hidraulico',24),(158,'Paro por soltura mecanica (Mec)','Se partio una de las bases que mueven las parrillas.',24),(159,'Paro por fatiga de materiales (Mec)','limpieza',24),(160,'Paro por fatiga de materiales (Mec)','taponamiento de material',24),(163,'Paro por fatiga de materiales (Mec)','taponamiento de material',24),(164,'Paro Programado','Paramos antes de las 5 horas para limpieza de chutter.',24),(165,'Paro por atasque de materiales (Prd)','tapon en el chute de bajo de rotatoria',24),(166,'Paro por fatiga de materiales (Mec)','limpieza',24),(167,'Paro por fatiga de materiales (Mec)','limpieza',24),(168,'Paro por fatiga de materiales (Mec)','limpieza de chuter',24),(170,'Otro','no tiene actuador',32),(171,'Paro por protecciones electronicas','esta fuera de servicios',32),(172,'Paro Programado','limpiar chuter',24),(173,'Paro por fatiga de materiales (Mec)','limpieza',24),(174,'Paro por fatiga de materiales (Mec)','limpieza',24),(175,'Paro por atasque de materiales (Prd)','Se encostro la tolvita del apron feeder y aprovechamos para limpiar el chuter de la val. rotatoria',24),(176,'Paro Programado','Se paro para inspeccionar y limpiar el chutter del molino y la rotatoria',24),(177,'Paro Programado','limpiar chuter',24),(179,'Paro por fatiga de materiales (Mec)','limpieza',24),(180,'Paro por atasque de materiales (Prd)','se encostro la tolva del apron feeder de caliza y aprovechamos para limpiar el chuter de la val. rotatoria',24),(181,'Paro Programado','limpiar chute',24),(182,'Paro por atasque de materiales (Prd)','Por costra.',24),(183,'Paro por causa desconocida','Causa desconocida.',24),(184,'Otro','mangueras del modulo 3 y 5 rotas\n\n\n\nmangueras rota modulos 3 y 5',32),(185,'Paro Programado','limpiar chute',24),(186,'Paro por falta de materiales (Prd)','los camiones no llegaban de la mina',26),(187,'Paro por protecciones electricas','el censor paro el equipo',26),(188,'Otro','el overlop de la 21-03 se calento',26),(189,'Paro Programado','FIN DE LA JORNADA',26),(191,'Paro Programado','Para limpiar el chute y rotatoria',24),(192,'Paro por vibraciones altas (Mec)','La rotatoria salio por falla de vibraciones',24),(193,'Paro Programado','Realizar limpiesa en chute y rotatoria',24),(194,'Paro por atasque de materiales (Prd)','Se desprendio una lamina de la tolva de caliza ',24),(196,'Paro por vibraciones altas (Mec)','Apron feeder de caliza tubo unos tornillos flojos',24),(197,'Paro Programado','Paro por silo lleno 31.26M',24),(198,'Paro Programado','Por Nivel del Silo Alto 30.12 M',24),(199,'Paro por sobrecarga de maquina (Prd)','Salio pór overload',24),(200,'Paro por vibraciones altas (Mec)','El ID fan salio porque le cayo una costra',24),(201,'Paro por fatiga de materiales (Mec)','limpiza',24),(202,'Paro por soltura mecanica (Mec)','la placa que sujeta el contactor del censor de rotacion  esta  suelto',32),(203,'Otro','chequeo de los equipos',26),(205,'Otro','correa fisurada',26),(206,'Otro','chequeo mecanico de la tolba y falta de meterial',26),(208,'Paro por rotura de piezas (Mec)','Se desprendio una de las laminas anti-aderentes, aprovechamos para limpiar el shuter de la val. rotatoria.',24),(210,'Paro por fatiga de materiales (Mec)','limpieza ',24),(211,'Paro por calentamiento excesivo (Mec)','alta temperatura en chumacera del motor de la banda.',26),(212,'Paro por protecciones electricas','Cuerda de seguridad, y se puso disponible inmediatamente.',24),(213,'Paro por atasque de materiales (Prd)','Encostre de material.',24),(214,'Paro por falta de materiales (Prd)','los camiones no  estaban llegando de la mina.',26),(215,'Paro por fatiga de materiales (Mec)','limpieza',24),(216,'Paro por falla de presion (Mec)','la bomba de vacio precenta una fuga de aire en la parte posterior',32),(218,'Otro','destape de lonas en la pila',26),(219,'Paro por atasque de materiales (Prd)','material cayó y activo cuerda del apilador',26),(220,'Paro por fatiga de materiales (Mec)','limpieza',24),(224,'Paro Programado','Paro programado para limpieza valv. rotatoria',24),(225,'Paro por fatiga de materiales (Mec)','limpieza profunda',24),(226,'Paro por atasque de materiales (Prd)','shuter tapado',26),(228,'Otro','calibracion del weighing feeder de caliza y reparacion del apron feeder  de caliza',24),(229,'Otro','La banda 311.17 desviada',24),(230,'Paro Programado','Alto Inventario en Silo de Clinker',5),(232,'Otro','Cama baja, desde que marca cama baja suben los rolos.',4),(235,'Paro accidental',NULL,1),(236,'Colisión apilador con pila material',NULL,1),(237,'Paro por lluvia (clima desfavorable)',NULL,1),(238,'Paro por inspección de equipo',NULL,1),(239,'Obstrucción en intercambiadores de calor',NULL,1),(240,'Falla Sistema hidráulico',NULL,1),(241,'Perdida de concreto Refractario ',NULL,1),(242,'Paro por Oxigeno >12% ',NULL,1),(243,'Paro por descalibración de equipo',NULL,1),(244,'Paro por fuga (Material, Aire, Aceite)',NULL,1),(245,'Paro por Silos/Tolvas Llenas',NULL,1),(246,'Paro por Alta Temperatura (Pro)',NULL,1),(247,'Paro por fallas de lubricacion (Lubr)','paro para chequeo de fuga de aceite en el tambor de la banda 513.01',4),(248,'Paro Programado','Calibracion de los weight feeders.',4),(249,'Otro','Limpieza en el apron de puzzolana.',4),(251,'Paro por falla de sistemas de refrigeracion (Mec)','rotura de la tuberia de la bomba que lleva agua al reductor del separador.',4);
/*!40000 ALTER TABLE `causa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplina`
--

DROP TABLE IF EXISTS `disciplina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disciplina` (
  `id_disciplina` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_disciplina` varchar(45) NOT NULL,
  PRIMARY KEY (`id_disciplina`),
  UNIQUE KEY `nombre_disciplina_UNIQUE` (`nombre_disciplina`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplina`
--

LOCK TABLES `disciplina` WRITE;
/*!40000 ALTER TABLE `disciplina` DISABLE KEYS */;
INSERT INTO `disciplina` VALUES (2,'Electrico'),(5,'Electronico'),(1,'Mecanico'),(4,'Proceso'),(3,'Produccion');
/*!40000 ALTER TABLE `disciplina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleado` (
  `id_Empleado` int(11) NOT NULL AUTO_INCREMENT,
  `codigo_empleado` int(11) unsigned NOT NULL,
  `nombre_empleado` varchar(20) NOT NULL,
  `apellido_empleado` varchar(20) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  PRIMARY KEY (`id_Empleado`),
  UNIQUE KEY `codigo_empleado_UNIQUE` (`codigo_empleado`),
  KEY `fk_Empleado_1_idx` (`codigo_empleado`),
  KEY `fk_Empleado_1_idx1` (`id_usuario`),
  CONSTRAINT `fk_usuario_empleado` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,800408,'Jesus','Rivera',2),(2,800104,'Alfredo','Amstrong',3),(3,0,'Admin','Admin',1),(4,1,'COP','MOLIENDA',4),(5,800401,'Jose','Pagan',5),(6,800058,'Juan Carlos','Fernandez',6),(7,800090,'Jose Luis','Lebron',7),(16,2,'COP','Clinkerizacion',24),(17,800035,'Antonia','Martinez',25),(18,3,'COP','Trituradora',26),(19,800145,'Rafael','Almonte',27),(20,800156,'Nelson','Segrera',28),(22,101175,'Edy','Gonzalez',30),(23,800353,'Felix','Ramon',31),(24,800075,'Abraham','Obispo',32),(25,800595,'Diana','Duran',33);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo` (
  `id_equipo` int(11) NOT NULL AUTO_INCREMENT,
  `cod_equipo` varchar(20) NOT NULL,
  `nombre_equipo` varchar(100) NOT NULL,
  `idSubArea` int(11) NOT NULL,
  PRIMARY KEY (`id_equipo`),
  UNIQUE KEY `cod_equipo_UNIQUE` (`cod_equipo`),
  KEY `fk_subarea_idx` (`idSubArea`),
  CONSTRAINT `fk_subarea` FOREIGN KEY (`idSubArea`) REFERENCES `sub_area` (`idsub_area`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=516 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,'211.01','TRANSPORTADOR DE PLACAS',1),(2,'211.02','TRITURADORA DE MARTILLOS',1),(3,'211.03','TRANSPORTADOR DE BANDA',1),(4,'211.04','FILTRO DE MANGAS',1),(5,'211.05','VENTILADOR',1),(6,'211.06','VALVULA MARIPOSA ELECTRICA',1),(7,'211.07','TRANSPORTADOR DE BANDA',1),(8,'211.08','FILTRO DE MANGAS',1),(9,'211.09','VENTILADOR',1),(10,'211.10','ACUMULADOR DE AIRE',1),(11,'211.11','VALVULA MARIPOSA MANUAL',1),(12,'211.12','GRUA DE DOS VIGAS',2),(13,'212.01','TRANSPORTADOR DE PLACAS',2),(14,'212.02','TRITURADORA DE RODILLOS',2),(15,'212.03','TRANSPORTADOR DE BANDA',2),(16,'212.04','TRANSPORTADOR DE PLACAS',2),(17,'212.05','FILTRO DE MANGAS',2),(18,'212.06','VENTILADOR',2),(19,'212.07','FILTRO DE MANGAS',2),(20,'212.08','PUENTE GRUA MANUAL',2),(21,'212.09','ACUMULADOR DE AIRE',2),(22,'212.10','VALVULA MARIPOSA MANUAL',2),(23,'213.01','APILADOR Y RECLAMADOR',1),(24,'213.02','TRANSPORTADOR DE BANDA',1),(25,'213.03','VALVULA DE AGUJAS',1),(26,'213.04','VENTILADOR',1),(27,'214.01','TRANSPORTADOR DE BANDA',2),(28,'214.02','APILADOR',2),(29,'214.03','RECLAMADOR',2),(30,'214.04','VALVULA DE AGUJAS',2),(31,'214.05','TRANSPORTADOR DE BANDA',2),(32,'214.06','TRANSPORTADOR DE BANDA',2),(33,'214.07','FILTRO DE MANGAS',2),(34,'214.08','VALVULA MANUAL',2),(35,'214.09','ACUMULADOR DE AIRE',2),(36,'311.01','TRANSPORTADOR DE BANDA MOVIL',3),(37,'311.02','FILTRO DE MANGAS',3),(38,'311.03','FILTRO DE MANGAS',3),(39,'311.04','VENTILADOR',3),(40,'311.05','VENTILADOR',3),(41,'311.06','VALVULA DE AGUJAS',3),(42,'311.07','TRANSPORTADOR DE PLACAS CALIZA',3),(43,'311.08','TRANSPORTADOR DE PLACAS ALTERNO',3),(44,'311.09','TRANSPORTADOR DE PLACAS ARCILLA',3),(45,'311.10','TRANSPORTADOR DE PLACAS ARENISCA',3),(46,'311.11','TRANS DE PLACAS HIERRO EN POLVO',3),(47,'311.12','ALIMENTADOR PESADOR CALIZA',3),(48,'311.13','ALIMENTADOR PESADOR ALTERNO',3),(49,'311.14','ALIMENTADOR PESADOR ARCILLA',3),(50,'311.15','ALIMENTADOR PESADOR ARENISCA',3),(51,'311.16','ALIMENTADOR PESADOR HIERRO POLVO',3),(52,'311.17','TRANSPORTADOR DE BANDA',3),(53,'311.18','SEPARADOR MAGNETICO',3),(54,'311.19','PUERTA INSPECCION',3),(55,'311.20','PUERTA INSPECCION',3),(56,'311.21a','VALVULA MARIPOSA ELECTRICA a',3),(57,'311.21b','VALVULA MARIPOSA ELECTRICA b',3),(58,'311.22','VALVULA MARIPOSA MANUAL',3),(59,'311.23','ACUMULADOR DE AIRE',3),(60,'312.01','VALVULA ROTATORIA MOLINO CRUDO',4),(61,'312.02','MOLINO VERTICAL DE CRUDO',4),(62,'312.02M','MOTOR PRINCIPAL MOLINO DE CRUDO M',4),(63,'312.02P','REDUCTOR MOLINO DE CRUDO P',4),(64,'312.02a','EST LUBRIC REDUCT MOLINO CRUDO a',4),(65,'312.02b','EST LUBRIC MOTOR P MOLINO CRUDO b',4),(66,'312.02c','EST LUBRIC HIDRA ROLOS MOLINO c',4),(67,'312.02d','EST LUBRIC RODAM ROLOS MOLINO d',4),(68,'312.02e','CLASIFICADOR DEL MOLINO DE CRUDO e',4),(69,'312.02f','VENT SELLOS AIRE MOLINO DE CRUDO f',4),(70,'312.03','DETECTOR DE METALES',4),(71,'312.04','VALVULA NEUMATICA DE TRES VIAS',4),(72,'312.05','VALVULA GUILLOTINA',4),(73,'312.06','TRANSPORTADOR DE BANDA',4),(74,'312.07','SEPARADOR MAGNETICO',4),(75,'312.08','ELEVADOR DE CANGILONES',4),(76,'312.09','CELDA DE CARGA',4),(77,'312.10','VALVULA DE AGUJAS',4),(78,'312.11','TRANSPORTADOR DE BANDA',4),(79,'312.12','DETECTOR DE METALES',4),(80,'312.13','VALVULA NEUMATICA DE TRES VIAS',4),(81,'312.14','CICLONES COLECTORES DE POLVO',4),(82,'312.15','COMPUERTA DESLIZANTE',4),(83,'312.16','ALIMENTADOR ROTATIVO',4),(84,'312.17','ALIMENTADOR ROTATIVO',4),(85,'312.18','AERODESLIZADOR',4),(86,'312.19','VENTILADOR',4),(87,'312.20','VENTILADOR',4),(88,'312.21','VENTILADOR',4),(89,'312.22','VENTI PRINCI DEL MOLINO DE CRUDO',4),(90,'312.23','DAMPER ELECTRICO DE PERSIANA',4),(91,'312.24','DAMPER ELECTRICO DE PERSIANA',4),(92,'312.25','DAMPER ELECTRICO DE PERSIANA',4),(93,'312.26','MUESTREADOR',4),(94,'312.27','FILTRO DE MANGAS',4),(95,'312.28','VENTILADOR',4),(96,'312.29','FILTRO DE MANGAS',4),(97,'312.30','ACUMULADOR DE AIRE',4),(98,'312.31','VALVULA MANUAL',4),(99,'312.32','GENERADOR DE GASES CALIENTES',4),(100,'312.33','DAMPER ELECTRICO',4),(101,'312.34','GRUA MANUAL TON',4),(102,'312.35','PUERTA INSPECCION',4),(103,'312.36','PUNTO DE OBSERVACION',4),(104,'313.01','VENTILADOR',4),(105,'313.02','TORRE ACONDICIONADORA',4),(106,'313.03','FILTRO DE MANGAS PRINCIPAL',4),(107,'313.04','VENTI PRINCIPAL DEL FILTRO',4),(108,'313.05','DAMPER ELECTRICO DE PERSIANA',4),(109,'313.06','DAMPER ELECTRICO DE MARIPOSA',4),(110,'313.07','DAMPER ELECTRICO DE PERSIANA',4),(111,'313.08','DAMPER ELECTRICO DE PERSIANA',4),(112,'313.09','DAMPER ELECTRICO DE MARIPOSA',4),(113,'313.10','CICLON COLECTOR DE POLVO',4),(114,'313.11','VENTILADOR',4),(115,'313.12','COMPUERTA DESLIZANTE',4),(116,'313.13','ALIMENTADOR ROTATIVO',4),(117,'313.14','TRANSPORTADOR DE CADENA',4),(118,'313.15','TRANSPORTADOR DE CADENA',4),(119,'313.16','TRANSPORTADOR DE CADENA',4),(120,'313.17','ELECTRO COMP HIDRA DESLIZANTE',4),(121,'313.18','POLIPASTO MANUAL T',4),(122,'314.01','ELEVADOR DE CANGILONES',5),(123,'314.02','AERODESLIZADOR',5),(124,'314.03','VENTILADOR',5),(125,'314.04','FILTRO DE MANGAS',5),(126,'314.05','VENTILADOR CENTRIFUGO',5),(127,'314.06','SISTEMA DE AIRE INTERIOR SILO',5),(128,'314.07','SOPLADOR DE LOBULOS',5),(129,'314.08','SOPLADOR DE LOBULOS',5),(130,'314.09','SOPLADOR DE LOBULOS',5),(131,'314.10','EQUIPO DE REDUCCION DE PRESION',5),(132,'314.11','PUERTA INSPECCION',5),(133,'314.12','VALVULA DE BALANCEO DE PRESION',5),(134,'314.13','CUBIERTA PARA MEDIDA',5),(135,'314.14','CUBIERTA INSPECCION',5),(136,'314.15','VALVULA DE MARIPOSA MANUAL',5),(137,'314.16','TUBERIAS DE AIREACION',5),(138,'411.01','PESADO CON SISTEMA DE AIREACION',5),(139,'411.02','SISTEMA DE TUBERIA DE AIRE PESADO',5),(140,'411.03','MEDIDOR DE FLUJO',5),(141,'411.04','AERODESLIZADOR',5),(142,'411.05','VENTILADOR',5),(143,'411.06','FILTRO DE MANGAS',5),(144,'411.07','VENTILADOR CENTRIFUGO',5),(145,'411.08','VENTILADOR AXIAL',5),(146,'411.09','ELEVADOR DE CANGILONES',5),(147,'411.10','VALVULA ELECTRICA DE TRES VIAS',5),(148,'411.11','VALVULA ELECTRICA DE TRES VIAS',5),(149,'411.12','AERODESLIZADOR',5),(150,'411.13','VENTILADOR',5),(151,'411.14','ESCLUSA DE AIRE ROTATIVA',5),(152,'411.15','ESCLUSA DE AIRE ROTATIVA',5),(153,'411.16','VALVULA NEUMATICA DE MARIPOSA',5),(154,'411.17','VALVULA NEUMATICA DE MARIPOSA',5),(155,'411.18','TUBERIAS DE AIREACION',5),(156,'411.19','MUESTREADOR',5),(157,'411.20','ACUMULADOR DE AIRE',5),(158,'411.21','TUBERIAS DE AIRE COMPRIMIDO',5),(159,'411.22','VALVULA CHECK',5),(160,'412.01','PRECALENTADOR Y PRECALCINADOR',6),(161,'412.02','ACUMULADOR DE AIRE',6),(162,'412.03','TUBERIAS Y PARTES',6),(163,'412.04','CANONES DE AIRE',6),(164,'412.05','VALVULA MARIPOSA ELECTRICA',6),(165,'412.06','JUNTA DE EXPANSION',6),(166,'412.07','JUNTA DE EXPANSION',6),(167,'412.08','JUNTA DE EXPANSION',6),(168,'413.01','HORNO',6),(169,'413.01P1','REDUCTOR PRINCIPAL DEL HORNO P',6),(170,'413.01P2','REDUCTOR AUXILIAR P',6),(171,'413.01M1','MOTOR PRINCIPAL HORNO M',6),(172,'413.01M2','MOTOR AUXILIAR M',6),(173,'413.01M3','GENERADOR M',6),(174,'413.01M4','CALENTADOR ELECTRICO M',6),(175,'413.01M5','CALENTADOR ELECTRICO M',6),(176,'413.01M6','RESISTENCIA ELECTRICA M',6),(177,'413.01M7','RESISTENCIA ELECTRICA M',6),(178,'413.01M8','RESISTENCIA ELECTRICA M',6),(179,'413.01B','ESTACION HIDRAULICA B',6),(180,'413.01C','BOMBA DE ACEITE PARA EL SELLO C',6),(181,'413.01D','COMPRESOR DE AIRE D',6),(182,'413.01E','EST DE LUBRICACION REDUCTOR E',6),(183,'413.02','VENTILADOR',6),(184,'413.03','VENTILADOR',6),(185,'413.04','VENTILADOR',6),(186,'413.05','VENTILADOR',6),(187,'413.06','VENTILADOR',6),(188,'413.07','VENTILADOR',6),(189,'413.08','VENTILADOR MOTOR PRINCIPAL',6),(190,'413.09','DUCTOS DE AIRE TERCIARIO',6),(191,'413.10','TUBERIA DE AIRE COMPRIMIDO',6),(192,'413.11','TUBERIA DE ACEITE',6),(193,'413.12','VENTILADOR CENTRIFUGO',6),(194,'413.13','VENTILADOR CENTRIFUGO',6),(195,'414.01','CAMPANA DEL HORNO',7),(196,'414.02','ENFRIADOR DE CLINKER',7),(197,'414.02A','ESTACION HIDRAULICA A',7),(198,'414.02B','TRITURADORA DE CLINKER B',7),(199,'414.02C','ESTACION DE LUBRICACION C',7),(200,'414.02D','CANONES DE AIRE D',7),(201,'414.02E','VALVULA ELECTRICA E',7),(202,'414.02FM','ENCENDEDOR FM',7),(203,'414.03A','VENTILADOR DE REFRIGERACION FGA A',7),(204,'414.03B','VENTILADOR DE REFRIGERACION FGB B',7),(205,'414.04A','VENTILADOR DE REFRIGERACION FGA A',7),(206,'414.04B','VENTILADOR DE REFRIGERACION FGB B',7),(207,'414.05A','VENTILADOR DE REFRIGERACION FGA A',7),(208,'414.05B','VENTILADOR DE REFRIGERACION FGB B',7),(209,'414.06','VENTILADOR DE REFRIGERACION F',7),(210,'414.07','VENTILADOR DE REFRIGERACION F',7),(211,'414.08','VENTILADOR DE REFRIGERACION F',7),(212,'414.09','VENTILADOR DE REFRIGERACION F',7),(213,'414.10','VENTILADOR DE REFRIGERACION F',7),(214,'414.11','VENTILADOR DE REFRIGERACION F',7),(215,'414.12','VALVULA ELECTRICA',7),(216,'414.13','VALVULA MARIPOSA MANUAL',7),(217,'414.14','ENFRIADOR DE AIRE',7),(218,'414.15','FILTRO DE MANGAS',7),(219,'414.16','TRANSPORTADOR DE CADENA',7),(220,'414.17','VENTILADOR',7),(221,'414.18','QUEMADOR',7),(222,'414.19','VENTILADOR',7),(223,'414.20','VALVULA ALTO RENDIMIENTO',7),(224,'414.21','SOPLADOR',7),(225,'414.22','VENTILADOR',7),(226,'414.23','VALVULA MARIPOSA ELECTRICA',7),(227,'414.24','VENTILADOR',7),(228,'414.25','VALVULA MANUAL',7),(229,'414.26','POLIPASTO ELECTRICO',7),(230,'414.27','POLIPASTO ELECTRICO',7),(231,'414.28','TUBERIAS',7),(232,'414.29','TUBERIAS AIRE COMPRIMIDO',7),(233,'414.30','JUNTA DE EXPANSION',7),(234,'416.01','FILTRO DE MANGAS',2),(235,'416.02','VENTILADOR',2),(236,'416.03','PUERTA INSPECCION',2),(237,'416.04','AGUJERO DE MEDICION',2),(238,'416.05','TOLVA',2),(239,'416.06','SOPLADOR DE LOBULOS',2),(240,'416.07','COMPUERTA DELIZANTE MANUAL',2),(241,'416.08','SISTEMA DE ALIMENT DE CENIZAS',2),(242,'416.09','AERODESLIZADOR',2),(243,'416.10','VENTILADOR',2),(244,'416.11','TUBERIA AIRE VENTILADOR',2),(245,'416.12','ACUMULADOR DE AIRE',2),(246,'416.13','TUBERIAS AIRE COMPRIMIDO',2),(247,'416.14','VALVULA MARIPOSA MANUAL',2),(248,'911A.01','COMPRESOR DE AIRE A',8),(249,'911A.02','COMPRESOR DE AIRE A',8),(250,'911A.03','COMPRESOR DE AIRE A',8),(251,'911A.04','COMPRESOR DE AIRE A',8),(252,'911A.05','ACUMULADOR DE AIRE A',8),(253,'911A.06','FILTRO SEPARADOR ACEITE A',8),(254,'911A.07','SECADOR DE AIRE A',8),(255,'911A.08','SECADOR DE AIRE A',8),(256,'911A.09','SECADOR DE AIRE A',8),(257,'911A.10','SECADOR DE AIRE A',8),(258,'911A.11','ACUMULADOR DE AIRE A',8),(259,'911A.12','VENTILADOR DE TECHO A',8),(260,'911A.13','VENTILADOR DE TECHO A',8),(261,'911A.14','VENTILADOR DE TECHO A',8),(262,'911A.15','POLIPASTO MANUAL T A',8),(263,'911A.16','VALVULA DE FRENO A',8),(264,'911A.17','VALVULA A',8),(265,'911A.18','VALVULA CHECK A',8),(266,'L12.01','RECLAMADOR L',9),(267,'L12.02','VALVULA DE AGUJAS L',9),(268,'L12.03','TRANSPORTADOR DE BANDA L',9),(269,'L12.04','TRANSPORTADOR DE BANDA L',9),(270,'L12.05','VALVULA HIDRAULICA DE TRES VIAS L',9),(271,'L12.06','TRANSPORTADOR DE BANDA L',9),(272,'L12.07','FILTRO DE MANGAS L',9),(273,'L12.08','FILTRO DE MANGAS L',9),(274,'L14.01','COMPUERTA ELECTRICA DE TRES VIAS L',9),(275,'L14.02','VALVULA DE AGUJAS L',9),(276,'L14.03','BANDA PESADORA L',9),(277,'L14.04','BANDA PESADORA L',9),(278,'L14.05','VALVULA L',9),(279,'L14.06','MOLINO DE CARBON L',9),(280,'L14.07','SEPARADOR L',9),(281,'L14.08','TRANSPORTADOR DE TORNILLO L',9),(282,'L14.09','VALVULA SELECTORA NEUMATICA L',9),(283,'L14.10','FILTRO DE MANGAS L',9),(284,'L14.11','VENTILADOR L',9),(285,'L14.12','TRANSPORTADOR DE TORNILLO L',9),(286,'L14.13','MUESTREADOR L',9),(287,'L14.14','VALVULA ELECTRICA L',9),(288,'L14.15','VALVULA ELECTRICA L',9),(289,'L14.16','POLIPASTO MANUAL T L',9),(290,'L14.17','APLICADOR DE BOLA L',9),(291,'L14.18','VALVULA DE ALIVIO L',9),(292,'L14.19','ASFIXIADOR DE CO L',9),(293,'L14.20','PLACA DE POLIETILENO L',9),(294,'L14.21','PUERTA INSPECCION L',9),(295,'L14.22','AGUJERO DE MEDICION L',9),(296,'L15.01','TRANSPORTADOR DE TORNILLO L',9),(297,'L15.02','TOLVA NO DE CARBON PULVERIZADO L',9),(298,'L15.03','TOLVA NO DE CARBON PULVERIZADO L',9),(299,'L15.04','CELDA DE CARGA L',9),(300,'L15.05','VALVULA DE SELLADO MANUAL L',9),(301,'L15.06','VALVULA DE SELLADO MANUAL L',9),(302,'L15.07','PESADOR L',9),(303,'L15.08','PESADOR L',9),(304,'L15.09','SOPLADOR DE LOBULOS L',9),(305,'L15.10','SOPLADOR DE LOBULOS L',9),(306,'L15.11','SOPLADOR DE LOBULOS L',9),(307,'L15.12','VALVULA DE ALIVIO L',9),(308,'L15.13','VALVULA MARIPOSA ELECTRICA L',9),(309,'L15.14','ACUMULADOR DE AIRE L',9),(310,'L15.15','FILTRO DE MANGAS L',9),(311,'L15.16','VALVULA MARIPOSA MANUAL L',9),(312,'414A.01','TRANSP METALICO DE CLINKER SZF A',7),(313,'414A.03','COLECT POLVO RECIBO DE CK A',7),(314,'414A.04','VENT DE TIRO COLECT POLVO A',7),(315,'414A.05','COMP VENTILADOR DE TIRO A',7),(316,'414A.07','TANQUE RED DE AIRE COMPRIMIDO A',7),(317,'415.01B','COMPUERTA DESC CLK B',10),(318,'415.01C','COMPUERTA DESC CLK C',10),(319,'415.01D','COMPUERTA DESC SCK D',10),(320,'415.01E','COMPUERTA DESC SCK E',10),(321,'415.01F','COMPUERTA DESC SCK F',10),(322,'415.01G','COMPUERTA DESC SCK G',10),(323,'415.01H','COMPUERTA DESC SCK H',10),(324,'415.01I','COMPUERTA DESC SCK I',10),(325,'415.01J','COMPUERTA DESC SCK J',10),(326,'415.01K','COMPUERTA DESC SCK K',10),(327,'415.01L','COMPUERTA DESC SCK L',10),(328,'415.01M','COMPUERTA DESC SCK',10),(329,'415.01N','COMPUERTA DESC SCK N',10),(330,'415.01O','COMPUERTA DESC SCK O',10),(331,'415.01P','COMPUERTA DESC SCK P',10),(332,'415.01Q','COMPUERTA DESC SCK Q',10),(333,'415.01R','COMPUERTA DESC SCK R',10),(334,'415.01S','COMPUERTA DESC SCK S',10),(335,'415.01T','COMPUERTA DESC SCK T',10),(336,'415.01U','COMPUERTA DESC SCK U',10),(337,'415.01V','COMPUERTA DESC SCK V',10),(338,'415.01W','COMPUERTA DESC SCK W',10),(339,'415.01X','COMPUERTA DESC SCK X',10),(340,'415.02','BANDA SILO CK',10),(341,'415.03','BANDA SILO CK',10),(342,'415.04','BANDA SILO CK',10),(343,'415.05','COLECTOR TECHO SILO CLK',10),(344,'415.06','VENTILADOR TECHO SILO CLK',10),(345,'415.07','COMPUERTA VENTILADOR TECHO SILO CLK',10),(346,'415.09','BANDA COLECTORA SILO CLK',10),(347,'415.10','BANDA A TOLVA CLK',10),(348,'415.11','VALVULA DE TRES VIAS',10),(349,'415.12','COLECTOR TUNEL SILOCK',10),(350,'415.13','COLECTOR TUNEL SILOCK',10),(351,'415.14','COLECTOR TUNEL SILOCK',10),(352,'415.15','COLECTOR TRANSFERENCIA SILOCK',10),(353,'415.16','VENTILADOR DE TIRO',10),(354,'415.17','VENTILADOR DE TIRO',10),(355,'415.18','VENTILADOR DE TIRO',10),(356,'415.19','VENTILADOR DE TIRO',10),(357,'415.20','ACUMULADOR AIRE COMPRIMIDO',10),(358,'512.02','TRITURADORA ADITIVOS',11),(359,'512.03','ELEVADOR DE CANGILONES',11),(360,'512.04','BANDA DESCARGA ELEVADOR',11),(361,'512.05','BANDA ALIMENTACION TOLVA CALIZA',11),(362,'512.06','BANDA ALIMENTACION TOLVA YESO',11),(363,'512.07','COLECTOR TOLVAS DOSIFICADORA',11),(364,'512.08','COLECTOR TOLVA CLINKER',11),(365,'512.09','VENTILADOR COLECTOR DOSIFICADORAS',11),(366,'512.10','VENTILADOR COLECTOR TOLVA CLK',11),(367,'512.12','ALIMENTADOR METALICO DE CALIZA',11),(368,'512.13','ALIMENTADOR METALICO DE PUZZOLANA',11),(369,'512.14','ALIMENTADOR METALICO DE YESO',11),(370,'512.15','BANDA PESADORA DE CLINKER',11),(371,'512.16','BANDA PESADORA DE CALIZA',11),(372,'512.17','BANDA PESADORA DE PUZZOLANA',11),(373,'512.18','BANDA PESADORA DE YESO',11),(374,'512.19','BANDA ALIMENTACION TORRE MOLINO',11),(375,'512.20','COLECTOR POLVO',11),(376,'512.21','VENTILADOR TIRO COLECTOR POLVO',11),(377,'512.22','SEPARADOR MAGNETICO',11),(378,'512.23A','ACTUADOR ELECTRICO',11),(379,'512.23B','ACTUADOR ELECTRICO A',11),(380,'512.23C','ACTUADOR ELECTRICO B',11),(381,'512.25','COMPUERTA DE VIAS',11),(382,'512.25A','ACTUADOR ELECTRICO A',11),(383,'512.25B','ACTUADOR ELECTRICO B',11),(384,'512.28','ACUMULADOR AIRE COMPRIMIDO',11),(385,'513.01','BANDA ALIMENTACION MOLINO',12),(386,'513.02','DETECTOR DE METALES',12),(387,'513.03','VALVULA NEUMATICA DE TRES VIAS',12),(388,'513.04','MOLINO VERTICAL DE CEMENTO',12),(389,'513.04M','MOTOR PRINCIPAL MOLINO M',12),(390,'513.04P','REDUCTOR DEL MOLINO P',12),(391,'513.04B','ESTACION DE LUBRICACION MOTOR B',12),(392,'513.04C','ESTACION HIDRAULICA DE ROLOS C',12),(393,'513.04D','ESTACION LUBRICACION ROLOS MOLINO D',12),(394,'513.04E','CLASIFICADOR E',12),(395,'513.04F','VENTILADOR DEL SELLO DE AIRE F',12),(396,'513.04G','VALVULA ROTATORIAG',12),(397,'513.06','BANDA RECIRCULACION MOLINO',12),(398,'513.07','ROLOS PESADORES',12),(399,'513.08','SEPARADOR MAGNETICO',12),(400,'513.09','ELEVADOR DE CANGILONES',12),(401,'513.10','CELDA DE CARGA TOLVA RECHAZO',12),(402,'513.13','BANDA DESCARGA TOLVA RECHAZO',12),(403,'513.14','VALVULA NEUMATICA DE TRES VIAS',12),(404,'513.15','COLECTOR TORRE MOLINO',12),(405,'513.16','VENTILADOR COLECTOR TORRE MOLINO',12),(406,'513.17','COLECTOR DE POLVO PRINCIPAL',12),(407,'513.18','VENTILADOR PRINCIPAL (VTI)',12),(408,'513.18M','MOTOR VENTILADOR PRINCIPAL M',12),(409,'513.19','AERODESLIZADOR FILTRO PRINCIPAL',12),(410,'513.20','VENT AERODESLIZADOR FILTRO PRINCIPAL',12),(411,'513.21','TOMAMUESTRA DE CEMENTO',12),(412,'513.22','ACTUADOR SUCCION VTI',12),(413,'513.23','ACTUADOR DESCARGA VTI A CHIMENEA',12),(414,'513.24','GENERADOR DE GASES',12),(415,'513.25','ACTUADOR COMP SOPLADOR GENGASES',12),(416,'513.26','ACTUADOR COMPUERTA GENGASES',12),(417,'513.27','ACUMULADOR AIRE COMPRIMIDO',12),(418,'514.01','ELEVADOR ALIMENTACION SILO CEMENTO',13),(419,'514.02','AERODESLIZADOR ALIMENTACION SILOS',13),(420,'514.03','VENTILADOR ALIMENTACION SILOS',13),(421,'514.04','COLECTOR POLVO SILO',13),(422,'514.05','COLECTOR POLVO SILO',13),(423,'514.06','VENTILADOR COLECTOR POLVO SILO',13),(424,'514.07','VENTILADOR COLECTOR POLVO SILO',13),(425,'514.12','ACTUADOR DESCARGA SILO',13),(426,'514.13','ACTUADOR DESCARGA SILO',13),(427,'514.14','ACTUADOR DESCARGA SILO',13),(428,'514.15','ACTUADOR DESCARGA SILO',13),(429,'514.16','AERODESLIZADOR ALIMENTA EMPAQUE',13),(430,'514.17','AERODESLIZADOR ALIMENTA EMPAQUE',13),(431,'514.18','VENTILADOR AERODESLIZADOR',13),(432,'514.19','VENTILADOR AERODESLIZADOR',13),(433,'514.20','SOPLADOR DESCARGA SILO',13),(434,'514.21','SOPLADOR DESCARGA SILO',13),(435,'514.25','ACUMULADOR DE AIRE COMPRIMIDO',13),(436,'515.01','AERODESLIZADOR DESCARGA GRANEL S',14),(437,'515.02','AERODESLIZADOR DESCARGA GRANEL S',14),(438,'515.03','ACTUADOR DESCARGA GRANEL S',14),(439,'515.04','ACTUADOR DESCARGA GRANEL S',14),(440,'515.05','AERODESLIZADOR DESCARGA S',14),(441,'515.06','AERODESLIZADOR DESCARGA S',14),(442,'515.07','DESCARGA GRANEL DESDE SILO',14),(443,'515.08','DESCARGA GRANEL DESDE SILO',14),(444,'515.09','SOPLADOR DESCARGA GRANEL SILO',14),(445,'515.10','SOPLADOR DESCARGA GRANEL SILO',14),(446,'515.11','COLECTOR GRANEL SILO',14),(447,'515.12','COLECTOR GRANEL SILO',14),(448,'610.01','ELEVADOR ALIMENTACION EMPAQUE',15),(449,'610.02','ZARANDA EMPAQUE',15),(450,'610.03','TOLVA ALIMENTACION EMPAQUE',15),(451,'610.04','CELDA DE CARGA',15),(452,'610.05','COMPUERTA GUILLOTINA',15),(453,'610.07','EMPACADORA DE SACOS HAVER',15),(454,'610.08','COLOCADOR DE SACOS HAVER',15),(455,'610.09','BANDA DESCARGA DE SACOS',15),(456,'610.10','BANDA REDIRECCIONAMIENTO SACOS',15),(457,'610.14','TORNILLO SIN FIN',15),(458,'610.15','VENTILADOR LIMPIEZA DE SACOS',15),(459,'610.16','COLECTIOR DESCARGA EMPAQUE',15),(460,'610.17','VENTILADOR DE TIRO',15),(461,'610.18','BANDA TRANSPORTADORA',15),(462,'610.20','BANDA TRANSPORTADORA',15),(463,'610.21','CARGADOR DE SACOS A CAMION NO',15),(464,'610.22','CARGADOR DE SACOS A CAMION NO',15),(465,'610.21A','BANDA TRANSPORTADORA A',15),(466,'610.22A','BANDA TRANSPORTADORA A',15),(467,'610.21B','BANDA TRANSPORTADORA B',15),(468,'610.22B','BANDA TRANSPORTADORA B',15),(469,'610.23','ACUMULADOR DE AIRE COMPRIMIDO',15),(470,'911.01','COMPRESOR DE AIRE NO',16),(471,'911.02','COMPRESOR DE AIRE NO',16),(472,'911.03','COMPRESOR DE AIRE NO',16),(473,'911.06','SECADOR DE COMPRESOR NO',16),(474,'911.07','SECADOR DE COMPRESOR NO',16),(475,'911.08','SECADOR DE COMPRESOR NO',16),(476,'911.09','ACUMULADOR DE AIRE COMPRIMIDO',16),(477,'911.10','VENTILADOR TECHO NO',16),(478,'911.11','VENTILADOR TECHO NO',16),(479,'911.12','VENTILADOR TECHO NO',16),(480,'912.01','BOMBA DE AGUA NO',17),(481,'912.02','BOMBA DE AGUA NO',17),(482,'912.03','BOMBA DE AGUA NO',17),(483,'912.04','BOMBA DE AGUA DE POZOS NO',17),(484,'912.05','BOMBA DE AGUA DE POZOS NO',17),(485,'BAS1','BASCULA PESADORA DE CAMIONES',18),(486,'BAS2','BASCULA PESADORA DE CAMIONES',18),(487,'AP1','ANALIZADOR DE PARTICULAS LAB',19),(488,'MC1','MAQUINA DE COMPRESION LAB',19),(489,'MC2','MAQUINA DE COMPRESION LAB',19),(490,'MC3','MAQUINA DE COMPRESION LAB',19),(491,'HM','HORNO TIPO MUFLA LAB',19),(492,'H2','HORNO LAB',19),(493,'GC','GABINETE DE CURADO LAB',19),(494,'MB1','MOLINO DE BOLAS LAB',19),(495,'PVI','PULVERIZADOR DE IMPACTO LAB',19),(496,'PVD','PULVERIZADOR DE DISCO LAB',19),(497,'TRIT','TRITURADORA LAB',19),(498,'120.01','ECOTOLVA',20),(499,'120.02','BANDA TRANSPORTADORA',20),(500,'120.03','BANDA TRANSPORTADORA',20),(501,'120.04','BANDA TRANSPORTADORA',20),(502,'120.05','TRIPPER MOVIL',20),(503,'120.06','COLECTOR DE POLVO',20),(504,'120.07','COLECTOR DE POLVO',20),(505,'120.08','VENTILADOR',20),(506,'120.09','VENTILADOR',20),(507,'120.11','COMPRESOR DE AIRE',20),(508,'120.12','TANQUE RED DE AIRE COMPRIMIDO',20),(509,'SUB512','SUBESTACION 512',11),(510,'SUB513','SUBESTACION 513',21),(511,'SUB513A','SUBESTACION 513A',21),(512,'SUB610','SUBESTACION 610',22),(513,'SUB.PRIN','SUBESTACION PRINCIPAL',23),(514,'PTAN','PLANTA DE TRATAMIENTO',24),(515,'SCI','SISTEMA CONTRAINCENDIO',17);
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo_causa`
--

DROP TABLE IF EXISTS `equipo_causa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo_causa` (
  `id_equipo_causa` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_relacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_equipo` int(11) NOT NULL,
  `id_causa` int(11) NOT NULL,
  `estatus_equipo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_equipo_causa`),
  KEY `fk_equipo_rel_idx` (`id_equipo`),
  KEY `fk_causa_rel_idx` (`id_causa`),
  CONSTRAINT `fk_causa_rel` FOREIGN KEY (`id_causa`) REFERENCES `causa` (`id_causa`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipo_rel` FOREIGN KEY (`id_equipo`) REFERENCES `equipo` (`id_equipo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=latin1 COMMENT='Tabla que relaciona el equipo con la causa';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo_causa`
--

LOCK TABLES `equipo_causa` WRITE;
/*!40000 ALTER TABLE `equipo_causa` DISABLE KEYS */;
INSERT INTO `equipo_causa` VALUES (4,'2016-02-12 15:05:14',388,41,'Activo'),(5,'2016-02-13 07:17:10',414,42,'Activo'),(10,'2016-02-16 05:05:12',385,47,'Activo'),(11,'2016-02-16 10:20:18',388,48,'Activo'),(12,'2016-02-17 08:35:44',418,49,'Activo'),(13,'2016-02-18 01:06:14',388,50,'Activo'),(14,'2016-02-18 02:51:51',388,51,'Activo'),(15,'2016-02-18 08:51:33',388,52,'Activo'),(21,'2016-02-19 00:17:25',388,58,'Activo'),(22,'2016-02-19 06:23:19',389,59,'Activo'),(24,'2016-02-21 15:21:34',368,61,'Activo'),(25,'2016-02-22 09:07:22',388,62,'Activo'),(26,'2016-02-23 14:25:51',388,63,'Activo'),(27,'2016-02-23 15:06:30',388,64,'Activo'),(29,'2016-02-25 22:26:07',389,67,'Activo'),(30,'2016-02-25 22:28:09',388,68,'Activo'),(31,'2016-02-28 06:42:02',371,69,'Activo'),(46,'2016-03-02 11:34:21',2,83,'Activo'),(49,'2016-03-04 20:21:40',389,86,'Activo'),(51,'2016-03-05 12:54:10',62,88,'Activo'),(52,'2016-03-05 19:05:42',389,89,'Activo'),(53,'2016-03-07 13:10:04',1,90,'Activo'),(54,'2016-03-07 13:13:58',1,91,'Activo'),(55,'2016-03-07 13:16:54',1,92,'Activo'),(56,'2016-03-07 13:20:19',1,93,'Activo'),(57,'2016-03-07 13:26:04',23,94,'Activo'),(58,'2016-03-07 18:15:20',7,95,'Inactivo'),(59,'2016-03-07 19:33:52',26,96,'Activo'),(60,'2016-03-08 08:52:16',1,97,'Activo'),(61,'2016-03-08 09:32:21',1,98,'Activo'),(62,'2016-03-08 09:58:39',1,99,'Activo'),(63,'2016-03-08 10:30:04',1,100,'Activo'),(64,'2016-03-08 11:54:12',1,101,'Activo'),(65,'2016-03-08 11:55:43',1,102,'Activo'),(66,'2016-03-08 13:46:39',1,103,'Activo'),(67,'2016-03-08 16:01:10',7,104,'Activo'),(70,'2016-03-08 16:58:32',7,107,'Activo'),(71,'2016-03-08 17:53:15',7,108,'Activo'),(72,'2016-03-08 20:12:13',1,109,'Activo'),(73,'2016-03-09 12:45:17',1,110,'Activo'),(74,'2016-03-09 18:25:13',23,111,'Activo'),(75,'2016-03-09 18:27:36',23,112,'Activo'),(76,'2016-03-09 20:48:46',23,113,'Activo'),(78,'2016-03-09 21:05:23',60,115,'Activo'),(79,'2016-03-09 21:15:04',42,116,'Activo'),(81,'2016-03-10 12:49:02',304,118,'Activo'),(82,'2016-03-10 12:59:59',61,119,'Activo'),(83,'2016-03-10 13:14:05',23,120,'Activo'),(84,'2016-03-10 14:44:19',61,121,'Activo'),(85,'2016-03-10 14:49:22',61,122,'Activo'),(86,'2016-03-10 14:53:45',61,123,'Activo'),(87,'2016-03-11 06:40:22',62,124,'Activo'),(89,'2016-03-11 06:44:25',62,126,'Activo'),(90,'2016-03-11 07:00:22',62,127,'Activo'),(91,'2016-03-11 14:27:56',60,128,'Activo'),(92,'2016-03-11 14:33:43',388,129,'Activo'),(93,'2016-03-11 14:37:55',60,130,'Activo'),(94,'2016-03-11 14:42:25',283,131,'Activo'),(95,'2016-03-12 06:27:38',62,132,'Activo'),(97,'2016-03-12 06:31:42',61,134,'Activo'),(102,'2016-03-12 14:51:57',60,139,'Activo'),(104,'2016-03-12 14:53:11',62,141,'Activo'),(106,'2016-03-12 14:54:40',62,143,'Activo'),(107,'2016-03-13 06:45:41',62,144,'Activo'),(108,'2016-03-13 06:46:45',62,145,'Activo'),(109,'2016-03-13 13:37:01',61,146,'Activo'),(110,'2016-03-13 13:38:53',61,147,'Activo'),(111,'2016-03-13 13:40:46',61,148,'Activo'),(112,'2016-03-14 07:07:47',62,149,'Activo'),(113,'2016-03-14 07:08:32',62,150,'Activo'),(115,'2016-03-14 11:00:58',60,152,'Activo'),(116,'2016-03-14 14:49:35',389,153,'Activo'),(117,'2016-03-15 06:40:34',62,154,'Activo'),(118,'2016-03-15 06:46:29',62,155,'Activo'),(120,'2016-03-16 06:43:06',60,157,'Activo'),(121,'2016-03-17 06:09:47',196,158,'Activo'),(122,'2016-03-17 06:35:16',61,159,'Activo'),(123,'2016-03-17 06:41:01',61,160,'Activo'),(126,'2016-03-17 07:04:12',61,163,'Activo'),(127,'2016-03-17 07:33:28',61,164,'Activo'),(128,'2016-03-17 12:07:41',60,165,'Activo'),(129,'2016-03-18 06:33:17',60,166,'Activo'),(130,'2016-03-18 06:35:02',60,167,'Activo'),(131,'2016-03-18 06:44:48',42,168,'Activo'),(133,'2016-03-18 14:21:03',427,170,'Inactivo'),(134,'2016-03-18 15:26:18',431,171,'Activo'),(135,'2016-03-18 22:21:53',62,172,'Activo'),(136,'2016-03-19 06:11:16',60,173,'Activo'),(137,'2016-03-19 06:13:30',42,174,'Activo'),(138,'2016-03-19 14:58:21',42,175,'Activo'),(139,'2016-03-19 15:01:39',61,176,'Activo'),(140,'2016-03-19 22:39:18',62,177,'Activo'),(142,'2016-03-20 06:01:51',60,179,'Activo'),(143,'2016-03-20 14:50:03',42,180,'Activo'),(144,'2016-03-20 22:59:20',62,181,'Activo'),(145,'2016-03-21 14:37:18',104,182,'Activo'),(146,'2016-03-21 14:39:52',104,183,'Activo'),(147,'2016-03-21 19:48:44',453,184,'Activo'),(148,'2016-03-21 22:46:40',62,185,'Activo'),(149,'2016-03-22 17:32:52',1,186,'Activo'),(150,'2016-03-22 17:36:20',23,187,'Activo'),(151,'2016-03-22 17:43:16',3,188,'Activo'),(152,'2016-03-22 20:32:52',2,189,'Activo'),(154,'2016-03-24 06:58:57',60,191,'Activo'),(155,'2016-03-25 18:22:49',60,192,'Activo'),(156,'2016-03-25 18:25:54',60,193,'Activo'),(157,'2016-03-25 18:31:03',42,194,'Activo'),(159,'2016-03-26 16:02:47',42,196,'Activo'),(160,'2016-03-28 06:37:58',62,197,'Activo'),(161,'2016-03-28 06:39:49',62,198,'Activo'),(162,'2016-03-28 06:41:31',49,199,'Activo'),(163,'2016-03-28 06:47:32',104,200,'Activo'),(164,'2016-03-28 10:47:12',61,201,'Activo'),(165,'2016-03-29 09:15:45',448,202,'Inactivo'),(166,'2016-03-29 09:24:47',2,203,'Activo'),(168,'2016-03-29 10:03:37',2,205,'Activo'),(169,'2016-03-30 08:16:37',1,206,'Activo'),(171,'2016-03-30 08:50:36',42,208,'Activo'),(173,'2016-03-30 13:41:24',71,210,'Activo'),(174,'2016-03-30 19:13:52',7,211,'Activo'),(175,'2016-03-31 07:03:09',52,212,'Activo'),(176,'2016-03-31 07:06:06',61,213,'Activo'),(177,'2016-03-31 07:58:44',1,214,'Activo'),(178,'2016-03-31 13:27:42',61,215,'Activo'),(179,'2016-03-31 14:00:13',454,216,'Activo'),(181,'2016-04-01 05:47:28',1,218,'Activo'),(182,'2016-04-01 05:51:02',23,219,'Activo'),(183,'2016-04-01 07:05:39',61,220,'Activo'),(187,'2016-04-01 08:51:54',62,224,'Activo'),(188,'2016-04-01 11:20:35',42,225,'Activo'),(189,'2016-04-01 19:09:51',7,226,'Activo'),(191,'2016-04-03 13:19:00',47,228,'Activo'),(192,'2016-04-03 13:23:43',52,229,'Activo'),(193,'2016-04-21 13:27:04',168,230,'Activo'),(195,'2016-04-22 15:07:02',388,232,'Activo'),(198,'2016-04-26 22:48:20',385,247,'Activo'),(199,'2016-04-27 15:59:07',372,248,'Activo'),(200,'2016-04-28 12:07:27',367,249,'Activo'),(202,'2016-04-28 12:53:18',388,251,'Activo');
/*!40000 ALTER TABLE `equipo_causa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log`
--

DROP TABLE IF EXISTS `log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log` (
  `id_log` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `fecha_accion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Fecha en que se realizo la accion',
  `accion` varchar(255) NOT NULL COMMENT 'Que accion realizo el usuario(INSERT, UPDATE, DELETE)',
  `tabla` varchar(45) NOT NULL COMMENT 'Tabla en la que se realizo la accion',
  PRIMARY KEY (`id_log`),
  KEY `fk_autenticar_1_idx` (`id_usuario`),
  CONSTRAINT `fk_Log_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Guarda la bitacora de las acciones realizadas por los usuarios';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operacion_imputacion`
--

DROP TABLE IF EXISTS `operacion_imputacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operacion_imputacion` (
  `id_operacion_imputacion` int(11) NOT NULL AUTO_INCREMENT,
  `tiempo_inicio_paro` datetime NOT NULL,
  `tiempo_fin_paro` datetime DEFAULT NULL,
  `estatus_paro` varchar(15) NOT NULL,
  `id_disciplina` int(11) NOT NULL,
  `id_equipo_causa` int(11) NOT NULL,
  PRIMARY KEY (`id_operacion_imputacion`),
  KEY `discplina_idx` (`id_disciplina`),
  KEY `fk_equipo_causa_op_idx` (`id_equipo_causa`),
  CONSTRAINT `fk_disciplina_op` FOREIGN KEY (`id_disciplina`) REFERENCES `disciplina` (`id_disciplina`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_equipo_causa_op` FOREIGN KEY (`id_equipo_causa`) REFERENCES `equipo_causa` (`id_equipo_causa`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operacion_imputacion`
--

LOCK TABLES `operacion_imputacion` WRITE;
/*!40000 ALTER TABLE `operacion_imputacion` DISABLE KEYS */;
INSERT INTO `operacion_imputacion` VALUES (4,'2016-02-12 08:36:58','2016-02-12 10:32:20','Completado',3,4),(5,'2016-02-13 05:50:35','2016-02-13 06:10:08','Completado',2,5),(10,'2016-02-16 04:31:54','2016-02-16 04:55:01','Completado',5,10),(11,'2016-02-16 09:27:57','2016-02-16 09:33:17','Completado',2,11),(12,'2016-02-17 00:59:51','2016-02-17 08:30:38','Completado',1,12),(13,'2016-02-18 00:40:34','2016-02-18 00:49:44','Completado',5,13),(14,'2016-02-18 02:25:18','2016-02-18 02:32:55','Completado',5,14),(15,'2016-02-18 08:33:24','2016-02-18 08:49:47','Completado',2,15),(21,'2016-02-19 00:02:30','2016-02-19 00:08:36','Completado',5,21),(22,'2016-02-19 05:24:33','2016-02-19 05:28:03','Completado',3,22),(24,'2016-02-21 13:15:27','2016-02-21 13:51:55','Completado',1,24),(25,'2016-02-22 08:43:12','2016-02-22 08:52:24','Completado',2,25),(26,'2016-02-23 13:42:43','2016-02-23 13:56:53','Completado',5,26),(27,'2016-02-23 14:39:53','2016-02-23 15:02:54','Completado',5,27),(29,'2016-02-25 16:33:06','2016-02-25 16:49:18','Completado',1,29),(30,'2016-02-25 22:06:56','2016-02-25 22:12:14','Completado',5,30),(31,'2016-02-28 01:30:32','2016-02-28 02:40:59','Completado',4,31),(43,'2016-03-02 11:25:25','2016-03-02 11:33:21','Completado',1,46),(46,'2016-03-04 20:12:35','2016-03-04 20:20:27','Completado',2,49),(48,'2016-03-05 10:00:34','2016-03-05 12:50:51','Completado',3,51),(49,'2016-03-05 18:55:58','2016-03-05 19:04:34','Completado',2,52),(50,'2016-03-07 07:00:00','2016-03-07 09:25:17','Completado',3,53),(51,'2016-03-07 10:12:27','2016-03-07 10:18:30','Completado',3,54),(52,'2016-03-07 10:20:46','2016-03-07 10:27:01','Completado',3,55),(53,'2016-03-07 12:42:41','2016-03-07 12:47:54','Completado',3,56),(54,'2016-03-07 13:21:49','2016-03-07 13:21:52','Completado',3,57),(55,'2016-03-07 17:23:10','2016-03-07 18:00:09','Completado',4,58),(56,'2016-03-07 17:26:18','2016-03-07 23:00:00','Completado',3,59),(57,'2016-03-08 07:00:00','2016-03-08 08:45:55','Completado',3,60),(58,'2016-03-08 09:21:06','2016-03-08 09:27:16','Completado',3,61),(59,'2016-03-08 09:50:50','2016-03-08 09:55:35','Completado',3,62),(60,'2016-03-08 10:20:51','2016-03-08 10:23:58','Completado',3,63),(61,'2016-03-08 11:05:25','2016-03-08 11:08:35','Completado',3,64),(62,'2016-03-08 11:41:39','2016-03-08 11:47:02','Completado',3,65),(63,'2016-03-08 13:32:46','2016-03-08 13:36:52','Completado',3,66),(64,'2016-03-08 15:19:49','2016-03-08 15:58:36','Completado',4,67),(65,'2016-03-08 16:16:20','2016-03-08 16:58:22','Completado',4,70),(66,'2016-03-08 17:02:59','2016-03-08 17:52:13','Completado',4,71),(67,'2016-03-08 19:55:21','2016-03-08 23:00:00','Completado',3,72),(68,'2016-03-09 07:00:52','2016-03-09 09:26:00','Completado',3,73),(69,'2016-03-09 17:57:14','2016-03-09 18:22:00','Completado',4,74),(70,'2016-03-09 17:57:14','2016-03-09 18:26:48','Completado',4,75),(71,'2016-03-09 19:48:00','2016-03-09 20:01:31','Completado',4,76),(72,'2016-03-09 04:44:00','2016-03-09 08:22:00','Completado',3,78),(73,'2016-03-09 01:57:00','2016-03-09 04:31:00','Completado',3,79),(74,'2016-03-10 09:00:00','2016-03-10 10:30:46','Completado',1,81),(75,'2016-03-10 08:00:34','2016-03-10 10:30:00','Completado',3,82),(76,'2016-03-10 11:24:00','2016-03-10 11:55:55','Completado',2,83),(77,'2016-03-10 12:09:50','2016-03-10 12:26:00','Completado',3,84),(78,'2016-03-10 12:30:05','2016-03-10 13:00:00','Completado',3,85),(79,'2016-03-10 13:05:55','2016-03-10 14:38:50','Completado',3,86),(80,'2016-03-10 11:35:20','2016-03-12 20:39:04','Completado',3,87),(81,'2016-03-11 03:34:49','2016-03-11 04:22:09','Completado',3,89),(82,'2016-03-11 06:10:27','2016-03-11 06:50:49','Completado',3,90),(83,'2016-03-11 09:00:41','2016-03-11 11:30:53','Completado',3,91),(84,'2016-02-06 14:32:39','2016-03-11 14:15:48','Completado',2,92),(85,'2016-03-11 09:00:00','2016-03-11 11:30:00','Completado',3,93),(86,'2016-03-11 13:04:39','2016-03-11 13:36:06','Completado',2,94),(87,'2016-03-12 11:46:33','2016-03-12 12:50:53','Completado',3,95),(88,'2016-03-12 04:28:05','2016-03-12 05:48:42','Completado',3,97),(89,'2016-03-12 10:40:00','2016-03-12 14:25:00','Completado',3,102),(90,'2016-03-12 14:25:00','2016-03-12 14:26:00','Completado',3,104),(91,'2016-03-12 14:40:00','2016-03-12 14:41:00','Completado',3,106),(92,'2016-03-12 11:54:11','2016-03-13 02:00:27','Completado',3,107),(93,'2016-03-13 03:40:01','2016-03-13 05:30:11','Completado',3,108),(94,'2016-03-13 13:01:00','2016-03-13 13:21:00','Completado',3,109),(95,'2016-03-13 13:23:00','2016-03-13 13:43:00','Completado',3,110),(96,'2016-03-13 07:57:00','2016-03-13 10:16:00','Completado',3,111),(97,'2016-03-13 11:12:06','2016-03-14 12:04:29','Completado',3,112),(98,'2016-03-14 02:00:03','2016-03-14 03:30:12','Completado',3,113),(100,'2016-03-14 08:00:00','2016-03-14 10:30:00','Completado',3,115),(101,'2016-03-14 14:33:34','2016-03-14 14:47:23','Completado',2,116),(102,'2016-03-15 01:05:28','2016-03-15 02:37:39','Completado',3,117),(103,'2016-03-15 04:55:26','2016-03-15 05:30:01','Completado',3,118),(105,'2016-03-16 01:30:05','2016-03-16 03:30:42','Completado',3,120),(106,'2016-03-16 19:26:14','2016-03-17 01:40:15','Completado',1,121),(107,'2016-03-16 12:31:13','2016-03-17 01:23:28','Completado',3,122),(108,'2016-03-17 06:00:25',NULL,'Pendiente',3,123),(109,'2016-03-17 06:00:25','2016-03-17 07:00:03','Completado',3,126),(110,'2016-03-17 06:00:26','2016-03-17 06:55:35','Completado',3,127),(111,'2016-03-17 09:12:33','2016-03-17 11:54:34','Completado',3,128),(112,'2016-03-18 00:08:56','2016-03-18 01:47:18','Completado',3,129),(113,'2016-03-18 03:18:03','2016-03-18 04:00:17','Completado',3,130),(114,'2016-03-18 05:46:24',NULL,'Pendiente',3,131),(116,'2016-03-18 14:15:18',NULL,'Pendiente',1,133),(117,'2016-03-18 15:23:24','2016-04-15 17:48:30','Completado',2,134),(118,'2016-03-18 18:00:10','2016-03-18 19:05:27','Completado',3,135),(119,'2016-03-18 23:30:46','2016-03-19 01:23:57','Completado',3,136),(120,'2016-03-19 04:00:52','2016-03-19 04:28:13','Completado',3,137),(121,'2016-03-19 12:50:00','2016-03-19 15:05:00','Completado',4,138),(122,'2016-03-19 07:00:00','2016-03-19 09:23:00','Completado',3,139),(123,'2016-03-19 19:30:08','2016-03-19 20:20:17','Completado',3,140),(124,'2016-03-19 12:21:43','2016-03-20 02:00:26','Completado',3,142),(125,'2016-03-20 13:10:00','2016-03-20 14:43:45','Completado',3,143),(126,'2016-03-20 18:50:18','2016-03-20 20:30:29','Completado',3,144),(127,'2016-03-21 01:40:45','2016-03-21 05:42:02','Completado',3,145),(128,'2016-03-21 07:30:48','2016-03-21 07:54:00','Completado',3,146),(129,'2016-03-21 19:46:15','2016-03-28 16:48:40','Completado',1,147),(130,'2016-03-21 17:00:49','2016-03-21 18:30:57','Completado',3,148),(131,'2016-03-22 07:00:00','2016-03-22 07:24:00','Completado',3,149),(132,'2016-03-22 13:33:00','2016-03-22 13:51:00','Completado',2,150),(133,'2016-03-22 14:03:00','2016-03-22 15:18:00','Completado',2,151),(134,'2016-03-22 19:00:00','2016-03-22 23:00:00','Completado',3,152),(136,'2016-03-24 01:42:00','2016-03-24 03:00:00','Completado',3,154),(137,'2016-03-25 06:39:00','2016-03-25 07:58:00','Completado',2,155),(138,'2016-03-25 02:58:00','2016-03-25 04:43:26','Completado',3,156),(139,'2016-03-25 05:37:00','2016-03-25 05:42:00','Completado',1,157),(140,'2016-03-26 03:13:00','2016-03-26 04:10:00','Completado',1,159),(141,'2016-03-27 11:10:00','2016-03-27 14:50:01','Completado',3,160),(142,'2016-03-27 19:09:00','2016-03-27 23:12:00','Completado',3,161),(143,'2016-03-28 01:05:02','2016-03-28 01:15:16','Completado',2,162),(144,'2016-03-27 20:46:50','2016-03-27 21:00:00','Completado',1,163),(145,'2016-03-28 07:52:21','2016-03-28 10:14:53','Completado',3,164),(146,'2016-03-29 09:13:10',NULL,'Pendiente',1,165),(147,'2016-03-29 07:00:00','2016-03-29 07:26:00','Completado',3,166),(149,'2016-03-29 08:30:00','2016-03-29 09:47:00','Completado',1,168),(150,'2016-03-30 07:00:00','2016-03-30 08:00:00','Completado',3,169),(152,'2016-03-30 05:35:08','2016-03-30 08:23:03','Completado',3,171),(153,'2016-03-30 11:35:00','2016-03-30 13:10:22','Completado',3,173),(154,'2016-03-30 11:08:00','2016-03-30 19:00:23','Completado',1,174),(155,'2016-03-30 23:14:00','2016-03-30 23:17:00','Completado',2,175),(156,'2016-03-30 05:00:00','2016-03-30 06:40:00','Completado',3,176),(157,'2016-03-31 07:00:00','2016-03-31 07:47:04','Completado',3,177),(158,'2016-03-31 09:04:11','2016-03-31 10:20:25','Completado',3,178),(159,'2016-03-31 13:58:22',NULL,'Pendiente',1,179),(160,'2016-03-31 23:00:47','2016-04-01 05:46:06','Completado',3,181),(161,'2016-04-01 04:19:16','2016-04-01 04:36:22','Completado',3,182),(162,'2016-03-31 13:45:03','2016-03-31 14:32:02','Completado',3,183),(163,'2016-03-31 04:20:00','2016-03-31 06:15:00','Completado',3,187),(164,'2016-04-01 09:07:16','2016-04-01 11:03:56','Completado',3,188),(165,'2016-04-01 14:44:33','2016-04-01 15:53:57','Completado',3,189),(167,'2016-04-03 07:39:00','2016-04-03 11:38:20','Completado',5,191),(168,'2016-04-03 12:03:43','2016-04-03 12:40:44','Completado',1,192),(169,'2016-04-03 23:24:51',NULL,'Pendiente',3,193),(170,'2016-04-22 11:35:00','2016-04-22 11:39:53','Completado',1,195),(173,'2016-04-26 15:53:31','2016-04-26 16:24:00','Completado',1,198),(174,'2016-04-27 09:45:23','2016-04-27 15:40:38','Completado',5,199),(175,'2016-04-28 02:02:22','2016-04-28 03:15:05','Completado',4,200),(177,'2016-04-28 12:19:11','2016-04-28 12:40:28','Completado',1,202);
/*!40000 ALTER TABLE `operacion_imputacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solucion`
--

DROP TABLE IF EXISTS `solucion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solucion` (
  `id_solucion` int(11) NOT NULL AUTO_INCREMENT,
  `solucion_paro` varchar(255) NOT NULL,
  `id_operacion_imputacion` int(11) NOT NULL,
  PRIMARY KEY (`id_solucion`),
  KEY `fk_solucion_1_idx` (`id_operacion_imputacion`),
  CONSTRAINT `fk_op_solucion` FOREIGN KEY (`id_operacion_imputacion`) REFERENCES `operacion_imputacion` (`id_operacion_imputacion`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solucion`
--

LOCK TABLES `solucion` WRITE;
/*!40000 ALTER TABLE `solucion` DISABLE KEYS */;
INSERT INTO `solucion` VALUES (5,'Se procedio a calibrar el weight',4),(6,'Poner disponible el compresor de aire ',5),(7,'se procedio a verificar el sensor y a repararlo ',10),(8,'Cambiar la fase del motor de la rotatoria',11),(9,'Se cambio la cadena del elevador de cemento y desetaponar el filtro principal',12),(10,'Se le invirtio el Giro al motor y luego se normalizo',13),(11,'Se invertio el giro y luego se Normalizo ',14),(12,'Dar giro a la valvula rotatoria y normalizar',15),(13,'Se Invertio el giro y luego se normalizo',21),(14,'Normalizar y bajar rolos nuevamente',22),(15,'Se flojo un terminal de unas de las fases del motor del Weigh de Caliza por lo tanto este se estaba parando.',24),(16,'Darle giro inverso a la rotatoria ya que estaba atascada y luego normalizar.',25),(17,'Se prosedio a verificar y normalizar los sensores.',26),(18,'TEniamos una prueba del rolo 2 & 4 esto estaban presentando inconveniente por lo tanto cambiamos al 1 & 3 y procedimos a iniciar la molienda',27),(19,'Verificar estado de lubricacion y normalizar',29),(20,'Se invertio el giro y luego se normalizo',30),(21,'Abrir el manhold para que baje el material de puzolana',31),(27,'Darle giro inverso y normalizar',46),(28,'destapar ',48),(29,'reiniciar',49),(30,'llenado de tolva',50),(31,'esperar se despegue material de la placa de impacto',51),(32,'esperar se despegue material de la placa de impacto',52),(33,'despegar el material',53),(34,'chequeo electrico',54),(35,'parar el apilador para limpiar la descarga',55),(36,'paro programado,fin de jornada',56),(37,'lubricar equipo en mina',57),(38,'esperar se estabilice el equipo',58),(39,'esperar se estabilice el equipo',59),(40,'esperar se estabilice el equipo',60),(41,'esperar se estabilice el equipo',61),(42,'destape de material obstruyente',62),(43,'esperar se estabilice el equipo',63),(44,'destapar y limpiar el shuter',64),(45,'limpiar el shuter por debajo',65),(46,'limpiar la parte de abajo del shuter',66),(47,'fin de la jornada',67),(48,'picar la pared de material causada.',68),(49,'procedimos a limpiar la descarga',70),(50,'limpiar el shuter',71),(51,'Limpieza de la valvula rotatoria y chute',72),(52,'Limpieza del chute de descarga del Apron Feeder de caliza ',73),(53,'Normalizacion de los compresores: Limpieza de los filtros de la linea de agua de enfriamiento de los compresores.',74),(54,'Limpieza del chuter y rotatoria para evitar que se tapara. ',75),(55,'Problema de comunicacion entre la 213.02 y el 213.01 causado por cables rotos o defectuoso',76),(56,'Altas vibraciones por cambio del material. \" Material muy fino \"',77),(57,'Altas vibraciones por cambio de material. \" Material muy fino \" ',78),(58,'Altas vibraciones por cambio de material. \" Material muy fino \"',79),(59,'limpiar el chuter',80),(60,'limpiar chuter',81),(61,'limpiar el chuter',82),(62,'Se atasco la 312.01 y chuter debajo de la 312.01. Se hizo una limpieza completa.',83),(63,'Salio el principal por alarma de grasa en los rolos, llamar al electrico.',84),(64,'Se atasco la 312.01 y el chuter debajo de la 312.01. Se procedio a limpieza completa.',85),(65,'Normalizar braker L14.a03',86),(66,'limpieza de chuter',87),(67,'limpieza de chuter',88),(68,'Limpiar el Chute',89),(69,'Esperar 15 minutos',90),(70,'Esperar 20 minutos',91),(71,'limpieza de chuter',92),(72,'limpieza de chuter',93),(73,'mejorar la operacion',94),(74,'mejorar la operacion',95),(75,'mejorar la operacion',96),(76,'limpieza de chuter',97),(77,'limpieza de chuter',98),(78,'Liempieza de chute y rotatoria ',100),(79,'El electrico fue a la subestacion.',101),(80,'limpieza de chuter',102),(81,'limpieza del chute',103),(82,'tuvimos que bajar presion de trabajo para mejorar las condiciones del molino',105),(83,'Soldar la base.',106),(84,'limpieza profunda',107),(85,'limpieza de chuter',109),(86,'Se procedio a limpiar debajo del chutter de la rotatoria y la rotatoria para garantizar la corrida',110),(87,'Limpieza para retirar el el material ',111),(88,'limpieza profunda',112),(89,'limpieza profunda',113),(90,'limpiar el chuter',118),(91,'limpiza profunda',119),(92,'limpieza profunda',120),(93,'Limpieza de chutter, valvula rotatoria y chutter molino',121),(94,'Limpieza',122),(95,'limpieza de chuter',123),(96,'limpieza profunda',124),(97,'Limpiar la tolvita del apron feeder de caliza',125),(98,'limpieza de chuter',126),(99,'Limpieza profunda.',127),(100,'Arrancar nuevamente el equipo.',128),(101,'limpieza de chuter',130),(102,'hablar con Arismendy para agilizar',131),(103,'se llamo el electrico y resorvio',132),(104,'los electricos ajustaron el overlop',133),(105,'FIN DE LA JORNADA',134),(106,'Limpiar el chute y rotatoria',136),(107,'corregir la falla',137),(108,'Limpieza en el chute ',138),(109,'retirar la lamina ',139),(110,'apretar tornillos en el apron feeder de caliza',140),(111,'Esperar que bajara el nivel del silo',141),(112,'Esperar que bajara el nivel',142),(113,'Resetearon la falla despues de haber revisado',143),(114,'verificar y resetear el equipo',144),(115,'limpieza profunda',145),(116,'colocar una manguera nueva',129),(117,'se inspecciono todos los equipos',147),(118,'se cambio la correa',149),(119,'se chequeo la tolaba',150),(120,'limpieza',152),(121,'limpieza profunda',153),(122,'reparacion en el acople del motor.',154),(123,'se puso disponible inmediatamente',155),(124,'Limpiar la valvula rotatoria y la descarga',156),(125,'esperar los camiones',157),(126,'limpieza profunda',158),(127,'el equipo perteneciente a mineria tuvo que destapar el material.',160),(128,'remover material obstruyente',161),(129,'limpiar',162),(130,'limpieza',163),(131,'el weighing feeder estaba desaliniado',164),(132,'destape de material atascado',165),(133,'calibracion y reparar el apron feeder de caliza',167),(134,'alineacion ',168),(135,'se energiso desde la usb estacion 610',117),(136,'normalizar',170),(139,'limpiar la parte interna del shuter',69),(140,'se tuvo que realizar algunos ajustes para evitar el liqueo de aceite',173),(141,'Procedieron a ajustar y  calibrar los weight feeders',174),(142,'destapar para limpiar',175),(143,'cambiar la tuberia.',177);
/*!40000 ALTER TABLE `solucion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_area`
--

DROP TABLE IF EXISTS `sub_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_area` (
  `idsub_area` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_sub_area` varchar(45) NOT NULL,
  `id_area` int(11) NOT NULL,
  PRIMARY KEY (`idsub_area`),
  UNIQUE KEY `nombre_sub_area_UNIQUE` (`nombre_sub_area`),
  KEY `area_idx` (`id_area`),
  CONSTRAINT `fk_area` FOREIGN KEY (`id_area`) REFERENCES `area` (`id_area`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_area`
--

LOCK TABLES `sub_area` WRITE;
/*!40000 ALTER TABLE `sub_area` DISABLE KEYS */;
INSERT INTO `sub_area` VALUES (1,'TRITURACION Y TRANSPORTE DE CALIZA',1),(2,'TRITURACION CORRECTIVOS',1),(3,'DOSIFICACION DE CRUDO',1),(4,'MOLIENDA DE CRUDO',1),(5,'HOMOGENIZACION DE HARINA',1),(6,'CALCINACION',1),(7,'ENFRIADOR Y ALIMENTACION SILO CLINKER',1),(8,'CUARTO DE COMPRESORES CALCINACION',1),(9,'MOLIENDA DE CARBON',1),(10,'TRANSPORTE DE CLINKER',2),(11,'DOSIFICACION DE CEMENTO',2),(12,'MOLIENDA D/CEMENTO',2),(13,'ALMACEN DE CEMENTO',2),(14,'DESCARGA A GRANEL',3),(15,'EMPAQUE EN FUNDAS',3),(16,'CUARTO DE COMPRESORES MOLIENDA',2),(17,'CUARTO DE BOMBAS-LAGUNA',2),(18,'DESPACHO',3),(19,'LABORATORIO',4),(20,'ALMACEN DE CLINKER',5),(21,'SUBESTACIONES 513',6),(22,'SUBESTACIONES 610',6),(23,'SUBESTACIONES PRINCIPAL',6),(24,'PLANTA DE TRATAMIENTO',7),(25,'SUBESTACIONES 512',6);
/*!40000 ALTER TABLE `sub_area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_usuario` (
  `idTipo_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_usuario` varchar(20) NOT NULL,
  PRIMARY KEY (`idTipo_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_usuario`
--

LOCK TABLES `tipo_usuario` WRITE;
/*!40000 ALTER TABLE `tipo_usuario` DISABLE KEYS */;
INSERT INTO `tipo_usuario` VALUES (1,'administrador'),(2,'operador'),(3,'consultor');
/*!40000 ALTER TABLE `tipo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_usuario` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `id_tipo_usuario` int(11) NOT NULL,
  `estatus` varchar(20) NOT NULL COMMENT 'Atributo para saber si la cuenta esta Activa o Inactiva',
  `cambio_contrasena` datetime DEFAULT CURRENT_TIMESTAMP COMMENT 'Atributo para saber cuando se realizo el cambio de contrasena.',
  `password_anterior` varchar(45) DEFAULT NULL COMMENT 'Atributo para saber que contrasena tenia antes de cambiar',
  `dias_password` time DEFAULT NULL COMMENT 'Atributo para saber cuantos dias tiene la contrasena en uso',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `nombre_usuario_UNIQUE` (`nombre_usuario`),
  UNIQUE KEY `id_usuario_UNIQUE` (`id_usuario`),
  KEY `tipo_usuario_idx` (`id_tipo_usuario`),
  CONSTRAINT `fk_usuario_1` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipo_usuario` (`idTipo_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','40bd001563085fc35165329ea1ff5c5ecbdbbeef',1,'activo',NULL,NULL,NULL),(2,'jesus','7110eda4d09e062aa5e4a390b0a572ac0d2c0220',2,'activo',NULL,NULL,NULL),(3,'alfredo','8cb2237d0679ca88db6464eac60da96345513964',3,'activo',NULL,NULL,NULL),(4,'cop molienda','a1234d4094f0e14cff3a623e0379793236619cc1',2,'activo','2016-02-11 15:12:46',NULL,NULL),(5,'jpagan','7ca2a48210e81fe42445ff3e64bad585c8bc3e30',1,'activo','2016-02-13 09:40:28',NULL,NULL),(6,'jfernandezb','b215acf73220e01e8dc2e26eb457eb747abd9c49',1,'activo','2016-02-13 09:42:33',NULL,NULL),(7,'jlebron','1d43155058a525077051862208377d8f34e58754',3,'activo','2016-02-24 13:22:44',NULL,NULL),(24,'calcinacion','a13eb6ac4e331762bf8c6f9c8caae51c4a13b9f2',2,'activo','2016-03-02 08:56:51',NULL,NULL),(25,'aacosta','525b435bbeffe5331b83a4148de4b1587e1fbf02',3,'activo','2016-03-02 09:24:23',NULL,NULL),(26,'trituradora','6f5067fe0753b2f35f4e33ac9119d61c0e3f215d',2,'activo','2016-03-02 11:30:43',NULL,NULL),(27,'rtaveras','7c4a8d09ca3762af61e59520943dc26494f8941b',3,'activo','2016-03-02 11:37:26',NULL,NULL),(28,'nsegrera','6ca66462570d7f9d143d78b49c3295772c42cf65',3,'activo','2016-03-03 10:22:14',NULL,NULL),(30,'earias','3247ac86969d09c77ecf975f06166f9ffd145068',3,'activo','2016-03-11 10:43:02',NULL,NULL),(31,'framon','cf24a2f042618b0b7023e3305f614d7b6d8066dd',3,'activo','2016-03-11 15:17:37',NULL,NULL),(32,'cop_empaque','8ebbad9aa6bb3e28875b345676ed48e89ff9d07b',2,'activo','2016-03-18 13:02:04',NULL,NULL),(33,'dduran','c3b3f45a000b35071e1dc9dad271b9c52f9b181a',3,'activo','2016-04-25 11:52:57',NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mantenimientodb'
--

--
-- Dumping routines for database 'mantenimientodb'
--
/*!50003 DROP FUNCTION IF EXISTS `func_buscar_indice` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `func_buscar_indice`(indice VARCHAR(100), sentencia VARCHAR(100)) RETURNS int(11)
BEGIN

DECLARE id INT;

CASE sentencia

WHEN 'usuario' THEN
SELECT id_usuario INTO id FROM mantenimientodb.usuario
WHERE usuario.nombre_usuario = indice;

WHEN 'equipo' THEN
SELECT id_equipo INTO id FROM mantenimientodb.equipo
WHERE equipo.cod_equipo = indice;

WHEN 'sub_area' THEN
SELECT idsub_area INTO id FROM mantenimientodb.sub_area
WHERE sub_area.nombre_sub_area = indice;

WHEN 'area' THEN
SELECT id_area INTO id FROM mantenimientodb.area
WHERE area.nombre_area = indice;

WHEN 'causa' THEN
SELECT id_causa INTO id FROM mantenimientodb.causa
WHERE causa.tipo_causa = indice
LIMIT 1;

WHEN 'disciplina' THEN
SELECT id_disciplina INTO id FROM mantenimientodb.disciplina
WHERE disciplina.nombre_disciplina = indice;

END CASE;
RETURN id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_indice` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_indice`(
IN indice VARCHAR(100),
IN sentencia VARCHAR(100),
OUT resultado VARCHAR(100))
    COMMENT 'Busca el indice del atributo solicitado'
BEGIN

CASE sentencia

WHEN 'usuario' THEN
SELECT id_usuario INTO resultado FROM mantenimientodb.usuario
WHERE usuario.nombre_usuario = indice;

WHEN 'equipo' THEN
SELECT id_equipo INTO resultado FROM mantenimientodb.equipo
WHERE equipo.cod_equipo = indice;

WHEN 'sub_area' THEN
SELECT idsub_area INTO resultado FROM mantenimientodb.sub_area
WHERE sub_area.nombre_sub_area = indice;

WHEN 'area' THEN
SELECT id_area INTO resultado FROM mantenimientodb.area
WHERE area.nombre_area = indice;

WHEN 'causa' THEN
SELECT id_causa INTO resultado FROM mantenimientodb.causa
WHERE causa.tipo_causa = indice
LIMIT 1;

WHEN 'disciplina' THEN
SELECT id_disciplina INTO resultado FROM mantenimientodb.disciplina
WHERE disciplina.nombre_disciplina = indice;

END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `logearUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `logearUsuario`(IN usuario VARCHAR(20), clave VARCHAR(20))
BEGIN
SELECT * FROM mantenimientodb.usuario WHERE nombre_usuario= usuario 
AND password = SHA(clave);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `Paros` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Paros`(IN estatus VARCHAR(15))
BEGIN

CASE estatus
WHEN 'Completado' THEN
SELECT 
    op.id_operacion_imputacion AS Codigo,
    usu.nombre_usuario AS Usuario,
    a.nombre_area AS Area,
    sa.nombre_sub_area AS SubArea,
    e.cod_equipo AS Equipo,
    op.tiempo_inicio_paro AS 'Tiempo de Inicio',
    op.tiempo_fin_paro AS 'Tiempo Final'
FROM
    mantenimientodb.area a
        INNER JOIN
    mantenimientodb.sub_area sa ON sa.id_area = a.id_area
        INNER JOIN
    mantenimientodb.equipo e ON e.idSubArea = sa.idsub_area
        INNER JOIN
    mantenimientodb.equipo_causa ON equipo_causa.id_equipo = e.id_equipo
        INNER JOIN
    mantenimientodb.operacion_imputacion op ON op.id_equipo_causa = equipo_causa.id_equipo_causa
        INNER JOIN
    mantenimientodb.causa cau ON cau.id_causa = equipo_causa.id_causa
        INNER JOIN
    mantenimientodb.usuario usu ON usu.id_usuario = cau.id_usuario
WHERE
    op.estatus_paro = estatus
ORDER BY tiempo_fin_paro DESC
LIMIT 15;

WHEN 'Pendiente' THEN
 
SELECT 
    op.id_operacion_imputacion AS Codigo,
    usu.nombre_usuario AS Usuario,
    a.nombre_area AS Area,
    sa.nombre_sub_area AS SubArea,
    e.cod_equipo AS Equipo,
    op.tiempo_inicio_paro AS 'Tiempo de Inicio'
FROM
    mantenimientodb.area a
        INNER JOIN
    mantenimientodb.sub_area sa ON sa.id_area = a.id_area
        INNER JOIN
    mantenimientodb.equipo e ON e.idSubArea = sa.idsub_area
        INNER JOIN
    mantenimientodb.equipo_causa ON equipo_causa.id_equipo = e.id_equipo
        INNER JOIN
    mantenimientodb.operacion_imputacion op ON op.id_equipo_causa = equipo_causa.id_equipo_causa
        INNER JOIN
    mantenimientodb.causa cau ON cau.id_causa = equipo_causa.id_causa
        INNER JOIN
    mantenimientodb.usuario usu ON usu.id_usuario = cau.id_usuario
WHERE
    op.estatus_paro = estatus
ORDER BY tiempo_fin_paro DESC
LIMIT 15;
END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `rellenarcomboBox` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `rellenarcomboBox`(
IN sentencia VARCHAR(15),
IN indice INT,
OUT resultado VARCHAR(100))
BEGIN

-- SET resultado = '';

IF sentencia = 'area' AND indice IS NULL THEN 
   SELECT nombre_area INTO resultado FROM mantenimientodb.area;

ELSE

CASE sentencia

WHEN 'subArea' THEN
SELECT nombre_sub_area INTO resultado FROM mantenimientodb.sub_area
WHERE id_area = indice;

WHEN 'equipo' THEN
SELECT cod_equipo INTO resultado FROM mantenimientodb.equipo
WHERE idSubArea = indice;

END CASE;
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizar_paro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizar_paro`(IN idparo INT,
IN tiempoFin VARCHAR(50),
IN solucion VARCHAR(255))
    COMMENT 'Procedimiento para modificar los paros de Pendientes a Completados.'
BEGIN
DECLARE id_equipo INT;

-- Actualiza el Estado de la Operacion
UPDATE mantenimientodb.operacion_imputacion 
SET 
    tiempo_fin_paro = tiempoFin,
    estatus_paro = 'Completado'
WHERE
    id_operacion_imputacion = idParo;

-- Actualiza el estado del Equipo
SELECT 
    id_equipo_causa
INTO id_equipo FROM
    mantenimientodb.operacion_imputacion
WHERE
    id_operacion_imputacion = idparo;

UPDATE mantenimientodb.equipo_causa 
SET 
    estatus_equipo = 'Activo'
WHERE
    id_equipo_causa = id_equipo;
    
-- Agrega la solucion del Paro    
INSERT INTO mantenimientodb.solucion (solucion_paro, id_operacion_imputacion)
 VALUES (solucion, idparo);

    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_administrar_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_administrar_usuario`(
IN nombreUsuario VARCHAR(20),
IN contrasena VARCHAR(45),
IN tipoUsuario INT,
IN codigo_empleado INT,
IN nombre_empleado VARCHAR(30),
IN apellido_empleado VARCHAR(30)
)
    COMMENT 'Procedimiento para agregar un nuevo usuario'
BEGIN

DECLARE idUsuario INT;

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
DECLARE EXIT HANDLER FOR SQLWARNING ROLLBACK;

START TRANSACTION;

-- Crea el usuario
INSERT INTO mantenimientodb.usuario (nombre_usuario, password, id_tipo_usuario, estatus) 
VALUES (nombreUsuario, SHA1(contrasena), tipoUsuario, 'activo');

SET idUsuario := LAST_INSERT_ID();

-- Agrega informacion de empleado
 INSERT INTO mantenimientodb.empleado (codigo_empleado, nombre_empleado, apellido_empleado, id_usuario)
 VALUES (codigo_empleado, nombre_empleado, apellido_empleado, idUsuario);

COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_agregar_registros` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregar_registros`(
IN nombre VARCHAR(100), 
IN registro VARCHAR(15),
IN codigo VARCHAR(50),
IN indice INT)
    COMMENT 'Procedimiento para agregar nuevos registros a la Base de Datos'
BEGIN

DECLARE nombre_de_area VARCHAR(100);
DECLARE nombre_de_subArea VARCHAR(100);
DECLARE nombre_de_equipo VARCHAR(100);
DECLARE nombre_de_disciplina VARCHAR(20);
DECLARE nombre_causa VARCHAR(200);

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
DECLARE EXIT HANDLER FOR SQLWARNING ROLLBACK;

START TRANSACTION;

CASE registro
WHEN 'area' THEN
-- Agrega Nueva Area
SET nombre_de_area = nombre;

IF nombre_de_area IS NOT NULL THEN
INSERT INTO mantenimientodb.area (nombre_area) VALUES (nombre_de_area);
END IF;

WHEN 'subArea' THEN
-- Agrega Nueva SubArea
SET nombre_de_subArea = nombre;
IF nombre_de_subArea IS NOT NULL THEN
INSERT INTO mantenimientodb.sub_area (nombre_sub_area, id_area) VALUES (nombre_de_subArea, indice);
END IF;

WHEN 'equipo' THEN
-- Agrega Nuevo Equipo
SET nombre_de_equipo = nombre;
INSERT INTO mantenimientodb.equipo (cod_equipo, nombre_equipo, idSubArea) VALUES (codigo, nombre_de_equipo, indice);

WHEN 'disciplina' THEN
-- Agrega Nueva Disciplina
SET nombre_de_disciplina = nombre;
IF nombre_de_disciplina IS NOT NULL THEN
INSERT INTO mantenimientodb.disciplina (nombre_disciplina) VALUES (nombre_de_disciplina);
END IF;

WHEN 'causa' THEN
-- Agrega Nueva Causa
SET nombre_causa = nombre;
IF nombre_causa IS NOT NULL THEN
INSERT INTO mantenimientodb.causa (tipo_causa, id_usuario) VALUES (nombre_causa, 1);
END IF;

COMMIT;
END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_agregar_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_agregar_usuario`(
IN nombreUsuario VARCHAR(20),
IN contrasena VARCHAR(45),
IN tipoUsuario INT,
IN codigo_empleado INT,
IN nombre_empleado VARCHAR(30),
IN apellido_empleado VARCHAR(30)
)
    COMMENT 'Gestiona las propiedades de un usuario'
BEGIN
DECLARE idUsuario INT;

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
DECLARE EXIT HANDLER FOR SQLWARNING ROLLBACK;

START TRANSACTION;

-- Crea el usuario
INSERT INTO mantenimientodb.usuario (nombre_usuario, password, id_tipo_usuario, estatus) 
VALUES (nombreUsuario, SHA1(contrasena), tipoUsuario, 'activo');

SET idUsuario := LAST_INSERT_ID();

-- Agrega informacion de empleado
 INSERT INTO mantenimientodb.empleado (codigo_empleado, nombre_empleado, apellido_empleado, id_usuario)
 VALUES (codigo_empleado, nombre_empleado, apellido_empleado, idUsuario);
 
COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_buscar_causa` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_causa`(IN causa VARCHAR(255), 
IN descripcion VARCHAR(255),
OUT resultado VARCHAR(10))
    COMMENT 'Busca el indice de la causa seleccionada'
BEGIN

SELECT id_causa INTO resultado FROM mantenimientodb.causa
WHERE causa.tipo_causa LIKE CONCAT('%',causa,'%') AND causa.descripcion_adicional LIKE CONCAT('%',descripcion,'%')
LIMIT 1;


END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_buscar_equipo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_equipo`(IN codigo VARCHAR(20))
BEGIN

SELECT 
    eq.cod_equipo,
    eq.nombre_equipo,
    a.nombre_area,
    sub.nombre_sub_area
FROM
    mantenimientodb.equipo eq
        INNER JOIN
    sub_area sub ON sub.idsub_area = eq.idSubArea
        INNER JOIN
    area a ON a.id_area = sub.id_area
WHERE eq.cod_equipo LIKE CONCAT('%',codigo,'%');

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_buscar_paro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_paro`(IN fechaInicio VARCHAR(50), 
IN fechaFin VARCHAR(50),
IN filtro VARCHAR(20))
BEGIN

CASE filtro
WHEN '' THEN
SELECT 
    op.id_operacion_imputacion AS Codigo,
    usu.nombre_usuario AS Usuario,
    a.nombre_area AS Area,
    sa.nombre_sub_area AS SubArea,
    e.cod_equipo AS Equipo,
    di.nombre_disciplina AS Disciplina,
    cau.tipo_causa AS Causa,
    cau.descripcion_adicional AS 'Descripcion Adicional',
    op.tiempo_inicio_paro AS 'Tiempo de Inicio',
    op.tiempo_fin_paro AS 'Tiempo Final',
    sol.solucion_paro AS Solucion
FROM
    mantenimientodb.area a
        INNER JOIN
    mantenimientodb.sub_area sa ON sa.id_area = a.id_area
        INNER JOIN
    mantenimientodb.equipo e ON e.idSubArea = sa.idsub_area
        INNER JOIN
    mantenimientodb.equipo_causa ON equipo_causa.id_equipo = e.id_equipo
        INNER JOIN
    mantenimientodb.operacion_imputacion op ON op.id_equipo_causa = equipo_causa.id_equipo_causa
        INNER JOIN
    mantenimientodb.causa cau ON cau.id_causa = equipo_causa.id_causa
        INNER JOIN
    mantenimientodb.usuario usu ON usu.id_usuario = cau.id_usuario
    INNER JOIN
    mantenimientodb.solucion sol ON sol.id_operacion_imputacion = op.id_operacion_imputacion
    INNER JOIN
    mantenimientodb.disciplina di ON op.id_disciplina = di.id_disciplina
ORDER BY tiempo_fin_paro DESC;

WHEN 'fecha' THEN
SELECT 
    op.id_operacion_imputacion AS Codigo,
    usu.nombre_usuario AS Usuario,
    a.nombre_area AS Area,
    sa.nombre_sub_area AS SubArea,
    e.cod_equipo AS Equipo,
    di.nombre_disciplina AS Disciplina,
    cau.tipo_causa AS Causa,
    cau.descripcion_adicional AS 'Descripcion Adicional',
    op.tiempo_inicio_paro AS 'Tiempo de Inicio',
    op.tiempo_fin_paro AS 'Tiempo Final',
    sol.solucion_paro AS Solucion
FROM
    mantenimientodb.area a
        INNER JOIN
    mantenimientodb.sub_area sa ON sa.id_area = a.id_area
        INNER JOIN
    mantenimientodb.equipo e ON e.idSubArea = sa.idsub_area
        INNER JOIN
    mantenimientodb.equipo_causa ON equipo_causa.id_equipo = e.id_equipo
        INNER JOIN
    mantenimientodb.operacion_imputacion op ON op.id_equipo_causa = equipo_causa.id_equipo_causa
        INNER JOIN
    mantenimientodb.causa cau ON cau.id_causa = equipo_causa.id_causa
        INNER JOIN
    mantenimientodb.usuario usu ON usu.id_usuario = cau.id_usuario
    INNER JOIN
    mantenimientodb.solucion sol ON sol.id_operacion_imputacion = op.id_operacion_imputacion
    INNER JOIN
    mantenimientodb.disciplina di ON op.id_disciplina = di.id_disciplina
    WHERE
     DATE_FORMAT(op.tiempo_inicio_paro, '%Y-%m-%d') >= fechaInicio
            AND DATE_FORMAT(op.tiempo_fin_paro, '%Y-%m-%d') <= fechaFin
ORDER BY tiempo_fin_paro DESC;

END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_cambiar_clave` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_cambiar_clave`(IN usuario VARCHAR(30),
IN clave_anterior VARCHAR(45),
IN clave_nueva VARCHAR(45)
)
BEGIN

IF EXISTS (SELECT * FROM mantenimientodb.usuario
WHERE nombre_usuario=usuario AND password=SHA1(clave_anterior) ) THEN

SELECT 'Entre a la condicion';

UPDATE mantenimientodb.usuario 
SET 
    password = SHA1(clave_nueva),
    password_anterior = SHA1(clave_anterior)
WHERE
    id_usuario = usuario;

ELSE
SELECT 'El usuario no existe';
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_cambiar_credencial_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_cambiar_credencial_usuario`(IN usuario VARCHAR(30),
IN clave VARCHAR(45),
IN tipo_usuario INT,
IN estado VARCHAR(15),
IN codigo_usuario INT
)
    COMMENT 'Procedimiento para modificar credenciales de Usuario'
BEGIN

DECLARE clave_anterior VARCHAR(45);

-- Verifica si el usuario existe
IF EXISTS (SELECT * FROM mantenimientodb.usuario
WHERE nombre_usuario=usuario) THEN


-- Si no se proporciona una nueva clave, solo actualiza el estado y tipo de usuario
IF clave IS NULL THEN

UPDATE mantenimientodb.usuario 
SET 
    id_tipo_usuario = tipo_usuario, 
    estatus = estado
WHERE 
	id_usuario = codigo_usuario;

ELSE

-- Guarda la clave anterior del usuario
SELECT password INTO clave_anterior FROM mantenimientodb.usuario WHERE id_usuario = codigo_usuario;

-- Actualiza los credenciales del Usuario
UPDATE mantenimientodb.usuario 
SET 
    password = SHA1(clave),
    id_tipo_usuario = tipo_usuario,
    estatus = estado,
    password_anterior = clave_anterior
WHERE
    id_usuario = codigo_usuario;
END IF;

ELSE
SELECT 'El usuario no existe';
END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminar_paro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_paro`(IN indice INT)
    COMMENT 'Procedimiento para eliminar paros'
BEGIN

DELETE FROM mantenimientodb.operacion_imputacion WHERE id_operacion_imputacion = indice;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminar_registros` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_registros`(IN id VARCHAR(255), IN registro VARCHAR(20))
BEGIN

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
DECLARE EXIT HANDLER FOR SQLWARNING ROLLBACK;

START TRANSACTION;

CASE registro
WHEN 'area' THEN
DELETE FROM mantenimientodb.area WHERE nombre_area = id;
-- INSERT INTO mantenimientodb.area (nombre_area) VALUES (CONCAT(id, "2222222"));

WHEN 'disciplina' THEN
DELETE FROM mantenimientodb.disciplina WHERE nombre_disciplina = id;

WHEN 'subArea' THEN
DELETE FROM mantenimientodb.sub_area WHERE nombre_sub_area = id;

COMMIT;
END CASE;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertar_paro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_paro`(
IN causa VARCHAR(200),
IN causa_extendida VARCHAR(255),
IN indice VARCHAR(20),
IN tiempo_inicio VARCHAR(30),
IN tiempo_fin VARCHAR(30), 
IN estadoEquipo VARCHAR(20),
IN estado_paro VARCHAR(20),
IN solucion VARCHAR(255))
BEGIN

DECLARE id_usuario INT;
DECLARE id_equipo INT;
DECLARE id_disciplina INT;

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
DECLARE EXIT HANDLER FOR SQLWARNING ROLLBACK;

START TRANSACTION;

-- SELECT buscar_indice(indice, 'usuario') INTO id_usuario;
-- Causa
SET id_usuario = buscar_indice(indice, 'usuario');
INSERT INTO mantenimientodb.causa
(tipo_causa, descripcion_adicional, id_usuario) 
VALUES (causa, causa_extendida, id_usuario);

-- Equipo_Causa
SET id_equipo = buscar_indice(indice, 'equipo');
	IF estadoEquipo = 'Activo' THEN 
		INSERT INTO mantenimientodb.equipo_causa 
        (id_equipo, id_causa, estatus_equipo) 
        VALUES(id_equipo, LAST_INSERT_ID(), 'Activo');
	ELSE
		INSERT INTO mantenimientodb.equipo_causa
        (id_equipo, id_causa, estatus_equipo) 
        VALUES(id_equipo, LAST_INSERT_ID(), 'Inactivo');
	END IF;

-- Operacion Imputacion
	IF estadoParo = 'Completado' THEN
    
		SET id_disciplina = buscar_indice(indice, 'disciplina');
		INSERT INTO mantenimientodb.operacion_imputacion
        (tiempo_inicio_paro, tiempo_fin_paro, estatus, id_disciplina, id_causa, id_usuario) 
		VALUES (tiempo_inicio, tiempo_fin, estadoParo, id_disciplina, LAST_INSERT_ID(), id_usuario);
	ELSE
		INSERT INTO mantenimientodb.operacion_imputacion
        (tiempo_inicio_paro, tiempo_fin_paro, estatus, id_disciplina, id_causa, usuario) 
		VALUES (tiempo_inicio, null, estado_paro, id_disciplina, LAST_INSERT_ID(), id_usuario);
	END IF;

	IF estadoParo = 'Completado' THEN
		INSERT INTO mantenimientodb.solucion
        (solucion_paro, id_operacion_imputacion) 
        VALUES (solucion, LAST_INSERT_ID());
	END IF;


COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_modificar_paro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_paro`(IN codigo INT, 
IN solucion VARCHAR(255), 
IN tiempoInicio VARCHAR(40),
IN tiempoFin VARCHAR(40),
IN disciplina INT,
IN causa VARCHAR(255),
IN descripcion VARCHAR(255),
IN codigoCausa INT)
    COMMENT 'Procedimiento para modificar los paros'
BEGIN

DECLARE EXIT HANDLER FOR SQLEXCEPTION ROLLBACK;
DECLARE EXIT HANDLER FOR SQLWARNING ROLLBACK;

START TRANSACTION;

-- Actualiza Datos de Solucion
UPDATE mantenimientodb.solucion SET solucion_paro = solucion 
WHERE id_operacion_imputacion = codigo;

-- Actualiza Datos Operacion Imputacion
UPDATE mantenimientodb.operacion_imputacion SET 
tiempo_inicio_paro = tiempoInicio, 
tiempo_fin_paro = tiempoFin, 
id_disciplina = disciplina
WHERE id_operacion_imputacion = codigo;

-- Actualiza la Causa del Paro
UPDATE mantenimientodb.causa SET tipo_causa = causa, 
descripcion_adicional = descripcion WHERE id_causa = codigoCausa;

COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrar_usuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrar_usuario`(IN usuario VARCHAR(20))
BEGIN

IF usuario IS NULL THEN

SELECT 
    nombre_usuario, estatus, tipo_usuario
FROM
    mantenimientodb.tipo_usuario
        INNER JOIN
    usuario ON idTipo_usuario = id_tipo_usuario;

ELSE


SELECT 
    nombre_usuario, estatus, tipo_usuario
FROM
    mantenimientodb.tipo_usuario
        INNER JOIN
    usuario ON idTipo_usuario = id_tipo_usuario
WHERE
	nombre_usuario = usuario;


END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_reporte_duracion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reporte_duracion`(IN fechaInicio VARCHAR(50), IN fechaFin VARCHAR(50))
BEGIN
SELECT 
    e.cod_equipo AS Equipo,
    cau.tipo_causa AS Causa,
    op.tiempo_inicio_paro,
    op.tiempo_fin_paro,
    CONCAT('', TIMEDIFF(IFNULL(tiempo_fin_paro, tiempo_inicio_paro),
            tiempo_inicio_paro)) AS Duracion,
    sol.solucion_paro
FROM
    mantenimientodb.area a
        INNER JOIN
    mantenimientodb.sub_area sa ON sa.id_area = a.id_area
        INNER JOIN
    mantenimientodb.equipo e ON e.idSubArea = sa.idsub_area
        INNER JOIN
    mantenimientodb.equipo_causa ON equipo_causa.id_equipo = e.id_equipo
        INNER JOIN
    mantenimientodb.operacion_imputacion op ON op.id_equipo_causa = equipo_causa.id_equipo_causa
        INNER JOIN
    mantenimientodb.causa cau ON cau.id_causa = equipo_causa.id_causa
        INNER JOIN
    mantenimientodb.usuario usu ON usu.id_usuario = cau.id_usuario
        INNER JOIN
    mantenimientodb.solucion sol ON op.id_operacion_imputacion = sol.id_operacion_imputacion
WHERE
     DATE_FORMAT(op.tiempo_inicio_paro, '%Y-%m-%d') >= fechaInicio
            AND DATE_FORMAT(op.tiempo_fin_paro, '%Y-%m-%d') <= fechaFin;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_reporte_duracion_total` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reporte_duracion_total`(IN fechaInicio VARCHAR(50), IN fechaFin VARCHAR(50))
BEGIN
SELECT 
     CONCAT('', SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(IFNULL(op.tiempo_fin_paro, op.tiempo_inicio_paro),
     op.tiempo_inicio_paro))))) AS Total
FROM

    mantenimientodb.area a
        INNER JOIN
    mantenimientodb.sub_area sa ON sa.id_area = a.id_area
        INNER JOIN
    mantenimientodb.equipo e ON e.idSubArea = sa.idsub_area
        INNER JOIN
    mantenimientodb.equipo_causa ON equipo_causa.id_equipo = e.id_equipo
        INNER JOIN
    mantenimientodb.operacion_imputacion op ON op.id_equipo_causa = equipo_causa.id_equipo_causa
        INNER JOIN
    mantenimientodb.causa cau ON cau.id_causa = equipo_causa.id_causa
        INNER JOIN
    mantenimientodb.usuario usu ON usu.id_usuario = cau.id_usuario
        INNER JOIN
    mantenimientodb.disciplina di ON op.id_disciplina = di.id_disciplina
WHERE
     DATE_FORMAT(op.tiempo_inicio_paro, '%Y-%m-%d') >= fechaInicio 
            AND DATE_FORMAT(op.tiempo_fin_paro, '%Y-%m-%d') <=  fechaFin
ORDER BY tiempo_fin_paro DESC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_reporte_frecuencia` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reporte_frecuencia`(IN fechaInicio VARCHAR(50), IN fechaFin VARCHAR(50))
BEGIN
SELECT 
    e.cod_equipo AS Equipo,
    cau.tipo_causa AS Tipo_Paro,
    COUNT(cau.tipo_causa) AS Frecuencia,
    sol.solucion_paro
FROM
    mantenimientodb.area a
        INNER JOIN
    mantenimientodb.sub_area sa ON sa.id_area = a.id_area
        INNER JOIN
    mantenimientodb.equipo e ON e.idSubArea = sa.idsub_area
        INNER JOIN
    mantenimientodb.equipo_causa ON equipo_causa.id_equipo = e.id_equipo
        INNER JOIN
    mantenimientodb.operacion_imputacion op ON op.id_equipo_causa = equipo_causa.id_equipo_causa
        INNER JOIN
    mantenimientodb.causa cau ON cau.id_causa = equipo_causa.id_causa
        INNER JOIN
    mantenimientodb.usuario usu ON usu.id_usuario = cau.id_usuario
        INNER JOIN
    mantenimientodb.solucion sol ON op.id_operacion_imputacion = sol.id_operacion_imputacion

WHERE
     DATE_FORMAT(op.tiempo_inicio_paro, '%Y-%m-%d') >= fechaInicio
            AND DATE_FORMAT(op.tiempo_fin_paro, '%Y-%m-%d') <=  fechaFin
GROUP BY cau.tipo_causa
ORDER BY Frecuencia DESC;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_reporte_rango_fecha` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_reporte_rango_fecha`(IN fechaInicio VARCHAR(50), IN fechaFin VARCHAR(50))
BEGIN

SELECT 
    op.id_operacion_imputacion,
    DATE_FORMAT(op.tiempo_inicio_paro, '%Y-%m-%d') AS Fecha_Inicio,
    DATE_FORMAT(op.tiempo_fin_paro, '%Y-%m-%d') AS Fecha_Fin,
    e.cod_equipo AS Equipo,
    di.nombre_disciplina AS Disciplina,
    cau.tipo_causa AS Tipo_Paro,
    IFNULL(cau.descripcion_adicional, '') AS Descripcion_Adicional,
    a.nombre_area AS Area,
    sa.nombre_sub_area AS SubArea,
    DATE_FORMAT(op.tiempo_inicio_paro, '%H:%i:%s') AS Hora_Inicio,
    DATE_FORMAT(op.tiempo_fin_paro, '%H:%i:%s') AS Hora_Fin,
    CONCAT('',
            TIMEDIFF(IFNULL(tiempo_fin_paro, tiempo_inicio_paro),
                    tiempo_inicio_paro)) AS Duracion,
    CONCAT('',
            ABS(TIME_TO_SEC(TIMEDIFF(IFNULL(op.tiempo_fin_paro,
                                            op.tiempo_inicio_paro),
                                    op.tiempo_inicio_paro)) / 3600)) AS 'Duracion Horas'
FROM
    mantenimientodb.area a
        INNER JOIN
    mantenimientodb.sub_area sa ON sa.id_area = a.id_area
        INNER JOIN
    mantenimientodb.equipo e ON e.idSubArea = sa.idsub_area
        INNER JOIN
    mantenimientodb.equipo_causa ON equipo_causa.id_equipo = e.id_equipo
        INNER JOIN
    mantenimientodb.operacion_imputacion op ON op.id_equipo_causa = equipo_causa.id_equipo_causa
        INNER JOIN
    mantenimientodb.causa cau ON cau.id_causa = equipo_causa.id_causa
        INNER JOIN
    mantenimientodb.usuario usu ON usu.id_usuario = cau.id_usuario
      INNER JOIN
    mantenimientodb.disciplina di ON op.id_disciplina = di.id_disciplina
WHERE
     DATE_FORMAT(op.tiempo_inicio_paro, '%Y-%m-%d') >= fechaInicio
            AND DATE_FORMAT(op.tiempo_fin_paro, '%Y-%m-%d') <=  fechaFin
ORDER BY tiempo_fin_paro DESC;

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

-- Dump completed on 2016-05-02 15:40:58
