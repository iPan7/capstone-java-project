--CREATE SCHEMA IF NOT EXISTS fistToFive;
--USE fistToFive;
--
--DROP TABLE IF EXISTS activities;
--
--CREATE TABLE activities(id INT PRIMARY KEY AUTO_INCREMENT, activityName VARCHAR(100), filePath VARCHAR(100), activityDescription VARCHAR(100));
----CREATE TABLE ratings(id INT PRIMARY KEY AUTO_INCREMENT, rating INT, username VARCHAR(100), FOREIGN KEY(activityId) REFERENCES activities(Id)
----    ON DELETE CASCADE);
--
--INSERT INTO `ACTIVITY` (ACTIVITY, FILEPATH, ACTIVITYDESCRIPTION) VALUES ('Micro Services 101','GitLab/Module2/Day3/MicroServices','Get the deets on Microservice yo!');
----INSERT INTO `ACTIVITY` (ID, ACTIVITY, FILEPATH, ACTIVITYDESCRIPTION, RATINGS) VALUES (2, 'Inheritance','GitLab/Module2/Day3/Inheritance','Learn about things with an IS A relationship!', NULL);
----INSERT INTO `ACTIVITY` (ID, ACTIVITY, FILEPATH, ACTIVITYDESCRIPTION, RATINGS) VALUES (3, 'Pop Culture','GitLab/Module2/Day3/Pop','Its time to get the scoop on pop!', NULL);
