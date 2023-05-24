## 사용자들에게 축제에 대한 정보를 제공해주는 Web Project - Back Part
#### &emsp; TeamProject Backend Part

## 프로젝트 동기
#### &emsp; 국내 축제를 좋아하는 4명이 모여 축제에 대한 사이트를 만들기로 했다.


## 기술 스택 및 개발 환경
#### &emsp; Java 17, Spring-Boot
#### &emsp; Spring-Security, Validation, Lombok, WebSecurityConfig, SwaggerConfig, CorsConfig
#### &emsp; Git, GitHub
#### &emsp; VSCODE
#### &emsp; Postman
#### &emsp; MySQL

## 프로젝트 주요 특징
#### &emsp; 1. Spring-Scrurity, Validation

- 인증 커스텀 구현(TokenProvider, Service 및 각종 Handler, error 처리)
- BCryptPasswordEncoder를 이용한 패스워드 암호화 처리
- Validation을 이용한 검증 처리
- WebSecurityConfig, CorsConfig작성 및 Jwt와 연동

#### &emsp; 2. 업로드
- 파일 및 이미지 업로드 구현 및 응용
- 이미지를 업로드 할 시 썸네일도 보일 수 있게 구현

#### &emsp; 3. 축제 메인 페이지
- 축제를 월별과 지역별로 구분
- 시작 기간과 종료 기간 사이에 오늘 날짜가 해당한다면 축제가 보이게 설정
- 만약 시작 기관과 종료 기간 사이에 오늘 날짜가 해당하지 않는다면 축제가 보이지 않게 설정
- 만약 축제가 종료된 축제를 보고싶다면 지역별이나 월별을 선택해서 볼 수 있게 설정

## 프로젝트 주요 문서
#### &emsp; 논리적 데이터 모델링

![image](https://github.com/jaehyeon502/festivalProject_back/assets/125006495/fc1e2233-cc10-4de9-8d3c-73f4bb46986b)

## 물리적 데이터 모델링

### 1) 사용자
CREATE TABLE `user` (

  `user_id` VARCHAR(20) NOT NULL, -- PK -- 사용자 아이디  </br>
  `password` TEXT NOT NULL,             -- 사용자 비밀번호 </br>
  `profile_url` TEXT NULL,              -- 사용자 프로필 URL </br>
  `nickname` VARCHAR(30) NOT NULL,      -- 사용자 닉네임  </br>
  `tel_number` VARCHAR(15) NOT NULL, -- 사용자 휴대전화번호 </br>
  PRIMARY KEY (`user_id`), </br>
  UNIQUE INDEX `telNumber_UNIQUE` (`tel_number` ASC) VISIBLE, </br>
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE, </br> 
  UNIQUE INDEX `id_UNIQUE` (`user_id` ASC) VISIBLE) </br>
);

### 2) 게시판
CREATE TABLE `board` (
  `board_number` INT NOT NULL AUTO_INCREMENT, -- PK -- 축제 후기 게시물 번호 </br>
  `board_title` TEXT NOT NULL,                      -- 축제 후기 게시물 제목 </br>
  `board_content` TEXT NOT NULL,                    -- 축제 후기 게시물 내용 </br>
  `board_img_url` TEXT NULL,                        -- 축제 후기 게시물 이미지 URL </br>
  `board_write_datetime` DATETIME NOT NULL DEFAULT now(), -- 축제 후기 게시물 작성 날짜 및 시간 </br>
  `view_count` INT NOT NULL DEFAULT 0,              -- 조회수 </br>
  `recommend_count` INT NOT NULL DEFAULT 0,         -- 추천수 </br>
  `comment_count` INT NOT NULL DEFAULT 0,           -- 댓글수 </br>
  `writer_id` VARCHAR(20) NOT NULL,                 -- '게시물 작성자 아이디 </br>
  `writer_profile_url` TEXT NULL COMMENT,           -- 게시물 작성자 프로필 사진 URL </br>
  `writer_nickname` VARCHAR(30) NOT NULL,           -- 게시물 작성자 닉네임 </br>
  `festival_number` INT NOT NULL,                   -- 축제 번호 </br>
  PRIMARY KEY (`board_number`), </br> 
  UNIQUE INDEX `boardNumber_UNIQUE` (`board_number` ASC) VISIBLE, </br>
  INDEX `fk_board_user1_idx` (`writer_id` ASC) VISIBLE, </br>
  INDEX `fk_board_festival1_idx` (`festival_number` ASC) VISIBLE, </br>
  CONSTRAINT `fk_board_user1` </br>
    FOREIGN KEY (`writer_id`) </br>
    REFERENCES `festival`.`user` (`user_id`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION, </br>
  CONSTRAINT `fk_board_festival1` </br>
    FOREIGN KEY (`festival_number`) </br>
    REFERENCES `festival`.`festival` (`festival_number`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION </br>
);

### 3) 댓글
CREATE TABLE `comment` ( 
  `comment_number` INT NOT NULL AUTO_INCREMENT, -- PK -- 댓글 번호 </br>
  `comment_content` TEXT NOT NULL,                  -- 댓글 내용 </br>
  `board_number` INT NOT NULL,                      -- 게시물 번호 </br>
  `writer_id` VARCHAR(20) NOT NULL,                 -- 댓글 작성자 아이디 </br>
  `write_datetime` DATETIME NOT NULL DEFAULT now(), -- 댓글 작성 날짜 및 시간 </br>
  `writer_profile_url` TEXT NULL,                   -- 댓글 작성자 프로필 사진 URL </br>
  `writer_nickname` VARCHAR(30) NOT NULL,           -- 댓글 작성자 닉네임 </br>
  PRIMARY KEY (`comment_number`), </br>
  INDEX `fk_comment_user_idx` (`writer_id` ASC) VISIBLE, </br>
  INDEX `fk_comment_board1_idx` (`board_number` ASC) VISIBLE, </br>
  CONSTRAINT `fk_comment_user` </br>
    FOREIGN KEY (`writer_id`) </br>
    REFERENCES `festival`.`user` (`user_id`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION, </br>
  CONSTRAINT `fk_comment_board1` </br>
    FOREIGN KEY (`board_number`) </br>
    REFERENCES `festival`.`board` (`board_number`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION </br>
);


### 4) 축제
CREATE TABLE `festival` (
  `festival_number` INT NOT NULL AUTO_INCREMENT, -- PK -- 축제 번호 </br>
  `festival_name` VARCHAR(45) NOT NULL,               -- 축제 이름 </br>
  `festival_type` VARCHAR(255) NOT NULL,              -- 축제 타입 </br>
  `festival_duration_start` DATE NOT NULL,            -- 축제 시작 날짜 </br>
  `festival_duration_end` DATE NOT NULL,              -- 축제 끝 날짜 </br>
  `festival_time` DATETIME NULL,                      -- 축제 시간 </br>
  `festival_area` VARCHAR(45) NOT NULL,               -- 축제 지역 </br>
  `festival_cost` TEXT NULL,                          -- 축제 비용 </br>
  `festival_information` TEXT NULL,                   -- 축제 정보 </br>
  `oneline_review_average` INT NULL,                  -- 축제 한줄평 평점 </br>
  `festival_information_url` TEXT NULL, </br>
  PRIMARY KEY (`festival_number`), </br>
  UNIQUE INDEX `festival_number_UNIQUE` (`festival_number` ASC) VISIBLE) </br>
);

### 5) 자유 게시판
CREATE TABLE `freeboard` (
  `board_number` INT NOT NULL AUTO_INCREMENT,         -- PK -- 자유 게시물 번호 </br>
  `board_title` TEXT NOT NULL,                        -- 자유 게시물 제목 </br>
  `board_content` TEXT NOT NULL,                      -- 자유 게시물 내용 </br>
  `board_img_url` TEXT NULL,                          -- 자유 게시물 이미지 URL </br>
  `board_write_datetime` DATETIME NOT NULL DEFAULT now(), -- 자유 게시물 작성 날짜 및 시간 </br>
  `view_count` INT NOT NULL DEFAULT 0,                -- 조회수 </br>
  `recommend_count` INT NOT NULL DEFAULT 0,           -- 추천수 </br>
  `comment_count` INT NOT NULL DEFAULT 0,             -- 댓글수 </br>
  `writer_profile_url` TEXT NULL,                     -- 게시물 작성자 프로필 사진 URL </br>
  `writer_nickname` VARCHAR(30) NOT NULL,             -- 게시물 작성자 닉네임 </br>
  `writer_user_id` VARCHAR(20) NOT NULL,  </br>
  PRIMARY KEY (`free_board_number`), </br>
  INDEX `fk_freeboard_user1_idx` (`writer_user_id` ASC) VISIBLE, </br>
  CONSTRAINT `fk_freeboard_user1` </br>
    FOREIGN KEY (`writer_user_id`) </br>
    REFERENCES `festival`.`user` (`user_id`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION </br>
);

### 6) 자유 게시판 댓글
CREATE TABLE IF NOT EXISTS `festival`.`freeboardcomment` (
  `comment_number` INT NOT NULL AUTO_INCREMENT,       -- PK -- 댓글 번호  </br>
  `comment_content` TEXT NOT NULL,                          -- 댓글 내용  </br>
  `free_board_number` INT NOT NULL,                         -- 자유 게시판 게시물 번호  </br>
  `writer_id` VARCHAR(20) NOT NULL,                         -- 작성자 아이디 </br>
  `write_datetime` DATETIME NOT NULL DEFAULT now(),         -- 댓글 작성 날짜 및 시간  </br>
  `writer_profile_url` TEXT NULL COMMENT '',                -- 댓글 작성자 프로필 사진 URL  </br>
  `writer_nickname` VARCHAR(30) NOT NULL COMMENT '',        -- 댓글 작성자 닉네임  </br>
  INDEX `fk_user_has_freeboard1_freeboard1_idx` (`free_board_number` ASC) VISIBLE,  </br>
  INDEX `fk_user_has_freeboard1_user1_idx` (`writer_id` ASC) VISIBLE,  </br>
  PRIMARY KEY (`comment_number`),  </br>
  CONSTRAINT `fk_user_has_freeboard1_user1`  </br>
    FOREIGN KEY (`writer_id`)  </br>
    REFERENCES `festival`.`user` (`user_id`)  </br>
    ON DELETE NO ACTION  </br>
    ON UPDATE NO ACTION,  </br>
  CONSTRAINT `fk_user_has_freeboard1_freeboard1`  </br>
    FOREIGN KEY (`free_board_number`)  </br>
    REFERENCES `festival`.`freeboard` (`board_number`) </br>
    ON DELETE NO ACTION  </br>
    ON UPDATE NO ACTION  </br>
);

### 7) 자유 게시판 추천
CREATE TABLE IF NOT EXISTS `festival`.`freeboardrecommend` (
  `user_id` VARCHAR(20) NOT NULL,             -- PK -- 사용자 아이디 </br>
  `free_board_number` INT NOT NULL,           -- PK -- 자유 게시판 게시물 번호 </br>
  `user_profile_url` TEXT NULL DEFAULT NULL,  -- 유저 프로필 사진 URL </br>
  `user_nickname` VARCHAR(30) NOT NULL,       -- 추천한 유저 닉네임 </br>
  PRIMARY KEY (`user_id`, `free_board_number`), </br>
  INDEX `fk_user_has_freeboard_freeboard1_idx` (`free_board_number` ASC) VISIBLE, </br>
  INDEX `fk_user_has_freeboard_user1_idx` (`user_id` ASC) VISIBLE, </br>
  CONSTRAINT `fk_user_has_freeboard_user1` </br>
    FOREIGN KEY (`user_id`) </br>
    REFERENCES `festival`.`user` (`user_id`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION, </br>
  CONSTRAINT `fk_user_has_freeboard_freeboard1` </br>
    FOREIGN KEY (`free_board_number`) </br>
    REFERENCES `festival`.`freeboard` (`board_number`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION) </br>
);

### 8) 관심있는 축제
CREATE TABLE `interestedfestival` (
  `sequence` INT NOT NULL AUTO_INCREMENT, -- PK -- 관심있는 축제 구분자 </br>
  `user_id` VARCHAR(20) NOT NULL,             -- 관심 축제 등록한 아이디 </br>
  `interested_festival_type` TEXT NOT NULL,   -- 관심있는 축제 </br>
  INDEX `fk_festival_has_user_user1_idx` (`user_id` ASC) VISIBLE, </br>
  PRIMARY KEY (`sequence`), </br>
  UNIQUE INDEX `festival_type_UNIQUE` (`sequence` ASC) VISIBLE, </br>
  CONSTRAINT `fk_festival_has_user_user1` </br>
    FOREIGN KEY (`user_id`) </br>
    REFERENCES `festival`.`user` (`user_id`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION </br>
);

### 9) 한줄평
CREATE TABLE `onelinereview` (
  `festival_number` INT NOT NULL,     -- PK -- 해당 행사 번호 </br>
  `user_id` VARCHAR(20) NOT NULL,     -- PK -- 한줄평 작성자 아이디 </br>
  `average` INT NOT NULL DEFAULT 0,         -- 해당 행사 방문 평점 </br>
  `one_line_review_content` TEXT NOT NULL,  -- 한줄평 내용 </br>
  `user_profile_url` VARCHAR(45) NULL,      -- 한줄평 작성자 프로필 사진 URL </br>
  `user_nickname` VARCHAR(45) NOT NULL,     -- 한줄평 작성자 닉네임 </br>
  `onelinereviewcol` VARCHAR(45) NULL, </br>
  `write_datetime` DATETIME NOT NULL DEFAULT now(), </br>
  INDEX `fk_onelinereview_festival1_idx` (`festival_number` ASC) VISIBLE, </br>
  INDEX `fk_onelinereview_user1_idx` (`user_id` ASC) VISIBLE, </br>
  PRIMARY KEY (`user_id`, `festival_number`), </br>
  CONSTRAINT `fk_onelinereview_festival1` </br>
    FOREIGN KEY (`festival_number`) </br>
    REFERENCES `festival`.`festival` (`festival_number`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION, </br>
  CONSTRAINT `fk_onelinereview_user1` </br>
    FOREIGN KEY (`user_id`) </br>
    REFERENCES `festival`.`user` (`user_id`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION </br>
);

### 10) 추천
CREATE TABLE `recommend` (
  `user_id` VARCHAR(20) NOT NULL,             -- PK -- 추천한 회원 </br>
  `board_number` INT NOT NULL,                -- PK -- 게시물 번호 </br>
  `user_profile_url` TEXT NULL DEFAULT NULL,        -- 유저 프로필 사진 URL </br>
  `user_nickname` VARCHAR(30) NOT NULL,             -- 추천한 유저 닉네임 </br>
  INDEX `fk_recommend_user1_idx` (`user_id` ASC) VISIBLE, </br>
  INDEX `fk_recommend_board1_idx` (`board_number` ASC) VISIBLE, </br>
  PRIMARY KEY (`board_number`, `user_id`), </br>
  CONSTRAINT `fk_recommend_user1` </br>
    FOREIGN KEY (`user_id`) </br>
    REFERENCES `festival`.`user` (`user_id`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION, </br>
  CONSTRAINT `fk_recommend_board1` </br>
    FOREIGN KEY (`board_number`) </br>
    REFERENCES `festival`.`board` (`board_number`) </br>
    ON DELETE NO ACTION </br>
    ON UPDATE NO ACTION </br>
);

### 11) 검색
CREATE TABLE `searchwordlog` (
  `sequence` INT NOT NULL AUTO_INCREMEN, --PK -- 축제 검색어 시퀀스 </br> </br>
  `search_word` TEXT NOT NULL,                -- 검색어 </br>
  PRIMARY KEY (`sequence`) </br>
  );
