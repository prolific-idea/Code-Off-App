

SET FOREIGN_KEY_CHECKS = 0;

ALTER TABLE `dbo`.`appuserrole` 
CHANGE COLUMN `appUserRoleId` `appUserRoleId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '' ,
ADD UNIQUE INDEX `appUserRoleId_UNIQUE` (`appUserRoleId` ASC)  COMMENT '';

ALTER TABLE `dbo`.`technology` 
CHANGE COLUMN `techId` `techId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '' ,
ADD UNIQUE INDEX `techId_UNIQUE` (`techId` ASC)  COMMENT '';

ALTER TABLE `dbo`.`person` 
CHANGE COLUMN `personId` `personId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '' ,
ADD UNIQUE INDEX `personId_UNIQUE` (`personId` ASC)  COMMENT '';

ALTER TABLE `dbo`.`entry` 
CHANGE COLUMN `entryId` `entryId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '' ,
ADD UNIQUE INDEX `entryId_UNIQUE` (`entryId` ASC)  COMMENT '';

ALTER TABLE `dbo`.`challenge` 
CHANGE COLUMN `challengeId` `challengeId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '' ,
ADD UNIQUE INDEX `challengeId_UNIQUE` (`challengeId` ASC)  COMMENT '';

ALTER TABLE `dbo`.`appuserdetails` 
CHANGE COLUMN `appUserDetailsId` `appUserDetailsId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '' ,
ADD UNIQUE INDEX `appUserDetailsId_UNIQUE` (`appUserDetailsId` ASC)  COMMENT '';

ALTER TABLE `dbo`.`appuser` 
CHANGE COLUMN `appUserId` `appUserId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '' ,
ADD UNIQUE INDEX `appUserId_UNIQUE` (`appUserId` ASC)  COMMENT '';

ALTER TABLE `dbo`.`role` 
CHANGE COLUMN `roleId` `roleId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '' ;


SET FOREIGN_KEY_CHECKS = 1;

