package com.brunosong.refactoring_exam.commonservice.domain;

public enum CurriculumColumn {

    GRADE("학년"),
    TERM("학기"),
    WEEK("주차"),
    PRE_PRO("예습/진도"),
    CLASS_NUM("cl< >ss"),
    COURSE_CODE("과목"),
    SUBJECT_DAY("과목차시"),
    COURSE_DETAIL("대표단원(한글/국어)"),
    AREA("영역"),
    REVISION_YEAR("개정연도"),
    REVISION_WHETHER("개정여부"),
    PUB_NAME("출판사"),
    UNIT_NAME("단원명"),
    UNIT_NUM("단원순서"),
    DAY_NUM("차시순서"),
    DAY_NAME("차시명"),
    DAY_CONT("차시내용(수정)"),
    DYA_DISPLAY("전체과목노출여부(차시)"),
    CONER_NUM("코너순서"),
    CONER_NAME("코너명"),
    CONER_CONT("코너내용"),
    TODAY_DISPLAY("오늘의학습노출여부"),
    TOC_SEQ("목차고유번호"),
    TOC_NUM("목차순서"),
    TOC_NAME("목차명"),
    TOC_TYPE("목차유형"),
    TYPE_CODE("목차유형코드"),
    VIEW_YN("문항뷰어여부"),
    ALL_YN("일괄채점여부"),
    MUST_YN("필수학습여부(완료체크)"),
    QST_CNT("기본출제문항수"),
    MAX_CNT("최대출제문항수"),
    PRE_CONER("선행코너"),
    PRE_MSG("선행메시지"),
    LVL1("레벨1(100%)"),
    LVL2("레벨2(99~50%)"),
    LVL3("레벨3(49%~0%)"),
    LVL4("레벨4"),
    LVL5("레벨5"),
    TWO_SEQ("2레벨고유번호"),
    UNIT_SEQ("단원고유번호"),
    DAY_SEQ("차시고유번호"),
    CONER_SEQ("코너고유번호"),
    VIEWER_CSS_CODE("문항뷰어CSS코드"),
    GOODS_TYPE("자재 종류[학습]_6레벨용"),
    SYSTEM_CODE("시스템코드_4레벨용"),
    FIVE_WEEK_FURTHER("5주차 진도"),
    PRE_PROG_REL_COURSE("이전 진도 연계 과목"),
    PRE_PROG_REL_LESN("이전 진도 연계 차시"),
    ALL100_CODE("올백 교재 자재코드");

    private String value;

    CurriculumColumn(String value) { this.value = value; }

    public String getValue() {return value; }
}
