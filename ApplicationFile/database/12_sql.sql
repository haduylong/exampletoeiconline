ALTER TABLE `toeiconline`.`exercise` 
DROP COLUMN `exercisetypeid`;

-- delete foregin key truoc

DROP TABLE `toeiconline`.`exercisetype`;

ALTER TABLE `toeiconline`.`exercise` 
ADD COLUMN `type` VARCHAR(45) NOT NULL AFTER `modifieddate`;