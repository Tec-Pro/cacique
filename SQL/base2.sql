use cacique;

CREATE  TABLE `cacique`.`corrientes` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `id_cliente` INT NULL ,
  `id_venta` INT NULL ,
  `descripcion` VARCHAR(50000) NULL ,
  `monto` float NULL ,
  PRIMARY KEY (`id`) );
ALTER TABLE `cacique`.`corrientes` CHANGE COLUMN `haber` `haber` FLOAT NULL DEFAULT '0'  ;