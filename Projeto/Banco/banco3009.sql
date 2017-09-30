-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: apple
-- ------------------------------------------------------
-- Server version	5.7.19-log

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idCliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomeCliente` varchar(200) NOT NULL,
  `cpfCliente` bigint(20) unsigned NOT NULL,
  `celularCliente` varchar(20) DEFAULT NULL,
  `fotoCliente` varchar(100) DEFAULT NULL,
  `ruaCliente` varchar(100) DEFAULT NULL,
  `compCliente` varchar(20) DEFAULT NULL,
  `numeroCliente` varchar(10) DEFAULT NULL,
  `bairroCliente` varchar(45) DEFAULT NULL,
  `cidadeCliente` varchar(45) DEFAULT NULL,
  `cepCliente` bigint(20) DEFAULT NULL,
  `dataNascCliente` date DEFAULT NULL,
  `dataCadastro` date DEFAULT NULL,
  `telefoneCliente` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `cpfCliente_UNIQUE` (`cpfCliente`),
  UNIQUE KEY `idCliente_UNIQUE` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'1',1,'1','C:UsersCarlosDesktop\01.jpg','1','1','1','1','1',1,NULL,NULL,'1'),(2,'1',5,'1','C:UsersCarlosDesktop\01.jpg','1','1','1','1','1',1,NULL,NULL,'1'),(3,'1',1154260,'1','C:UsersCarlosDesktop\01.jpg','1','1','1','1','1',1,NULL,NULL,'1'),(4,'1',11542601606,'11','C:UsersCarlosDesktop\01.jpg','1','1','1','1','1',1,'2017-09-30','2017-09-30','1');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra` (
  `idCompra` int(11) NOT NULL AUTO_INCREMENT,
  `nomeFornec` varchar(45) NOT NULL,
  `cnpjFornec` varchar(45) NOT NULL,
  `dataCompra` date NOT NULL,
  `outros` float unsigned DEFAULT NULL,
  `valorTotal` float unsigned NOT NULL,
  PRIMARY KEY (`idCompra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra_has_produto`
--

DROP TABLE IF EXISTS `compra_has_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compra_has_produto` (
  `Compra_idCompra` int(11) NOT NULL,
  `Produto_idProduto` int(10) unsigned NOT NULL,
  `qtdControle` int(10) unsigned NOT NULL DEFAULT '0',
  `qtdCompra` int(10) unsigned NOT NULL DEFAULT '0',
  `precoCompra` float unsigned NOT NULL,
  PRIMARY KEY (`Compra_idCompra`,`Produto_idProduto`),
  KEY `fk_Compra_has_Produto_Produto1_idx` (`Produto_idProduto`),
  KEY `fk_Compra_has_Produto_Compra1_idx` (`Compra_idCompra`),
  CONSTRAINT `fk_Compra_has_Produto_Compra1` FOREIGN KEY (`Compra_idCompra`) REFERENCES `compra` (`idCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Compra_has_Produto_Produto1` FOREIGN KEY (`Produto_idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra_has_produto`
--

LOCK TABLES `compra_has_produto` WRITE;
/*!40000 ALTER TABLE `compra_has_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra_has_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conta`
--

DROP TABLE IF EXISTS `conta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conta` (
  `idConta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `aReceber` float unsigned NOT NULL DEFAULT '0',
  `totalCompras` float unsigned NOT NULL DEFAULT '0',
  `pago` float unsigned NOT NULL DEFAULT '0',
  `Cliente_idCliente` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idConta`),
  UNIQUE KEY `idConta_UNIQUE` (`idConta`),
  KEY `fk_Conta_Cliente1_idx` (`Cliente_idCliente`),
  CONSTRAINT `fk_Conta_Cliente1` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conta`
--

LOCK TABLES `conta` WRITE;
/*!40000 ALTER TABLE `conta` DISABLE KEYS */;
/*!40000 ALTER TABLE `conta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `idFuncionario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomeFunc` varchar(200) NOT NULL,
  `fotoFunc` varchar(100) DEFAULT NULL,
  `salarioFunc` double unsigned DEFAULT NULL,
  `comissaoFunc` double unsigned DEFAULT NULL,
  `cpfFunc` varchar(20) DEFAULT NULL,
  `ruaFunc` varchar(100) DEFAULT NULL,
  `compFunc` varchar(20) DEFAULT NULL,
  `numeroFunc` varchar(10) DEFAULT NULL,
  `bairroFunc` varchar(45) DEFAULT NULL,
  `cidadeFunc` varchar(45) DEFAULT NULL,
  `dataNascFunc` date DEFAULT NULL,
  `telefoneFunc` varchar(20) DEFAULT NULL,
  `celularFunc` varchar(20) DEFAULT NULL,
  `dataAdmissaoFunc` date DEFAULT NULL,
  `cepFunc` bigint(20) unsigned DEFAULT NULL,
  `senhaFunc` varchar(20) DEFAULT NULL,
  `administrador` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idFuncionario`),
  UNIQUE KEY `idFuncionario_UNIQUE` (`idFuncionario`),
  UNIQUE KEY `cpfFunc_UNIQUE` (`cpfFunc`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (10,'1','C:\\Users\\Carlos\\D',1,1,'1','1','1','1','1','1','2017-09-25','1','1','2017-09-25',1,'1',NULL),(12,'1','C:UsersCarlosDesktop\01.jpg',1,1,'2','1','1','1','1','1','2017-09-25','1','1','2017-09-25',1,'1',NULL),(13,'2','C:UsersCarlosDesktop\01.jpg',1,1,'4','1','1','1','1','1','2017-09-26','1','1','2017-09-26',1,'2',NULL),(16,'123','C:UsersCarlosDesktop\01.jpg',1,1,'7','1','1','1','11','1','2017-09-27','1','1','2017-09-27',1,'1',NULL),(17,'1','C:UsersCarlosDesktop\01.jpg',1,1,'10','1','1','1','1','1','2017-09-27','1','1','2017-09-27',1,'1',NULL),(19,'1','C:UsersCarlosDesktop\01.jpg',1,1,'13','1','1','1','1','1','2017-09-27','1','1','2017-09-27',1,'1',NULL),(20,'1','C:UsersCarlosDesktop\01.jpg',1,1,'15','1','1','1','1','1','2017-09-27','1','1','2017-09-27',1,'1',NULL),(21,'3','C:UsersCarlosDesktop\01.jpg',2,2,'20','2','2','2','2','2','2017-09-27','2','2','2017-09-27',2,'2',NULL),(22,'Carlos','C:UsersCarlosDesktop\01.jpg',10000,100,'12314','11','123','111','1122','safasfsaf','2017-09-27','1213242435','2132121421','2017-09-27',123,'123',NULL),(23,'1','C:UsersCarlosDesktop\01.jpg',1,1,'90','1','1','1','1','1','2017-09-30','1','2','2017-09-30',1,'1',NULL),(24,'1','C:UsersCarlosDesktop\01.jpg',1,1,'100','1','1','1','1','1','2017-09-30','1','1','2017-09-30',1,'1',NULL),(25,'1','C:UsersCarlosDesktop\01.jpg',1,1,'245325','11','1','1','1','1','2017-09-30','1','1','2017-09-30',1,'1',NULL),(28,'1','null',1,1,'232142','1','1','1','1','1','2017-09-30','1','1','2017-09-30',1,'1',NULL),(29,'1','C:UsersCarlosDesktop\01.jpg',1,1,'21421','1','1','1','1','1','2017-09-30','1','1','2017-09-30',1,'1',NULL),(30,'1','C:UsersCarlosDesktop\01.jpg',1,1,'23','1','1','1','1','1','2017-09-30','1','1','2017-09-30',1,'1',NULL),(31,'1','null',1,1,'21415','1','1','1','1','1','2017-09-30','1','1','2017-09-30',1,'1',NULL),(32,'carlos','null',100000,90,'11542601606','joao silver','1','155','esplanada','pouso alegre','2017-09-30','998504094','123456','2017-09-30',37552184,'123',NULL);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gerente`
--

DROP TABLE IF EXISTS `gerente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gerente` (
  `idGerente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomeGerente` varchar(200) NOT NULL,
  `fotoGerente` varchar(100) DEFAULT NULL,
  `salarioGerente` float unsigned NOT NULL,
  `comissaoGerente` float unsigned NOT NULL,
  `cpfGerente` bigint(20) unsigned NOT NULL,
  `ruaGerente` varchar(100) DEFAULT NULL,
  `compGerente` varchar(20) DEFAULT NULL,
  `numeroGerente` varchar(10) DEFAULT NULL,
  `bairroGerente` varchar(45) DEFAULT NULL,
  `cidadeGerente` varchar(45) NOT NULL,
  `dataNascGerente` date NOT NULL,
  `telefoneGerente` varchar(20) DEFAULT NULL,
  `celularGerente` varchar(20) DEFAULT NULL,
  `dataAdmissaoGerente` date NOT NULL,
  `cepGerente` bigint(20) unsigned NOT NULL,
  `senhaGerente` varchar(20) NOT NULL,
  PRIMARY KEY (`idGerente`),
  UNIQUE KEY `idFuncionario_UNIQUE` (`idGerente`),
  UNIQUE KEY `cpfFunc_UNIQUE` (`cpfGerente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gerente`
--

LOCK TABLES `gerente` WRITE;
/*!40000 ALTER TABLE `gerente` DISABLE KEYS */;
/*!40000 ALTER TABLE `gerente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `idPedido` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Cliente_idCliente` int(10) unsigned NOT NULL,
  `Funcionario_idFuncionario` int(10) unsigned NOT NULL,
  `dataPed` date NOT NULL,
  `precoPed` float unsigned NOT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `idPedido_UNIQUE` (`idPedido`),
  KEY `fk_Pedido_Cliente_idx` (`Cliente_idCliente`),
  KEY `fk_Pedido_Funcionario1_idx` (`Funcionario_idFuncionario`),
  CONSTRAINT `fk_Pedido_Cliente` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Funcionario1` FOREIGN KEY (`Funcionario_idFuncionario`) REFERENCES `funcionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_has_produto`
--

DROP TABLE IF EXISTS `pedido_has_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido_has_produto` (
  `Pedido_idPedido` int(10) unsigned NOT NULL,
  `Produto_idProduto` int(10) unsigned NOT NULL,
  `qtdVenda` int(10) unsigned NOT NULL DEFAULT '0',
  `qtdControle` int(10) unsigned NOT NULL DEFAULT '0',
  `precoUnitPed` float NOT NULL,
  PRIMARY KEY (`Pedido_idPedido`,`Produto_idProduto`),
  KEY `fk_Pedido_has_Produto_Produto1_idx` (`Produto_idProduto`),
  KEY `fk_Pedido_has_Produto_Pedido1_idx` (`Pedido_idPedido`),
  CONSTRAINT `fk_Pedido_has_Produto_Pedido1` FOREIGN KEY (`Pedido_idPedido`) REFERENCES `pedido` (`idPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_has_Produto_Produto1` FOREIGN KEY (`Produto_idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_has_produto`
--

LOCK TABLES `pedido_has_produto` WRITE;
/*!40000 ALTER TABLE `pedido_has_produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_has_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `idProduto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomeProduto` varchar(100) NOT NULL,
  `precoCompraProduto` float unsigned NOT NULL DEFAULT '0',
  `precoVendaProduto` float unsigned NOT NULL DEFAULT '0',
  `qtdEstoqueProduto` int(10) unsigned NOT NULL DEFAULT '0',
  `fotoProduto` varchar(100) DEFAULT NULL,
  `descricaoProduto` varchar(200) DEFAULT NULL,
  `ultimaDataCompraProduto` date DEFAULT NULL,
  `dataCadastroProduto` date DEFAULT NULL,
  PRIMARY KEY (`idProduto`),
  UNIQUE KEY `idProduto_UNIQUE` (`idProduto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'apple'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-30 17:02:40
