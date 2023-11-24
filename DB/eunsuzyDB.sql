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
    `eventDay` INT DEFAULT 0,
    `achieve` VARCHAR(1),
    `achieveDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    `regDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY(`userNo`),
    CHECK(`achieve` IN ('F', 'T'))
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
    `font` VARCHAR(50),
    `fontColor` VARCHAR(50),
    `backColor` VARCHAR(50),
    `img` VARCHAR(200),
    `orgImg` VARCHAR(200),
    `regDate` DATETIME DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`letterNo`),
    FOREIGN KEY (`rollingPaperNo`) REFERENCES RollingPapers(`rollingPaperNo`),
    FOREIGN KEY (`writerNo`) REFERENCES Users(`userNo`)
)ENGINE=InnoDB;
SELECT *
FROM Letters;

DROP TABLE Events;
CREATE TABLE `Events` (
    `eventNo` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `eventTitle` VARCHAR(50),
    `description` VARCHAR(200),
    `eventDay` INT DEFAULT 0
)ENGINE=InnoDB;
SELECT *
FROM Events;

INSERT INTO `Users` (userId, userPwd, userName, email)
VALUES
('admin', 'admin', '관리자', 'admin@ssafy.com')

UPDATE Users
SET usreNo = 0
FROM userNo = 1;

INSERT INTO `Events` (eventTitle, description, eventDay)
VALUES
('영상 구독', '특정 운동 채널 구독하기.', 1),
('코멘트 남기기', '운동 영상에 운동 계획 댓글 남기기.', 1),
('스트레칭 따라하기', '제공된 스트레칭 영상 시청하고 따라하기.', 1),
('유튜브 좋아요 누르기', '운동 영상에 "좋아요" 누르기.', 2),
('운동 루틴 공유하기', ' 자신만의 운동 루틴을 SNS에 공유하기.', 2),
('5분 집중 운동', '5분간 집중 운동 영상 따라하기.', 2),
('친구에게 운동 영상 공유하기', '운동 영상 링크를 친구에게 전송하기.', 3),
('집에서 할 수 있는 운동 찾기', '집에서 할 수 있는 운동 영상 시청하기.', 3),
('워밍업 영상 따라하기', '워밍업 운동 영상 따라하기.', 3),
('운동 영상 플레이리스트 만들기', '좋아하는 운동 영상으로 플레이리스트 만들기.', 4),
('운동 도전 댓글 남기기', '운동 도전 영상에 참여 의사 댓글 남기기.', 4),
('10분 요가 영상 따라하기', '10분 요가 세션 영상 시청하고 따라하기.', 4),
('걷기 운동 영상 시청하기', '걷기 운동 관련 영상 시청하기.', 5),
('하루 운동 목표 설정하기', '하루 운동 목표 댓글로 남기기.', 5),
('HIIT 운동 도전하기', '고강도 인터벌 트레이닝(HIIT) 운동 영상 따라하기.', 5),
('체중 감량 운동 영상 시청하기', '체중 감량을 위한 운동 영상 시청하기.', 6),
('건강한 식단 공유하기', '건강한 식단에 관한 정보 공유하기.', 6),
('필라테스 영상 따라하기', '필라테스 영상 시청하고 운동 따라하기.', 6),
('운동 성과 공유하기', '주일간의 운동 성과 SNS에 공유하기.', 7),
('스트레스 해소 운동 찾기', '스트레스 해소에 좋은 운동 영상 찾아보기.', 7),
('휴식과 명상', '휴식과 명상을 위한 가이드 영상 시청하기.', 7);