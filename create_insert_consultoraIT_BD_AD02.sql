SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `consultoraIT` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci ;
USE `consultoraIT` ;

-- -----------------------------------------------------
-- Table `consultoraIT`.`departamentos`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `consultoraIT`.`departamentos` (
  `dept_no` TINYINT(2) NOT NULL ,
  `dnombre` VARCHAR(20) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL ,
  `loc` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`dept_no`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


-- -----------------------------------------------------
-- Table `consultoraIT`.`empleados`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `consultoraIT`.`empleados` (
  `emp_no` SMALLINT(4) UNSIGNED NOT NULL ,
  `apellido` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL ,
  `oficio` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_spanish_ci' NULL DEFAULT NULL ,
  `dir` SMALLINT(6) NULL DEFAULT NULL ,
  `fecha_alt` DATE NULL DEFAULT NULL ,
  `salario` FLOAT(6,2) NULL DEFAULT NULL ,
  `comision` FLOAT(6,2) NULL DEFAULT NULL ,
  `dept_no` TINYINT(2) NOT NULL ,
  PRIMARY KEY (`emp_no`) ,
  INDEX `dept_no` (`dept_no` ASC) ,
  CONSTRAINT `dept_no`
    FOREIGN KEY (`dept_no`)
    REFERENCES `consultoraIT`.`departamentos` (`dept_no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_spanish_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



-- -----------------------------------------------------
-- Inserta los departamentos 
-- -----------------------------------------------------
INSERT INTO departamentos VALUES
(10,'SISTEMAS Y SEGURIDAD','SEVILLA'),
(20,'DESARROLLO','GRANADA'),
(30,'PRUEBAS','JAÉN'),
(40,'DIRECCIÓN','ALMERÍA'),
(50,'VENTAS','ANDALUCÍA');

-- -----------------------------------------------------
-- Inserta los empleados
-- -----------------------------------------------------
INSERT INTO empleados VALUES
(1000,'ANTUNEZ','ANALISTA',1,20180201,1600.00,10.00,20),
(2000,'CASINELLO','ADMIN.SIS.',2,20190510,1750.00,0.00,10),
(3000,'LOPEZ','DES.WEB',3,20190609,1480.00,10.00,20),
(4000,'GARCIA','DES.MOV.',4,20200915,1500.00,10.00,20),
(5000,'FERNANDEZ','TESTER',5,20200831,1450.00,5.00,30),
(6000,'DE FRUTOS','GERENTE',6,20171210,3000.00,20.00,40),
(7000,'GONZALEZ','COMERCIAL',7,20180301,1900.00,10.00,50),
(8000,'BELZUNCES','ADMIN.SEG.',8,20180103,1850.00,0.00,10);