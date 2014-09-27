use cacique;
ALTER TABLE `cacique`.`corrientes` ADD COLUMN `fecha` DATE NULL  AFTER `haber` ;

UPDATE `cacique`.`demos` SET `activado`=0 WHERE `id`='1';