package com.brunosong.refactoring_exam.commonservice.common.util;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

 /**
 * <pre>
 * 기능 : Excel Reader Interface (Excel 파일 업로드 시 사용됨)
 * </pre>
 */
public interface APIExcelReader {

     /**
      * Sheet에서 데이터를 가져온다.
      * 주의할 사항은 첫번째 row는 각 컬럼의 key가 나와야 한다.
      *
      * @param excelStream the excel stream
      * @return the sheet data
      * @throws Exception the exception
      */
    public List<LinkedHashMap<String, Object>> getSheetData(InputStream excelStream) throws Exception;



}
