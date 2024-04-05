package com.brunosong.refactoring_exam.commonservice.service;


import com.brunosong.refactoring_exam.commonservice.domain.MetaItemVo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MetaExcelUploadCommonServiceRefactoringTest {

    @Test
    void CODENAME으로_CODE_찾기() {

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

}
