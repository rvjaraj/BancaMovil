/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.1.9-MariaDB : Database - archivos
*********************************************************************
*/
CREATE DATABASE archivospdf;

USE `archivospdf`;

/*Table structure for table `pdf` */

DROP TABLE IF EXISTS `pdf`;

CREATE TABLE `pdf` (
  `codigopdf` int(10) NOT NULL AUTO_INCREMENT,
  `nombrepdf` varchar(50) DEFAULT NULL,
  `archivopdf` mediumblob,
  PRIMARY KEY (`codigopdf`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `pdf` */

insert  into `pdf`(`codigopdf`,`nombrepdf`,`archivopdf`) values (8,'Turismo Selva',NULL);

