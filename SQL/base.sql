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

CREATE  TABLE `cacique`.`articulos` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `codigo` VARCHAR(45) NOT NULL ,
  `nombre` VARCHAR(45) NULL ,
  `marca` VARCHAR(45) NULL ,
  `stock_actual` INT NULL DEFAULT 0 ,
  `stock_minimo` INT NULL DEFAULT 0 ,
  `precio_compra` FLOAT NULL DEFAULT 0 ,
  `precio_venta` FLOAT NULL DEFAULT 0 ,
  `descripcion` VARCHAR(200) NULL ,
  `ultima_compra` DATE NULL ,
  `proveedor_id` INT NULL ,
  PRIMARY KEY (`id`, `codigo`) );

create table usuarios (
	id integer not null auto_increment,
	nombre varchar(50) default 'cacique',
	pass varchar(50) default 'cacique',
	primary key(id));

CREATE  TABLE `cacique`.`emails` (
  `email` VARCHAR(45) NOT NULL ,
  `password` VARCHAR(45) NULL ,
  PRIMARY KEY (`email`) );


ALTER TABLE `cacique`.`emails` ADD COLUMN `id` VARCHAR(45) NOT NULL  AFTER `password` 
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`email`, `id`) ;


ALTER TABLE `cacique`.`emails` CHANGE COLUMN `id` `id` INT NOT NULL AUTO_INCREMENT  
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`id`, `email`) ;

/*Versión 2.0 !*/
CREATE  TABLE `cacique`.`ips` (
  `id` INT NOT NULL ,
  `remoto` VARCHAR(45) NULL ,
  `servidor` INT(2) NULL ,
  PRIMARY KEY (`id`) );
ALTER TABLE `cacique`.`ips` CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT  ;
ALTER TABLE `cacique`.`ips` CHANGE COLUMN `remoto` `remoto` VARCHAR(45) NULL DEFAULT 'localhost'  , CHANGE COLUMN `servidor` `servidor` INT(2) NULL DEFAULT 0  ;


 GRANT ALL PRIVILEGES ON *.* TO 'tecpro'@'%'  IDENTIFIED BY 'tecpro'; 
 GRANT ALL PRIVILEGES ON *.* TO 'tecpro'@'localhost'  IDENTIFIED BY 'tecpro' WITH GRANT OPTION; 

CREATE  TABLE `cacique`.`envios` (
  `fecha` DATE NOT NULL ,
  `enviado` VARCHAR(5) NULL ,
  PRIMARY KEY (`fecha`) );
ALTER TABLE `cacique`.`envios` ADD COLUMN `id` INT NOT NULL AUTO_INCREMENT  AFTER `enviado` 
, DROP PRIMARY KEY 
, ADD PRIMARY KEY (`id`, `fecha`) , RENAME TO  `cacique`.`envios` ;

ALTER TABLE `cacique`.`envios` CHANGE COLUMN `enviado` `enviado` INT NULL DEFAULT NULL  ;
