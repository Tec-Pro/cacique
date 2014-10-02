USE cacique;
ALTER TABLE `cacique`.`corrientes` ADD COLUMN `fecha_pago` DATE NULL DEFAULT NULL  AFTER `fecha` ;
ALTER TABLE `cacique`.`clientes` ADD COLUMN `cuenta_corriente_manual` FLOAT NULL DEFAULT 0  AFTER `cuenta` ;