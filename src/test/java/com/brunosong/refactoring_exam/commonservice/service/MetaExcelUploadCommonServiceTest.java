package com.brunosong.refactoring_exam.commonservice.service;

import com.brunosong.refactoring_exam.commonservice.dummy.MetaExcelMapListDummy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.List;

@SpringBootTest
class MetaExcelUploadCommonServiceTest extends MetaExcelMapListDummy {

    @Autowired
    private MetaExcelUploadCommonService metaExcelUploadCommonService;

    @Test
    void mapToMetaBulkConvert_테스트() {

        List<LinkedHashMap<String, Object>> excelMapList = newExcelMapList(null, 1);
        metaExcelUploadCommonService.mapToMetaBulkConvert(excelMapList);

    }

}