SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `Productos` ;
CREATE SCHEMA IF NOT EXISTS `Productos` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Productos` ;

-- -----------------------------------------------------
-- Table `Productos`.`Productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Productos`.`Productos` (
  `idProductos` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Precio` VARCHAR(45) NOT NULL,
  `existencias` INT NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `origen` VARCHAR(45) NOT NULL,
  `imagen` MEDIUMBLOB,
  PRIMARY KEY (`idProductos`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
