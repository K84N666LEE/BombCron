-- --------------------------------------------------------------------------------------
--
-- 자동 실행 체크
--
-- --------------------------------------------------------------------------------------

CREATE TABLE `AutoSend_Table`
(
	`ast_num`      INT(11)           NOT NULL AUTO_INCREMENT COMMENT '[_] 일련번호',
	`ast_siteNo`   INT(11)           NOT NULL DEFAULT '0'    COMMENT '[_] 사이트번호',
	`ast_part`     CHAR(1)               NULL DEFAULT NULL   COMMENT '[_] 예비',
	`ast_fd_00`    ENUM('Y','N')         NULL DEFAULT 'N'    COMMENT '[_] 취소여부',
	`ast_fd_01`    ENUM('Y','N','A')     NULL DEFAULT 'N'    COMMENT '[_] 실행완료여부',
	`ast_fd_02`    CHAR(14)              NULL DEFAULT NULL   COMMENT '[_] 실행시작일시',
	`ast_fd_03`    CHAR(2)               NULL DEFAULT NULL   COMMENT '[_] 반복실행매월일',
	`ast_fd_04`    CHAR(1)               NULL DEFAULT NULL   COMMENT '[_] 반복실행매주요일',
	`ast_fd_05`    CHAR(6)               NULL DEFAULT NULL   COMMENT '[_] 반복실행시분초',
	`ast_fd_06`    VARCHAR(250)          NULL DEFAULT NULL   COMMENT '[_] 제목(설명)',
	`ast_fd_07`    VARCHAR(250)          NULL DEFAULT NULL   COMMENT '[_] 실행프로그램',
	`ast_fd_08`    VARCHAR(2000)         NULL DEFAULT NULL   COMMENT '[P] 넘겨줄 파리미터 값',
	`ast_fd_09`    VARCHAR(250)          NULL DEFAULT NULL   COMMENT '[_] 생성위치',
	`ast_fd_10`    CHAR(1)               NULL DEFAULT NULL   COMMENT '[_] 예비',
	`ast_fd_11`    CHAR(1)               NULL DEFAULT NULL   COMMENT '[_] 예비',
	`ast_fd_12`    CHAR(1)               NULL DEFAULT NULL   COMMENT '[_] 예비',
	`ast_create_u` VARCHAR(10)       NOT NULL DEFAULT ''     COMMENT '[_] 생성자',
	`ast_create_d` VARCHAR(14)       NOT NULL DEFAULT ''     COMMENT '[_] 생성일',
	PRIMARY KEY (`ast_num`),
	INDEX `ast_siteNo` (`ast_siteNo`),
	INDEX `ast_fd_00` (`ast_fd_00`),
	INDEX `ast_fd_01` (`ast_fd_01`),
	INDEX `ast_fd_02` (`ast_fd_02`)
)
COMMENT='자동실행'
COLLATE='utf8_general_ci'
ENGINE=MyISAM
;