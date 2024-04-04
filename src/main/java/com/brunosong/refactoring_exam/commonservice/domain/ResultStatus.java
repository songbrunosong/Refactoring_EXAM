package com.brunosong.refactoring_exam.commonservice.domain;

public enum ResultStatus {

    SUCCESS("성공적으로 처리되었습니다."),
    ALL_SUCCESS("모든레벨이 성공적으로 처리되었습니다."),

    ERROR("저장중 에러가 발생하였습니다."),
    EMPTY("값이 없습니다."),

    ERROR_COLUMN("샘플 파일의 컬럼명이 잘못되었습니다."),
    ERROR_TOO_MANY_ROW("행은 최대 2000행을 넘을 수 없습니다."),
    ERROR_META_NUM_DUPLICATE("메타의 순서값이 중복입력되었습니다."),
    ERROR_META_UNIT_NUM_DUPLICATE("단원 메타의 순서값이 중복입력되었습니다."),
    ERROR_META_DAY_NUM_DUPLICATE("차시 메타의 순서값이 중복입력되었습니다."),
    ERROR_META_CORNER_NUM_DUPLICATE("코너 메타의 순서값이 중복입력되었습니다."),
    ERROR_META_CHAP_NUM_DUPLICATE("목차 메타의 순서값이 중복입력되었습니다."),
    ERROR_LEVEL2_COURSE_CODE("과목명 정보와 2레벨 고유번호가 일치하지 않습니다."),
    BEFORE_LEVEL_EMPTY("전 레벨이 존재하지 않습니다."),
    ALREADY("등록하려는 값이 이미 존재합니다."),
    UPDATE_COUNT_ERROR("UNIT_SEQ 에 빈값이 존재합니다."),

    ERROR_LEVEL3("3레벨에서 오류가 발생하였습니다."),
    ERROR_LEVEL4("4레벨에서 오류가 발생하였습니다."),
    ERROR_LEVEL5("5레벨에서 오류가 발생하였습니다."),
    ERROR_LEVEL6("6레벨에서 오류가 발생하였습니다."),
    ERROR_CSS_COURSE_CODE("[course_detail(대표단원) 컬럼 에러]"),
    ERROR_RPRS_COURSE_CODE("[course_detail(대표단원) 컬럼 에러]"),
    ERROR_COURSE_CODE("과목명 정보가 잘못되었습니다."),
    ERROR_AREA_CODE("영역 정보가 잘못되었습니다."),
    ERROR_EMPTY_LEVEL2("2레벨고유번호가 누락되었습니다."),
    ERROR_EMPTY_GRADE("학년 정보가 누락되었습니다."),
    ERROR_EMPTY_TERM("학기 정보가 누락되었습니다."),
    ERROR_EMPTY_PRE_PRO("예습/진도 정보가 누락되었습니다."),
    ERROR_EMPTY_COURSE_CODE("과목 정보가 누락되었습니다."),
    ERROR_EMPTY_SUBJECT_DAY("과목차시 정보가 누락되었습니다."),
    ERROR_EMPTY_COURSE_DETAIL("대표단원 정보가 누락되었습니다."),
    ERROR_EMPTY_PUB_NAME("출판사 정보가 누락되었습니다."),
    ERROR_EMPTY_UNIT_NAME("단원명 정보가 누락되었습니다."),
    ERROR_EMPTY_UNIT_NUM("단원순서 정보가 누락되었습니다."),
    ERROR_EMPTY_DAY_NUM("차시순서 정보가 누락되었습니다."),
    ERROR_EMPTY_DAY_NAME("차시명 정보가 누락되었습니다."),
    ERROR_EMPTY_DAY_DISPLAY("전체과목노출여부 정보가 누락되었습니다."),
    ERROR_EMPTY_CORNER_NUM("코너순서 정보가 누락되었습니다."),
    ERROR_EMPTY_CORNER_NAME("코너명 정보가 누락되었습니다."),
    ERROR_EMPTY_TODAY_DISPLAY("오늘의학습 노출여부 정보가 누락되었습니다."),
    ERROR_EMPTY_CHAP_NUM("목차순서 정보가 누락되었습니다."),
    ERROR_EMPTY_CHAP_NAME("목차명 정보가 누락되었습니다."),
    ERROR_EMPTY_CHAP_TYPE("목차유형 정보가 누락되었습니다."),
    ERROR_EMPTY_TOC_TYPE_CODE("목차유형코드 정보가 누락되었습니다."),
    ERROR_EMPTY_QST_VIEW_YN("문항뷰어여부 정보가 누락되었습니다."),
    ERROR_EMPTY_ALL_YN("일괄채점여부 정보가 누락되었습니다."),
    ERROR_EMPTY_MUST_YN("필수학습여부 정보가 누락되었습니다."),
    ERROR_EMPTY_UNIT_SEQ("단원고유번호 정보가 누락되었습니다."),
    ERROR_EMPTY_DAY_SEQ("차시고유번호 정보가 누락되었습니다."),
    ERROR_EMPTY_CORNER_SEQ("코너고유번호 정보가 누락되었습니다."),
    ERROR_EMPTY_TOC_NUM("목차순서 정보가 누락되었습니다."),

    /*문항 ResultStatus*/
    ERROR_QST("문항 등록 중 오류가 발생했습니다."),
    ERROR_WRIT_YN("문항저작도구과목노출여부가 잘못 입력되었습니다."),
    ERROR_QST_NUM("문항 순서 항목이 잘못 입력되었습니다"),

    ERROR_QST_PROP("문항 속성 등록 중 오류가 발생했습니다."),
    ERROR_CHAP_SEQ("목차고유번호가 잘못 입력되었습니다."),
    ERROR_QUESTION_CODE("문항코드 잘못 입력되었습니다."),
    ERROR_FILE_NAME("파일명이 잘못 입력되었습니다."),
    ERROR_DIFFICULTY("난이도가 잘못 입력되었습니다."),
    ERROR_ACT_TYPE("행동영역이 잘못 입력되었습니다."),
    ERROR_CNTT_TYPE("내용영역(기능영역)이 잘못 입력되었습니다."),
    ERROR_Q_TYPE("문제형태가 잘못 입력되었습니다."),
    ERROR_DF("맞춤문항난이도유형코드가 잘못 입력되었습니다."),
    ERROR_CAP_CD1("역량1이 잘못 입력되었습니다."),
    ERROR_CAP_CD2("역량2이 잘못 입력되었습니다."),
    ERROR_CAP_CD3("역량3이 잘못 입력되었습니다."),
    ERROR_KC_CD("지식개념이 잘못 입력되었습니다."),
    ERROR_NOT_EXIST_QUESTION("입력된 목차고유번호와 문항코드에 맞는 문항이 존재하지 않습니다."),
    ERROR_EMPTY_QST("문항이 존재하지 않습니다. 목차고유번호와 문항코드를 확인해주세요."),


    /* QMaker 문항 저작도구 관련 */
    ERROR_QST_QMAKER("문항 저작 상태 변경 중 오류가 발생했습니다."),
    ERROR_CONTENTS_SEQ("문항 코드가 존재하지 않습니다."),
    ERROR_WRIT_STATUS("저작 상태 코드가 잘못 입력되었습니다.");

    private String value;

    ResultStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

