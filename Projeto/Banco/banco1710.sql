/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.7.19-log : Database - apple
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`apple` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `apple`;

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `idCliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomeCliente` varchar(200) NOT NULL,
  `cpfCliente` varchar(20) NOT NULL,
  `celularCliente` varchar(30) DEFAULT NULL,
  `ruaCliente` varchar(100) DEFAULT NULL,
  `compCliente` varchar(20) DEFAULT NULL,
  `numeroCliente` varchar(10) DEFAULT NULL,
  `bairroCliente` varchar(45) DEFAULT NULL,
  `cidadeCliente` varchar(45) DEFAULT NULL,
  `cepCliente` varchar(20) DEFAULT NULL,
  `dataNascCliente` varchar(20) DEFAULT NULL,
  `dataCadastro` date DEFAULT NULL,
  `telefoneCliente` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `cpfCliente_UNIQUE` (`cpfCliente`),
  UNIQUE KEY `idCliente_UNIQUE` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `cliente` */

insert  into `cliente`(`idCliente`,`nomeCliente`,`cpfCliente`,`celularCliente`,`ruaCliente`,`compCliente`,`numeroCliente`,`bairroCliente`,`cidadeCliente`,`cepCliente`,`dataNascCliente`,`dataCadastro`,`telefoneCliente`) values (9,'1','13161894650','1','1','1','1','1','1','1','11/11/1111','2017-10-10','1'),(11,'etset edicao','11542601606','1','1','1','1','1','1','1','11/11/1111','2017-10-11','1'),(12,'1','09431550610','1','1','1','1','1','1','1','11/11/1111','2017-10-11','1');

/*Table structure for table `compra` */

DROP TABLE IF EXISTS `compra`;

CREATE TABLE `compra` (
  `idCompra` int(11) NOT NULL AUTO_INCREMENT,
  `nomeFornecCompra` varchar(45) NOT NULL,
  `cnpjFornecCompra` varchar(45) NOT NULL,
  `dataCompra` varchar(20) NOT NULL,
  `outrosCompra` float unsigned DEFAULT NULL,
  `valorTotalCompra` float unsigned NOT NULL,
  `chaveAcessoCompra` varchar(50) DEFAULT NULL,
  `dataEntradaCompra` date NOT NULL,
  `numeroNFECompra` int(10) unsigned NOT NULL,
  `idFuncCompra` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idCompra`),
  KEY `idFuncCompra` (`idFuncCompra`),
  CONSTRAINT `compra_ibfk_1` FOREIGN KEY (`idFuncCompra`) REFERENCES `funcionario` (`idFuncionario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `compra` */

insert  into `compra`(`idCompra`,`nomeFornecCompra`,`cnpjFornecCompra`,`dataCompra`,`outrosCompra`,`valorTotalCompra`,`chaveAcessoCompra`,`dataEntradaCompra`,`numeroNFECompra`,`idFuncCompra`) values (1,'1','1','2017-10-04',1,1,'1','2017-10-10',1,1);

/*Table structure for table `compra_has_produto` */

DROP TABLE IF EXISTS `compra_has_produto`;

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

/*Data for the table `compra_has_produto` */

/*Table structure for table `conta` */

DROP TABLE IF EXISTS `conta`;

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

/*Data for the table `conta` */

/*Table structure for table `funcionario` */

DROP TABLE IF EXISTS `funcionario`;

CREATE TABLE `funcionario` (
  `idFuncionario` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomeFunc` varchar(200) NOT NULL,
  `salarioFunc` double unsigned DEFAULT NULL,
  `comissaoFunc` double unsigned DEFAULT NULL,
  `cpfFunc` varchar(20) DEFAULT NULL,
  `ruaFunc` varchar(100) DEFAULT NULL,
  `compFunc` varchar(20) DEFAULT NULL,
  `numeroFunc` varchar(10) DEFAULT NULL,
  `bairroFunc` varchar(45) DEFAULT NULL,
  `cidadeFunc` varchar(45) DEFAULT NULL,
  `dataNascFunc` varchar(20) DEFAULT NULL,
  `telefoneFunc` varchar(30) DEFAULT NULL,
  `celularFunc` varchar(30) DEFAULT NULL,
  `dataAdmissaoFunc` date DEFAULT NULL,
  `cepFunc` varchar(20) DEFAULT NULL,
  `senhaFunc` varchar(20) DEFAULT NULL,
  `administrador` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`idFuncionario`),
  UNIQUE KEY `idFuncionario_UNIQUE` (`idFuncionario`),
  UNIQUE KEY `cpfFunc_UNIQUE` (`cpfFunc`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `funcionario` */

insert  into `funcionario`(`idFuncionario`,`nomeFunc`,`salarioFunc`,`comissaoFunc`,`cpfFunc`,`ruaFunc`,`compFunc`,`numeroFunc`,`bairroFunc`,`cidadeFunc`,`dataNascFunc`,`telefoneFunc`,`celularFunc`,`dataAdmissaoFunc`,`cepFunc`,`senhaFunc`,`administrador`) values (1,'1',1,1,'1','1','1','1','1','1','18/04/1994','1','1','2017-09-30','1','1',1),(2,'teste edicao',1,1,'2','1','1','1','1','1','  /  /    ','1','1','2017-09-25','1','2',0),(46,'1',1,1,'13161894650','1','1','1','1','1','11/11/1111','1','1','2017-10-10','1','1',0),(47,'Carlos Henrique',9999999,10,'11542601606','rua qualquer','comp qualquer','0','bairro qualquer','cidade qualquer','11/11/1111','(35)3333-3333','(35)99999-9999','2017-10-11','37552-184','123456789',1);

/*Table structure for table `pedido` */

DROP TABLE IF EXISTS `pedido`;

CREATE TABLE `pedido` (
  `idPedido` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Cliente_idCliente` int(10) unsigned NOT NULL,
  `Funcionario_idFuncionario` int(10) unsigned NOT NULL,
  `dataPedido` date NOT NULL,
  `valorTotalPedido` float unsigned NOT NULL,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `idPedido_UNIQUE` (`idPedido`),
  KEY `fk_Pedido_Cliente_idx` (`Cliente_idCliente`),
  KEY `fk_Pedido_Funcionario1_idx` (`Funcionario_idFuncionario`),
  CONSTRAINT `fk_Pedido_Cliente` FOREIGN KEY (`Cliente_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_Funcionario1` FOREIGN KEY (`Funcionario_idFuncionario`) REFERENCES `funcionario` (`idFuncionario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `pedido` */

insert  into `pedido`(`idPedido`,`Cliente_idCliente`,`Funcionario_idFuncionario`,`dataPedido`,`valorTotalPedido`) values (1,9,1,'2017-10-17',0),(2,11,47,'2017-10-17',40),(3,11,47,'2017-10-17',20),(4,11,47,'2017-10-17',0),(5,11,47,'2017-10-17',40),(6,11,47,'2017-10-17',40),(7,9,2,'2017-10-17',40),(8,9,1,'2017-10-17',0),(9,9,1,'2017-10-17',10),(10,9,1,'2017-10-17',0),(11,9,1,'2017-10-17',10),(12,9,1,'2017-10-17',10),(13,9,1,'2017-10-17',40),(14,11,47,'2017-10-17',90),(15,11,2,'2017-10-17',20),(16,9,1,'2017-10-17',0),(17,9,1,'2017-10-17',20),(18,12,2,'2017-10-17',40);

/*Table structure for table `pedido_has_produto` */

DROP TABLE IF EXISTS `pedido_has_produto`;

CREATE TABLE `pedido_has_produto` (
  `Pedido_idPedido` int(10) unsigned NOT NULL,
  `Produto_idProduto` int(10) unsigned NOT NULL,
  `qtdVenda` int(10) unsigned NOT NULL DEFAULT '0',
  `qtdControle` int(10) unsigned NOT NULL DEFAULT '0',
  `precoUnitItem` float unsigned NOT NULL,
  `precoTotalItem` float unsigned NOT NULL,
  PRIMARY KEY (`Pedido_idPedido`,`Produto_idProduto`),
  KEY `fk_Pedido_has_Produto_Produto1_idx` (`Produto_idProduto`),
  KEY `fk_Pedido_has_Produto_Pedido1_idx` (`Pedido_idPedido`),
  CONSTRAINT `fk_Pedido_has_Produto_Pedido1` FOREIGN KEY (`Pedido_idPedido`) REFERENCES `pedido` (`idPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Pedido_has_Produto_Produto1` FOREIGN KEY (`Produto_idProduto`) REFERENCES `produto` (`idProduto`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pedido_has_produto` */

insert  into `pedido_has_produto`(`Pedido_idPedido`,`Produto_idProduto`,`qtdVenda`,`qtdControle`,`precoUnitItem`,`precoTotalItem`) values (13,1,2,0,10,20),(13,2,1,0,20,20),(14,1,5,0,10,50),(14,2,2,0,20,40),(15,1,2,0,10,20),(17,1,2,2,10,20),(18,2,2,2,20,40);

/*Table structure for table `produto` */

DROP TABLE IF EXISTS `produto`;

CREATE TABLE `produto` (
  `idProduto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nomeProduto` varchar(100) NOT NULL,
  `precoCompraProduto` float unsigned NOT NULL DEFAULT '0',
  `precoVendaProduto` float unsigned NOT NULL DEFAULT '0',
  `qtdEstoqueProduto` int(10) unsigned NOT NULL DEFAULT '0',
  `descricaoProduto` varchar(200) DEFAULT NULL,
  `ultimaDataCompraProduto` date DEFAULT NULL,
  `dataCadastroProduto` date DEFAULT NULL,
  `percLucro` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`idProduto`),
  UNIQUE KEY `idProduto_UNIQUE` (`idProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `produto` */

insert  into `produto`(`idProduto`,`nomeProduto`,`precoCompraProduto`,`precoVendaProduto`,`qtdEstoqueProduto`,`descricaoProduto`,`ultimaDataCompraProduto`,`dataCadastroProduto`,`percLucro`) values (1,'Prod1',0,10,5,'kjiasoidoasj',NULL,'2017-10-17',15),(2,'prod2',0,20,2,'sadasdoijowhqeiu	',NULL,'2017-10-17',20);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
