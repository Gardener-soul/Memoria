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
	`img` VARCHAR(200),
	`orgImg` VARCHAR(200),
	`regDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
	`reportCount` INT DEFAULT 0,
	PRIMARY KEY(`userNo`)
)ENGINE=InnoDB;
SELECT *
FROM Users;

DROP TABLE RollingPapers;
CREATE TABLE `RollingPapers` (
	`rollingPaperNo` INT NOT NULL AUTO_INCREMENT,
	`ownerNo` INT NOT NULL,
	`title` VARCHAR(100) NOT NULL,
	`writerCount` INT DEFAULT 0,
	`regDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
	-- emoticon VARCHAR(200),
	PRIMARY KEY (`rollingPaperNo`),
	FOREIGN KEY (`ownerNo`) REFERENCES Users(`userNo`)
)ENGINE=InnoDB;
SELECT *
FROM RollingPapers;

DROP TABLE Letters;
CREATE TABLE `Letters` (
	`letterNo` INT NOT NULL AUTO_INCREMENT,
	`rollingPaperNo` INT NOT NULL,
	`writerNo` INT NOT NULL,
	`content` TEXT NOT NULL,
	`img` VARCHAR(200),
	`orgImg` VARCHAR(200),
	`font` VARCHAR(50) DEFAULT 'Arial, sans-serif',
	`fontColor` VARCHAR(50) DEFAULT '#000000',
	`backColor` VARCHAR(50) DEFAULT '#FFFFFF',
	`regDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (`letterNo`),
	FOREIGN KEY (`rollingPaperNo`) REFERENCES RollingPapers(`rollingPaperNo`),
	FOREIGN KEY (`writerNo`) REFERENCES Users(`userNo`)
)ENGINE=InnoDB;
SELECT *
FROM Letters;

-- DROP TABLE Photos;
-- CREATE TABLE `Photos` (
-- 	`photoNo` INT NOT NULL AUTO_INCREMENT,
-- 	`rollingPaperNo` INT NOT NULL,
-- 	`writerNo` INT NOT NULL,
-- 	`img` VARCHAR(200) NOT NULL,
-- 	`orgImg` VARCHAR(200) NOT NULL,
-- 	`regDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
-- 	PRIMARY KEY (`photoNo`),
-- 	FOREIGN KEY (`rollingPaperNo`) REFERENCES RollingPapers(`rollingPaperNo`),
-- 	FOREIGN KEY (`writerNo`) REFERENCES Users(`userNo`)
-- )ENGINE=InnoDB;
-- SELECT *
-- FROM photo;

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


INSERT INTO `Users` (userId, userPwd, userName, email)
VALUES
('eunsooo', '97', '박은수', 'suzy@naver.com'),
('suuzy', '98', '이수지', 'eunsu@naver.com'),
('HunTeac', '9898', '한재훈', 'jaehun@naver.com');

INSERT INTO `RollingPapers` (ownerNo, title)
VALUES
(1, '박은수의 롤링페이퍼'),
(2, '이수지의 롤링페이퍼'),
(3, '한재훈의 롤링페이퍼');

INSERT INTO `Letters` (letterNo, rollingPaperNo, writerNo, content)
VALUES
(1, 2, 1, '수지랑 짝이라서 재밌었어'),
(2, 2, 3, '수지 덕분에 즐거웠어'),
(3, 1, 3, '은수햄 덕분에 행복했어'),
(4, 3, 2, '엘지뽀이 한재훈');


