package com.brunosong.refactoring_exam.commonservice.service;


import com.brunosong.refactoring_exam.commonservice.domain.MetaItemVo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MetaExcelUploadCommonServiceRefactoringTest {

    @Test
    void COURSE_CODE_NAME으로_COURSE_CODE_찾기() {

        //given
        List<MetaItemVo> courseCodeList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            courseCodeList.add(new MetaItemVo(String.valueOf(i),"1","테스트" + i ,"TEST_" + i ));
        }
        String courseCodeName = "테스트1";

        //when
        MetaExcelUploadCommonService commonService = new MetaExcelUploadCommonService(null);
        String courseCode = commonService.findCourseCode(courseCodeList, courseCodeName);

        //then
        assertThat(courseCode).isEqualTo("TEST_1");
    }


    @Test
    void AREA_CODE_NAME으로_AREA_CODE_찾기() {

        //given
        List<MetaItemVo> areaCodeList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            areaCodeList.add(new MetaItemVo(String.valueOf(i),"1","영역코드명" + i ,"AREA_" + i ));
        }
        String areaCodeName = "영역코드명1";

        //when
        MetaExcelUploadCommonService commonService = new MetaExcelUploadCommonService(null);
        String courseCode = commonService.findAreaCode(areaCodeList, areaCodeName);

        //then
        assertThat(courseCode).isEqualTo("AREA_1");
    }

}
