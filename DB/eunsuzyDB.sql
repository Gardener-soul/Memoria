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
SELECT *
FROM Users;

DROP TABLE RollingPapers;
CREATE TABLE RollingPapers (
    rollingPaperNo INT NOT NULL AUTO_INCREMENT,
    owner INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    writerCount INT DEFAULT 0,
    regDate DATETIME DEFAULT CURRENT_TIMESTAMP,
    -- emoticon VARCHAR(200),
    PRIMARY KEY (rollingPaperNo),
    FOREIGN KEY (owner) REFERENCES Users(userNo)
)ENGINE=InnoDB;
SELECT *
FROM RollingPapers;

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
FROM Messages;

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