use cacique;
ALTER TABLE `cacique`.`articulos` ADD COLUMN `es_articulo` INT(1) NULL DEFAULT 1  AFTER `equivalencia_3` ;
ALTER TABLE `cacique`.`articulos` CHANGE COLUMN `codigo` `codigo` VARCHAR(45) NULL  ;
