-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: dbs_avicola
-- ------------------------------------------------------
-- Server version	5.7.33-log

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
-- Table structure for table `tbl_cubeta`
--

DROP TABLE IF EXISTS `tbl_cubeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cubeta` (
  `cub_cubetaid` int(11) NOT NULL AUTO_INCREMENT,
  `cub_tipo` varchar(45) DEFAULT NULL,
  `cub_descripcion` varchar(45) DEFAULT NULL,
  `cub_valor` double DEFAULT NULL,
  PRIMARY KEY (`cub_cubetaid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cubeta`
--

LOCK TABLES `tbl_cubeta` WRITE;
/*!40000 ALTER TABLE `tbl_cubeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_cubeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_cubeta_has_tbl_foto`
--

DROP TABLE IF EXISTS `tbl_cubeta_has_tbl_foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_cubeta_has_tbl_foto` (
  `fk_cubeta` int(11) DEFAULT NULL,
  `fk_foto` int(11) DEFAULT NULL,
  KEY `fk_cubetaf_idx` (`fk_cubeta`),
  KEY `fk_fotof_idx` (`fk_foto`),
  CONSTRAINT `fk_cubetaf` FOREIGN KEY (`fk_cubeta`) REFERENCES `tbl_cubeta` (`cub_cubetaid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_fotof` FOREIGN KEY (`fk_foto`) REFERENCES `tbl_foto` (`fot_fotoid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_cubeta_has_tbl_foto`
--

LOCK TABLES `tbl_cubeta_has_tbl_foto` WRITE;
/*!40000 ALTER TABLE `tbl_cubeta_has_tbl_foto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_cubeta_has_tbl_foto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_foto`
--

DROP TABLE IF EXISTS `tbl_foto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_foto` (
  `fot_fotoid` int(11) NOT NULL AUTO_INCREMENT,
  `fot_ruta` varchar(255) DEFAULT NULL,
  `fot_descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fot_fotoid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_foto`
--

LOCK TABLES `tbl_foto` WRITE;
/*!40000 ALTER TABLE `tbl_foto` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_foto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_galpon`
--

DROP TABLE IF EXISTS `tbl_galpon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_galpon` (
  `gal_galponid` int(11) NOT NULL AUTO_INCREMENT,
  `gal_cantidad` int(11) DEFAULT NULL,
  `gal_ubicacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`gal_galponid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_galpon`
--

LOCK TABLES `tbl_galpon` WRITE;
/*!40000 ALTER TABLE `tbl_galpon` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_galpon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_huevo`
--

DROP TABLE IF EXISTS `tbl_huevo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_huevo` (
  `hue_hievosid` int(11) NOT NULL AUTO_INCREMENT,
  `hue_clasificacion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`hue_hievosid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_huevo`
--

LOCK TABLES `tbl_huevo` WRITE;
/*!40000 ALTER TABLE `tbl_huevo` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_huevo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_lote`
--

DROP TABLE IF EXISTS `tbl_lote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_lote` (
  `lot_loteid` int(11) NOT NULL AUTO_INCREMENT,
  `lot_cantidad` int(11) DEFAULT NULL,
  `lot_color` varchar(45) DEFAULT NULL,
  `fk_huevo` int(11) DEFAULT NULL,
  `fk_galpon` int(11) DEFAULT NULL,
  `fk_produccion` int(11) DEFAULT NULL,
  PRIMARY KEY (`lot_loteid`),
  KEY `fk_huevo_idx` (`fk_huevo`),
  KEY `fk_galpon_idx` (`fk_galpon`),
  KEY `fk_produccion_idx` (`fk_produccion`),
  CONSTRAINT `fk_galpon` FOREIGN KEY (`fk_galpon`) REFERENCES `tbl_galpon` (`gal_galponid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_huevo` FOREIGN KEY (`fk_huevo`) REFERENCES `tbl_huevo` (`hue_hievosid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_produccion` FOREIGN KEY (`fk_produccion`) REFERENCES `tbl_produccion` (`pro_produccionid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_lote`
--

LOCK TABLES `tbl_lote` WRITE;
/*!40000 ALTER TABLE `tbl_lote` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_lote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_ordencompra`
--

DROP TABLE IF EXISTS `tbl_ordencompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_ordencompra` (
  `orc_ordencompraid` int(11) NOT NULL AUTO_INCREMENT,
  `orc_fecha` date DEFAULT NULL,
  `orc_valortotal` double DEFAULT NULL,
  `orc_impuesto` double DEFAULT NULL,
  `fk_cliente` int(11) DEFAULT NULL,
  `orc_direccion` varchar(255) DEFAULT NULL,
  `orc_telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orc_ordencompraid`),
  KEY `fk_cliente_idx` (`fk_cliente`),
  CONSTRAINT `fk_cliente` FOREIGN KEY (`fk_cliente`) REFERENCES `tbl_usuario` (`usu_usuarioid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ordencompra`
--

LOCK TABLES `tbl_ordencompra` WRITE;
/*!40000 ALTER TABLE `tbl_ordencompra` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_ordencompra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_ordencompra_has_tbl_cubeta`
--

DROP TABLE IF EXISTS `tbl_ordencompra_has_tbl_cubeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_ordencompra_has_tbl_cubeta` (
  `orc_odencompracubetaid` int(11) NOT NULL AUTO_INCREMENT,
  `fk_ordendecompra` int(11) DEFAULT NULL,
  `fk_cubetas` int(11) DEFAULT NULL,
  `orc_valorcompra` double DEFAULT NULL,
  `orc_cantidadcomprada` int(11) DEFAULT NULL,
  PRIMARY KEY (`orc_odencompracubetaid`),
  KEY `fk_ordenC_idx` (`fk_ordendecompra`),
  KEY `fk_cubetaC_idx` (`fk_cubetas`),
  CONSTRAINT `fk_cubetaC` FOREIGN KEY (`fk_cubetas`) REFERENCES `tbl_cubeta` (`cub_cubetaid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ordenC` FOREIGN KEY (`fk_ordendecompra`) REFERENCES `tbl_ordencompra` (`orc_ordencompraid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_ordencompra_has_tbl_cubeta`
--

LOCK TABLES `tbl_ordencompra_has_tbl_cubeta` WRITE;
/*!40000 ALTER TABLE `tbl_ordencompra_has_tbl_cubeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_ordencompra_has_tbl_cubeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_produccion`
--

DROP TABLE IF EXISTS `tbl_produccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_produccion` (
  `pro_produccionid` int(11) NOT NULL AUTO_INCREMENT,
  `pro_fecha` date DEFAULT NULL,
  `pro_cantidadtotal` int(11) DEFAULT NULL,
  PRIMARY KEY (`pro_produccionid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_produccion`
--

LOCK TABLES `tbl_produccion` WRITE;
/*!40000 ALTER TABLE `tbl_produccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_produccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_produccion_has_tbl_cubeta`
--

DROP TABLE IF EXISTS `tbl_produccion_has_tbl_cubeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_produccion_has_tbl_cubeta` (
  `fk_produccionid` int(11) DEFAULT NULL,
  `fk_cubetaid` int(11) DEFAULT NULL,
  `prc_cantidad` int(11) DEFAULT NULL,
  KEY `fk_cubeta_idx` (`fk_cubetaid`),
  KEY `fk_producciont` (`fk_produccionid`),
  CONSTRAINT `fk_cubetat` FOREIGN KEY (`fk_cubetaid`) REFERENCES `tbl_cubeta` (`cub_cubetaid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_producciont` FOREIGN KEY (`fk_produccionid`) REFERENCES `tbl_produccion` (`pro_produccionid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_produccion_has_tbl_cubeta`
--

LOCK TABLES `tbl_produccion_has_tbl_cubeta` WRITE;
/*!40000 ALTER TABLE `tbl_produccion_has_tbl_cubeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_produccion_has_tbl_cubeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_rol`
--

DROP TABLE IF EXISTS `tbl_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_rol` (
  `rol_rolid` int(11) NOT NULL AUTO_INCREMENT,
  `rol_nombre` varchar(45) DEFAULT NULL,
  `rol_descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rol_rolid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_rol`
--

LOCK TABLES `tbl_rol` WRITE;
/*!40000 ALTER TABLE `tbl_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_usuario` (
  `usu_usuarioid` int(11) NOT NULL AUTO_INCREMENT,
  `usu_tipodocumento` varchar(45) DEFAULT NULL,
  `usu_numerodocumento` bigint(12) DEFAULT NULL,
  `usu_nombres` varchar(45) DEFAULT NULL,
  `usu_apellidos` varchar(45) DEFAULT NULL,
  `usu_correoelectronico` varchar(45) DEFAULT NULL,
  `usu_clave` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`usu_usuarioid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_usuario_has_tbl_rol`
--

DROP TABLE IF EXISTS `tbl_usuario_has_tbl_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbl_usuario_has_tbl_rol` (
  `fk_usuarioid` int(11) DEFAULT NULL,
  `fk_rolid` int(11) DEFAULT NULL,
  KEY `fk_usu_idx` (`fk_usuarioid`),
  KEY `fk_rol_idx` (`fk_rolid`),
  CONSTRAINT `fk_rol` FOREIGN KEY (`fk_rolid`) REFERENCES `tbl_rol` (`rol_rolid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usu` FOREIGN KEY (`fk_usuarioid`) REFERENCES `tbl_usuario` (`usu_usuarioid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario_has_tbl_rol`
--

LOCK TABLES `tbl_usuario_has_tbl_rol` WRITE;
/*!40000 ALTER TABLE `tbl_usuario_has_tbl_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_usuario_has_tbl_rol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-30 19:40:32
