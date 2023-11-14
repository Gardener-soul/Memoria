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

INSERT INTO `Users` (userId, userPwd, userName, email, nickname)
VALUES
('박은수', 'eunsooo97', '', ''),
('이수지', 'suuzy98', '', '');
-- ('파리', '유럽 서부', '낭만의 도시', '바게트, 크루아상, 잠봉뵈르'),
-- ('푸꾸옥', '동남아시아', '휴양의 도시', '쌀국수, 반미'),
-- ('시드니', '오세아니아', '여행의 도시', '아웃백, 쌀국수, 스테이크'),
-- ('교토',  '동아시아 ', '고요한 도시', '우동, 규카츠, 타코야끼'),
-- ('상하이',  '동아시아', '야경의 도시', '훠궈, 덤플링');

