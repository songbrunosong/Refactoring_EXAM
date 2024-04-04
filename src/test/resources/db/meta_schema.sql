CREATE TABLE `goods_meta_master` (
    `master_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '마스터 고유번호',
    `meta_name` varchar(100) DEFAULT NULL COMMENT '메타명',
    `meta_code` varchar(100) DEFAULT NULL COMMENT '코드',
    `input_type` varchar(20) DEFAULT NULL COMMENT 'html input type',
    `is_goods` enum('Y','N') DEFAULT 'Y' COMMENT '자재 마스터 여부(N이면 콘텐츠 마스터)',
    `conts_gubun` varchar(20) DEFAULT 'Y' COMMENT '콘텐츠 구분',
    `orders` int(11) DEFAULT NULL COMMENT '순서',
    `must_save_yn` enum('Y','N') DEFAULT 'N' COMMENT '필수저장여부',
    `use_yn` enum('Y','N') DEFAULT 'Y' COMMENT '사용여부',
    `base_yn` enum('Y','N') DEFAULT 'N' COMMENT '기본속성여부',
    `auto_yn` enum('Y','N') DEFAULT 'N' COMMENT '자동생성여부',
    `display_yn` enum('Y','N') DEFAULT 'N' COMMENT '자재등록 화면 노출여부',
    `cre_dtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    `cre_id` varchar(20) DEFAULT NULL COMMENT '등록자',
    `cre_ip` varchar(30) DEFAULT NULL COMMENT '등록ip',
    `upd_dtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시',
    `upd_id` varchar(20) DEFAULT NULL COMMENT '수정자',
    `upd_ip` varchar(30) DEFAULT NULL COMMENT '수정ip',
    PRIMARY KEY (`master_seq`),
    KEY `IDX_GOODS_META_MASTER_01` (`meta_code`)
);


CREATE TABLE `goods_meta_item` (
  `item_seq` int(11) NOT NULL AUTO_INCREMENT COMMENT '항목 고유번호',
  `master_seq` int(11) NOT NULL COMMENT '마스터 고유번호',
  `item_name` varchar(100) NOT NULL COMMENT '항목명',
  `item_code` varchar(100) DEFAULT NULL COMMENT '항목코드',
  `orders` int(11) DEFAULT NULL COMMENT '순서',
  `cre_dtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
  `cre_id` varchar(20) DEFAULT NULL COMMENT '등록자',
  `cre_ip` varchar(30) DEFAULT NULL COMMENT '등록ip',
  `upd_dtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시',
  `upd_id` varchar(20) DEFAULT NULL COMMENT '수정자',
  `upd_ip` varchar(30) DEFAULT NULL COMMENT '수정ip',
  `use_yn` enum('Y','N') NOT NULL DEFAULT 'Y' COMMENT '사용여부',
  PRIMARY KEY (`item_seq`),
  KEY `FK_GOODS_META_ITEM_01` (`master_seq`),
  KEY `IDX_GOODS_META_ITEM_02` (`item_code`)
);


