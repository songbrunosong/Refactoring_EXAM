package com.brunosong.refactoring_exam.commonservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MetaBulkVo {

    String seq;
    String grade;    // 학년
    String term;    // 학기
    String week;    // 주차
    String pre_pro;    // 예습/진도
    String class_num;    // cl< >ss
    String course_code;    // 과목
    String subject_day;    // 과목차시
    String course_detail;    // 대표단원
    String area;            // 영역

}
