INSERT INTO goods_meta_master (meta_name,meta_code,input_type,is_goods,conts_gubun,orders,must_save_yn,use_yn,base_yn,auto_yn,display_yn,cre_dtime,cre_id,cre_ip )
VALUES
('과목코드','COURSE_CODE','SELECT','Y',NULL,0,'Y','Y','N','N','N',current_timestamp(),'A1234',NULL),
('영역명','CRCM_SCTN_CD','SELECT','Y',NULL,NULL,'N','Y','N','N','N',current_timestamp(),'A1234',NULL);

INSERT INTO goods_meta_item (master_seq,item_name,item_code,orders,cre_dtime,cre_id,cre_ip,use_yn)
VALUES
(1,'국어','KORN',3,current_timestamp(),'A1234',NULL,'Y'),
(1,'영단어 2300','ENWD',5,current_timestamp(),'A1234',NULL,'Y'),
(1,'독해 완성','KORD',6,current_timestamp(),'A1234',NULL,'Y'),
(1,'수학','MATH',8,current_timestamp(),'A1234',NULL,'Y'),
(1,'영역별_심화 영어','ENSL',9,current_timestamp(),'A1234',NULL,'Y'),
(1,'영역별 개념 학습','MACP',10,current_timestamp(),'A1234',NULL,'Y'),
(1,'사회','SOSI',11,current_timestamp(),'A1234',NULL,'Y'),
(1,'수준별_영어 도서관','ENRD',12,current_timestamp(),'A1234',NULL,'Y'),
(1,'AI 맞춤문제','MAAI',13,current_timestamp(),'A1234',NULL,'Y'),
(1,'과학','SCNE',14,current_timestamp(),'A1234',NULL,'Y'),
(1,'퀴즈 놀이','QUIZ',14,current_timestamp(),'A1234',NULL,'Y');



INSERT INTO goods_meta_item (master_seq,item_name,item_code,orders,cre_dtime,cre_id,cre_ip,use_yn)
VALUES
(2,'어휘 확장','KO01',5,current_timestamp(),'A1234',NULL,'Y'),
(2,'읽기','KO02',6,current_timestamp(),'A1234',NULL,'Y'),
(2,'일기 쓰기','KO03',7,current_timestamp(),'A1234',NULL,'Y'),
(2,'말하기 듣기','KO04',8,current_timestamp(),'A1234',NULL,'Y'),
(2,'우리말','KO05',9,current_timestamp(),'A1234',NULL,'Y'),
(2,'갈래글 쓰기','KO06',10,current_timestamp(),'A1234',NULL,'Y'),
(2,'미디어리터러시','KO07',11,current_timestamp(),'A1234',NULL,'Y'),
(2,'기본 모음과 자음 ','HA01',12,current_timestamp(),'A1234',NULL,'Y'),
(2,'기본 받침','HA02',13,current_timestamp(),'A1234',NULL,'Y'),
(2,'복잡한 모음','HA03',14,current_timestamp(),'A1234',NULL,'Y');