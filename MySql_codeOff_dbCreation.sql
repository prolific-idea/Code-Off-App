-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: dbo
-- Source Schemata: dbo
-- Created: Fri Feb 26 12:26:58 2016
-- Workbench Version: 6.3.4
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema dbo
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `dbo` ;
CREATE SCHEMA IF NOT EXISTS `dbo` ;

-- ----------------------------------------------------------------------------
-- Table dbo.sysdiagrams
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`sysdiagrams` (
  `name` VARCHAR(160) NOT NULL COMMENT '',
  `principal_id` INT NOT NULL COMMENT '',
  `diagram_id` INT NOT NULL AUTO_INCREMENT COMMENT '',
  `version` INT NULL COMMENT '',
  `definition` LONGBLOB NULL COMMENT '',
  PRIMARY KEY (`diagram_id`)  COMMENT '',
  UNIQUE INDEX `UK_principal_name` (`principal_id` ASC, `name` ASC)  COMMENT '');

-- ----------------------------------------------------------------------------
-- Table dbo.Person
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Person` (
  `personId` INT NOT NULL COMMENT '',
  `username` VARCHAR(50) NOT NULL COMMENT '',
  `firstName` VARCHAR(50) NULL COMMENT '',
  `lastName` VARCHAR(50) NULL COMMENT '',
  `score` INT NOT NULL COMMENT '',
  `url` VARCHAR(50) NULL COMMENT '',
  PRIMARY KEY (`personId`)  COMMENT '');

-- ----------------------------------------------------------------------------
-- Table dbo.Challenge
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Challenge` (
  `challengeId` INT NOT NULL COMMENT '',
  `solution` LONGBLOB NOT NULL COMMENT '',
  `url` VARCHAR(100) NOT NULL COMMENT '',
  `solutionFilePath` VARCHAR(100) NULL COMMENT '',
  `startDate` DATE NULL COMMENT '',
  `endDate` DATE NOT NULL COMMENT '',
  PRIMARY KEY (`challengeId`)  COMMENT '');

-- ----------------------------------------------------------------------------
-- Table dbo.Entry
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Entry` (
  `entryId` INT NOT NULL COMMENT '',
  `solution` LONGBLOB NULL COMMENT '',
  `date` DATE NOT NULL COMMENT '',
  `personId` INT NOT NULL COMMENT '',
  `techId` INT NOT NULL COMMENT '',
  `url` VARCHAR(100) NULL COMMENT '',
  `challengeId` INT NOT NULL COMMENT '',
  `result` INT NULL COMMENT '',
  PRIMARY KEY (`entryId`)  COMMENT '',
  CONSTRAINT `FK_Entry_Technology`
    FOREIGN KEY (`techId`)
    REFERENCES `dbo`.`Technology` (`techId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Entry_Person`
    FOREIGN KEY (`personId`)
    REFERENCES `dbo`.`Person` (`personId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Entry_Challenge`
    FOREIGN KEY (`challengeId`)
    REFERENCES `dbo`.`Challenge` (`challengeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.AppUserDetails
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`AppUserDetails` (
  `appUserDetailsId` INT NOT NULL COMMENT '',
  `firstName` VARCHAR(50) NULL COMMENT '',
  `lastName` VARCHAR(50) NULL COMMENT '',
  `emailAddress` VARCHAR(100) NULL COMMENT '',
  PRIMARY KEY (`appUserDetailsId`)  COMMENT '');

-- ----------------------------------------------------------------------------
-- Table dbo.Role
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Role` (
  `roleId` INT NOT NULL COMMENT '',
  `description` VARCHAR(50) NULL COMMENT '',
  PRIMARY KEY (`roleId`)  COMMENT '');

-- ----------------------------------------------------------------------------
-- Table dbo.AppUserRole
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`AppUserRole` (
  `appUserRoleId` INT NOT NULL COMMENT '',
  `appUserId` INT NOT NULL COMMENT '',
  `roleId` INT NOT NULL COMMENT '',
  PRIMARY KEY (`appUserRoleId`)  COMMENT '',
  CONSTRAINT `FK_AppUserRole_Role`
    FOREIGN KEY (`roleId`)
    REFERENCES `dbo`.`Role` (`roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_AppUserRole_AppUser`
    FOREIGN KEY (`appUserId`)
    REFERENCES `dbo`.`AppUser` (`appUserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.AppUser
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`AppUser` (
  `appUserId` INT NOT NULL COMMENT '',
  `appUserDetailsId` INT NOT NULL COMMENT '',
  `username` VARCHAR(50) NOT NULL COMMENT '',
  `password` VARBINARY(100) NULL COMMENT '',
  `enabled` TINYINT(1) NULL COMMENT '',
  PRIMARY KEY (`appUserId`)  COMMENT '',
  CONSTRAINT `FK_AppUser_AppUserDetails`
    FOREIGN KEY (`appUserDetailsId`)
    REFERENCES `dbo`.`AppUserDetails` (`appUserDetailsId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- ----------------------------------------------------------------------------
-- Table dbo.Technology
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `dbo`.`Technology` (
  `techId` INT NOT NULL COMMENT '',
  `description` VARCHAR(50) NULL COMMENT '',
  PRIMARY KEY (`techId`)  COMMENT '');
SET FOREIGN_KEY_CHECKS = 1;
