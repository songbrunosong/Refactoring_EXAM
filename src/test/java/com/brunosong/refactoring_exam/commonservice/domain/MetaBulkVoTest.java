package com.brunosong.refactoring_exam.commonservice.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MetaBulkVoTest {

    @Test
    void 영역코드이름을_영역코드로_변경하기(){

        //given
        List<MetaItemVo> areaCodeList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            areaCodeList.add(new MetaItemVo(String.valueOf(i),"1","AREA_CODE_테스트" + i ,"AREA_" + i ));
        }

        //when
        MetaBulkVo metaBulkVo = new MetaBulkVo();
        metaBulkVo.setArea("AREA_CODE_테스트1");
        metaBulkVo.setCourse_code("AAA");
        metaBulkVo.changeAreaNameToCode(areaCodeList);

        //then
        assertThat(metaBulkVo.getArea()).isEqualTo("AREA_1");

    }

    @Test
    void 영역코드이름이_기본이고_COURSE_CODE가_EVAI일때(){

        MetaBulkVo metaBulkVo = new MetaBulkVo();
        metaBulkVo.setArea("기본");
        metaBulkVo.setCourse_code("EVAI");

        metaBulkVo.changeAreaNameToCode(null);

        assertThat(metaBulkVo.getArea()).isEqualTo("NN00");

    }

    @Test
    void 영역코드이름이_기본이고_COURSE_CODE가_EVAI가_아닐때(){

        MetaBulkVo metaBulkVo = new MetaBulkVo();
        metaBulkVo.setArea("기본");
        metaBulkVo.setCourse_code("XXXX");

        metaBulkVo.changeAreaNameToCode(null);

        assertThat(metaBulkVo.getArea()).isEqualTo("ES01");

    }

}