-- set not null
ALTER TABLE `toeiconline`.`examinationquestion` 
CHANGE COLUMN `question` `question` TEXT NULL ,
CHANGE COLUMN `option1` `option1` VARCHAR(300) NULL ,
CHANGE COLUMN `option2` `option2` VARCHAR(300) NULL ,
CHANGE COLUMN `option3` `option3` VARCHAR(300) NULL ,
CHANGE COLUMN `option4` `option4` VARCHAR(300) NULL ,
CHANGE COLUMN `correctanswer` `correctanswer` VARCHAR(10) NULL ;