package com.brunosong.refactoring_exam.commonservice.domain;

import com.brunosong.refactoring_exam.commonservice.handler.ex.ExcelMetaCheckException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MetaBulkVo {

    String seq;
    String topId;    //소속된 ID
    String grade;    // 학년
    String term;    // 학기
    String week;    // 주차
    String pre_pro;    // 예습/진도
    String class_num;    // cl< >ss
    String course_code;    // 과목
    String subject_day;    // 과목차시
    String course_detail;    // 대표단원
    String area;            // 영역

    public void changeAreaNameToCode(List<MetaItemVo> areaCodeList) {

        if(!"".equals(this.area)) {
            if ("기본".equals(this.area)) {
                this.area = "EVAI".equals(this.course_code) ? "NN00" : "ES01";
            } else {
                String result = areaCodeList.stream()
                        .filter(metaItemVo -> metaItemVo.getItem_name().equals(this.area))
                        .map(MetaItemVo::getItem_code)
                        .findFirst()
                        .orElse("NOT MATCH");

                if (result.equals("NOT MATCH")) {
                    throw new ExcelMetaCheckException(ResultStatus.ERROR_AREA_CODE.getValue());
                }
                this.area = result;
            }
        }
    }


    public void changeCourseNameToCode(List<MetaItemVo> courseCodeList) {

        String result = courseCodeList.stream()
                .filter(item -> item.getItem_name().equals(this.course_code))
                .map(MetaItemVo::getItem_code)
                .findFirst()
                .orElse("");

        if(result.equals("")) {
            throw new ExcelMetaCheckException(ResultStatus.ERROR_LEVEL2_COURSE_CODE.getValue());
        }

        this.course_code = result;
    }


}
