CREATE TABLE user (
    id 		    BIGINT 	PRIMARY KEY NOT NULL AUTO_INCREMENT	COMMENT '사용자 식별자',
    username 	VARCHAR(255) NOT NULL				        COMMENT '유저이름',
    email 	    VARCHAR(255) NOT NULL			            COMMENT '이메일',
    password 	VARCHAR(255) NOT NULL		                COMMENT '비밀번호',
    create_at 	DATE						                COMMENT '생성일',
    update_at 	DATE						                COMMENT '수정일'
);

CREATE TABLE schedule (
    id 		    BIGINT 	PRIMARY KEY NOT NULL AUTO_INCREMENT	COMMENT '할일 식별자',
    user_id 	bigint	NOT NULL					        COMMENT '사용자 식별자',
    title 	    VARCHAR(255) NOT NULL			            COMMENT '할일 제목',
    text 	    VARCHAR(255) NOT NULL			            COMMENT '할일 내용',
    create_at 	DATE						                COMMENT '생성일',
    update_at 	DATE						                COMMENT '수정일'
	FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE comment (
    id 		        BIGINT 	PRIMARY KEY NOT NULL AUTO_INCREMENT	COMMENT '댓글 식별자',
    schedule_id 	BIGINT	NOT NULL					        COMMENT '할일 식별자',
    user_id 	    BIGINT	NOT NULL					        COMMENT '사용자 식별자',
    comment 	    VARCHAR(255) NOT NULL				        COMMENT '댓글',
    create_at 	    DATE						                COMMENT '생성일',
    update_at 	    DATE						                COMMENT '수정일'
	FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (schedule_id) REFERENCES schedule (id)
);
