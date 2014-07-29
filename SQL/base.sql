drop database if exists cacique;
create database cacique;

use cacique;

CREATE  TABLE `cacique`.`clientes` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(100) NOT NULL ,
  `telefono` VARCHAR(60) NULL ,
  `celular` VARCHAR(60) NULL ,
  `direccion` VARCHAR(120) NULL ,
  `nacimiento` DATE NULL ,
  `facebook` VARCHAR(60) NULL ,
  `email` VARCHAR(60) NULL ,
  `dni` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE `cacique`.`proveedors` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nombre` VARCHAR(100) NOT NULL ,
  `cuit` VARCHAR(45) NULL ,
  `direccion` VARCHAR(100) NULL ,
  `telefono` VARCHAR(45) NULL ,
  `celular` VARCHAR(45) NULL ,
  `email` VARCHAR(45) NULL ,
  `forma_de_pago` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) );

