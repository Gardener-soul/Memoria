DROP DATABASE IF EXISTS eunsuzyDB;

CREATE DATABASE eunsuzyDB CHARACTER SET=utf8mb4;

USE eunsuzyDB;

DROP TABLE IF EXISTS eunsuzyDB;

DROP TABLE Users;
CREATE TABLE `Users` (
	`userNo` INT NOT NULL AUTO_INCREMENT,
    `userId` VARCHAR(30) UNIQUE NOT NULL,
    `userPwd` VARCHAR(100) NOT NULL,
    `userName` VARCHAR(20) NOT NULL,
    `email` VARCHAR(50) UNIQUE NOT NULL,
--     `nickname` VARCHAR(20) UNIQUE NOT NULL,
    `regDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `reportCount` INT DEFAULT 0,
    PRIMARY KEY(`userNo`)
)ENGINE=InnoDB;
SELECT *
FROM Users;

DROP TABLE RollingPapers;
CREATE TABLE `RollingPapers` (
    `rollingPaperNo` INT NOT NULL AUTO_INCREMENT,
    `owner` INT NOT NULL,
    `title` VARCHAR(100) NOT NULL,
    `writerCount` INT DEFAULT 0,
    `regDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    -- emoticon VARCHAR(200),
    PRIMARY KEY (`rollingPaperNo`),
    FOREIGN KEY (`owner`) REFERENCES Users(`userNo`)
)ENGINE=InnoDB;
SELECT *
FROM RollingPapers;

DROP TABLE Messages;
CREATE TABLE `Messages` (
    `messageNo` INT NOT NULL AUTO_INCREMENT,
    `rollingPaperNo` INT NOT NULL,
    `writer` INT NOT NULL,
    `content` TEXT NOT NULL,
    `font` VARCHAR(50) DEFAULT 'Arial, sans-serif',
    `fontColor` VARCHAR(50) DEFAULT '#000000',
    `backColor` VARCHAR(50) DEFAULT '#FFFFFF',
    `regDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`messageNo`),
    FOREIGN KEY (`rollingPaperNo`) REFERENCES RollingPapers(`rollingPaperNo`),
    FOREIGN KEY (`writer`) REFERENCES Users(`userNo`)
)ENGINE=InnoDB;
SELECT *
FROM Messages;

DROP TABLE Events;
CREATE TABLE `Events` (
  `eventNo` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `userNo` INT NOT NULL,
  `eventTitle` VARCHAR(50),
  `description` VARCHAR(200),
  `achieve` INT DEFAULT 0,
  `achieveDate` DATETIME DEFAULT 0,
  FOREIGN KEY (`userNo`) REFERENCES Users(`userNo`)
)ENGINE=InnoDB;
SELECT *
FROM Events;

-- DROP TABLE Photo;
-- CREATE TABLE `Photos` (
--     MessageID INT NOT NULL AUTO_INCREMENT,
--     RollingPaperID INT NOT NULL,
--     Writer INT NOT NULL,
--     Content TEXT NOT NULL,
--     Font VARCHAR(50) DEFAULT 'Arial, sans-serif',
--     fontColor,
--     backColor VARCHAR(50) DEFAULT '#FFFFFF',
--     REGDATE DATETIME DEFAULT CURRENT_TIMESTAMP,
--     PRIMARY KEY (MessageID),
--     FOREIGN KEY (RollingPaperID) REFERENCES RollingPapers(RollingPaperID),
--     FOREIGN KEY (Writer) REFERENCES Users(UserNO)
-- )ENGINE=InnoDB;
-- SELECT *
-- FROM photo;



INSERT INTO `Users` (userId, userPwd, userName, email, nickname)
VALUES
('eunsooo', '97', '박은수', 'suzy@naver.com', 'soul'),
('suuzy', '98', '이수지', 'eunsu@naver.com', 'dubbidubbab'),
('HunTeac', '9898', '한재훈', 'jaehun@naver.com', 'hahahan');

INSERT INTO `RollingPapers` (owner, title)
VALUES
(1, '박은수의 롤링페이퍼'),
(2, '이수지의 롤링페이퍼'),
(3, '한재훈의 롤링페이퍼');

