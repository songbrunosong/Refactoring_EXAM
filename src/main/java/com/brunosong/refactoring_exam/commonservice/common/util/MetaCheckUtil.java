package com.brunosong.refactoring_exam.commonservice.common.util;

import com.brunosong.refactoring_exam.commonservice.domain.ResultStatus;
import com.brunosong.refactoring_exam.commonservice.handler.ex.ExcelMetaCheckException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class MetaCheckUtil {

    public static void checkRowCount(List<LinkedHashMap<String, Object>> excelMapList) {
        if (excelMapList.size() > 2000) {
            throw new ExcelMetaCheckException(ResultStatus.ERROR_TOO_MANY_ROW.getValue());
        }
    }

    public static void checkColumnName(LinkedHashMap<String, Object> columnCheckRow,
                                        Set<String> enumValues) {

        Set<String> keySet = columnCheckRow.keySet();
        for (String key : keySet) {
            if (!enumValues.contains(key)) {
                throw new ExcelMetaCheckException(ResultStatus.ERROR_COLUMN.getValue());
            }
        }
    }

}
