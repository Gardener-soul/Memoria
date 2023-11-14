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
    `nickname` VARCHAR(20) UNIQUE NOT NULL,
    `regDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `reportCount` INT DEFAULT 0,
    PRIMARY KEY(`UserNO`)
)ENGINE=InnoDB;

DROP TABLE RollingPapers;
CREATE TABLE RollingPapers (
    RollingPaperID INT NOT NULL AUTO_INCREMENT,
    Title VARCHAR(100) NOT NULL,
    Owner INT NOT NULL,
    WriterCount INT DEFAULT 0,
    REGDATE DATETIME DEFAULT CURRENT_TIMESTAMP,
    EMOTICON VARCHAR(200),
    PRIMARY KEY (RollingPaperID),
    FOREIGN KEY (Owner) REFERENCES Users(UserNO)
)ENGINE=InnoDB;

DROP TABLE Messages;
CREATE TABLE Messages (
    MessageID INT NOT NULL AUTO_INCREMENT,
    RollingPaperID INT NOT NULL,
    Writer INT NOT NULL,
    Content TEXT NOT NULL,
    BackColor VARCHAR(50) DEFAULT '#FFFFFF',
    Font VARCHAR(50) DEFAULT 'Arial, sans-serif',
    REGDATE DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (MessageID),
    FOREIGN KEY (RollingPaperID) REFERENCES RollingPapers(RollingPaperID),
    FOREIGN KEY (Writer) REFERENCES Users(UserNO)
)ENGINE=InnoDB;

SELECT *
FROM users;

SELECT userId, userPwd, userName, email, nickname
FROM users
WHERE userId = 'suuzy';

INSERT INTO `Users` (userId, userPwd, userName, email, nickname)
VALUES
('eunsooo', '97', '박은수', 'suzy', 'soul'),
('suuzy', '98', '이수지', 'eunsu', 'dubbidubbab');

