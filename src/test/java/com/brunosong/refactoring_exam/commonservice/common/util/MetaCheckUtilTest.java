package com.brunosong.refactoring_exam.commonservice.common.util;

import com.brunosong.refactoring_exam.commonservice.domain.CurriculumColumn;
import com.brunosong.refactoring_exam.commonservice.dummy.MetaExcelMapListDummy;
import com.brunosong.refactoring_exam.commonservice.handler.ex.ExcelMetaCheckException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

class MetaCheckUtilTest extends MetaExcelMapListDummy {

    @Test
    void 엑셀로우_카운트가_2000이넘으면_익셉션을_발생한다(){

        List<LinkedHashMap<String, Object>> excelMapList = newExcelMapList(null,2300);

        //when
        Assertions.assertThatThrownBy( () ->
                MetaCheckUtil.checkRowCount(excelMapList)
        ).isInstanceOf(ExcelMetaCheckException.class);

    }


    @Test
    void 시스템에_없는컬럼이_있으면_익셉션을_발생한다(){

        //given
        List<LinkedHashMap<String, Object>> excelMapList = newExcelMapList(null);

        //when
        Set<String> enumValues = new HashSet<>();
        for (CurriculumColumn enumValue : EnumSet.allOf(CurriculumColumn.class)) {
            enumValues.add(enumValue.getValue());
        }
        LinkedHashMap<String, Object> map = excelMapList.get(0);
        map.put("테스트","");

        //then
        Assertions.assertThatThrownBy( () ->
                MetaCheckUtil.checkColumnName(excelMapList.get(0), enumValues )
        ).isInstanceOf(ExcelMetaCheckException.class);

    }

}