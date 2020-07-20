-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema sistemabancario
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sistemabancario
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sistemabancario` DEFAULT CHARACTER SET utf8 ;
USE `sistemabancario` ;

-- -----------------------------------------------------
-- Table `sistemabancario`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistemabancario`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `sistemabancario`.`Cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cedula` VARCHAR(45) NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `fechNac` DATE NOT NULL,
  `estadocivil` TINYINT NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `contracenia` VARCHAR(45) NOT NULL,
  `eliminado` TINYINT NULL DEFAULT 0,
  `activo` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistemabancario`.`Cuenta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistemabancario`.`Cuenta` ;

CREATE TABLE IF NOT EXISTS `sistemabancario`.`Cuenta` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(45) NOT NULL,
  `saldo` DOUBLE NOT NULL,
  `fecha` DATE NOT NULL,
  `eliminado` TINYINT NULL,
  `cliente` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `numero_UNIQUE` (`numero` ASC),
  INDEX `fk_Cuenta_Cliente1_idx` (`cliente` ASC),
  CONSTRAINT `fk_Cuenta_Cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `sistemabancario`.`Cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistemabancario`.`Credito`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistemabancario`.`Credito` ;

CREATE TABLE IF NOT EXISTS `sistemabancario`.`Credito` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `plazo` INT NOT NULL,
  `proposito` VARCHAR(500) NOT NULL,
  `cantidad` DOUBLE NOT NULL,
  `estado` VARCHAR(45) NULL,
  `cuenta` INT UNSIGNED NOT NULL,
  `elimado` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Credito_Cuenta1_idx` (`cuenta` ASC),
  CONSTRAINT `fk_Credito_Cuenta1`
    FOREIGN KEY (`cuenta`)
    REFERENCES `sistemabancario`.`Cuenta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistemabancario`.`Amortizacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistemabancario`.`Amortizacion` ;

CREATE TABLE IF NOT EXISTS `sistemabancario`.`Amortizacion` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `periodo` INT NOT NULL,
  `couta` DOUBLE NOT NULL,
  `interes` DOUBLE NOT NULL,
  `total` DOUBLE NOT NULL,
  `deuda` DOUBLE NOT NULL,
  `estado` TINYINT NOT NULL,
  `fecha` DATE NOT NULL,
  `credito` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Amortizacion_Credito1_idx` (`credito` ASC),
  CONSTRAINT `fk_Amortizacion_Credito1`
    FOREIGN KEY (`credito`)
    REFERENCES `sistemabancario`.`Credito` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistemabancario`.`Trabajador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistemabancario`.`Trabajador` ;

CREATE TABLE IF NOT EXISTS `sistemabancario`.`Trabajador` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cedula` VARCHAR(45) NOT NULL,
  `nombres` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `contracenia` VARCHAR(45) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  `sueldo` DOUBLE NOT NULL,
  `eliminado` TINYINT NULL DEFAULT 0,
  `activo` TINYINT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cedula_UNIQUE` (`cedula` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistemabancario`.`Solicitud`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistemabancario`.`Solicitud` ;

CREATE TABLE IF NOT EXISTS `sistemabancario`.`Solicitud` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cliente` INT NOT NULL,
  `plazo` INT NOT NULL,
  `historial` VARCHAR(45) NOT NULL,
  `proposito` VARCHAR(500) NOT NULL,
  `cantidad` DOUBLE NOT NULL,
  `saldocuenta` VARCHAR(45) NOT NULL,
  `tiempoempleo` DOUBLE NOT NULL,
  `tasadepagos` DOUBLE NOT NULL,
  `estadocivil` VARCHAR(45) NOT NULL,
  `garante` VARCHAR(45) NOT NULL,
  `avaluovivienda` DOUBLE NOT NULL,
  `activos` VARCHAR(45) NOT NULL,
  `edad` INT NOT NULL,
  `tipovivienda` VARCHAR(45) NOT NULL,
  `numerocreditos` INT NOT NULL,
  `empleo` VARCHAR(45) NOT NULL,
  `trebajadorextrangero` TINYINT NOT NULL,
  `tipocliente` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  `elimado` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_Solicitud_Cliente1_idx` (`cliente` ASC),
  CONSTRAINT `fk_Solicitud_Cliente1`
    FOREIGN KEY (`cliente`)
    REFERENCES `sistemabancario`.`Cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistemabancario`.`transferencias`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistemabancario`.`transferencias` ;

CREATE TABLE IF NOT EXISTS `sistemabancario`.`transferencias` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `cantidad` DOUBLE NOT NULL,
  `concepto` VARCHAR(45) NOT NULL,
  `Ordenante` INT UNSIGNED NOT NULL,
  `Beneficiario` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transferencias_Cuenta1_idx` (`Ordenante` ASC),
  INDEX `fk_transferencias_Cuenta2_idx` (`Beneficiario` ASC),
  CONSTRAINT `fk_transferencias_Cuenta1`
    FOREIGN KEY (`Ordenante`)
    REFERENCES `sistemabancario`.`Cuenta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transferencias_Cuenta2`
    FOREIGN KEY (`Beneficiario`)
    REFERENCES `sistemabancario`.`Cuenta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistemabancario`.`Transaciones`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistemabancario`.`Transaciones` ;

CREATE TABLE IF NOT EXISTS `sistemabancario`.`Transaciones` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(45) NULL,
  `cantidad` VARCHAR(45) NULL,
  `fecha` DATE NULL,
  `Cuenta_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Transaciones_Cuenta1_idx` (`Cuenta_id` ASC),
  CONSTRAINT `fk_Transaciones_Cuenta1`
    FOREIGN KEY (`Cuenta_id`)
    REFERENCES `sistemabancario`.`Cuenta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sistemabancario`.`ALogin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sistemabancario`.`ALogin` ;

CREATE TABLE IF NOT EXISTS `sistemabancario`.`ALogin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `ip` VARCHAR(45) NOT NULL,
  `acceso` TINYINT NOT NULL,
  `Cliente` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ALogin_Cliente1_idx` (`Cliente` ASC),
  CONSTRAINT `fk_ALogin_Cliente1`
    FOREIGN KEY (`Cliente`)
    REFERENCES `sistemabancario`.`Cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
