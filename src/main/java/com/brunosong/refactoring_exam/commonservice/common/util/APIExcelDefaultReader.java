package com.brunosong.refactoring_exam.commonservice.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Slf4j
public class APIExcelDefaultReader implements APIExcelReader {

    private final boolean isFormula = false;

    private Workbook workbook;

	/**
     * File을 HSSFWorkbook 객체로 만든다.
     *
     * @throws Exception
     */
    private boolean initialize(InputStream excelStream) throws Exception {
        boolean isFile = false;

        try {
        	workbook = WorkbookFactory.create(excelStream);
        	isFile = true;

        } catch (IOException ioe) {
        	throw new Exception("TODO", ioe);
        } finally {
        	if (excelStream != null){
                try {
                	excelStream.close();
                } catch (Exception e) {
                	log.debug(e.getMessage());
                }
            }
        }
        return isFile;
    }

    /**
     * Sheet에서 데이터를 가져온다.
     * 주의할 사항은 첫번째 row는 각 컬럼의 key가 나와야 한다.
     *
     * @param filePath
     * @param sheetIndex
     * @throws Exception
     */
    @Override
	public List<LinkedHashMap<String, Object>> getSheetData(InputStream excelStream) throws Exception {
    	List<LinkedHashMap<String, Object>> sheetData = null;

        if (initialize(excelStream))
            sheetData = executeSheetData(0);
        return sheetData;
    }

    /**
     * Sheet에서 데이터를 가져온다.
     * 주의할 사항은 첫번째 row는 각 컬럼의 key가 나와야 한다.
     *
     * @param wantedSheetIndex
     * @return List<Map<String, Object>>
     * @throws Exception
     */
    private List<LinkedHashMap<String, Object>> executeSheetData(int wantedSheetIndex) throws Exception {
    	List<LinkedHashMap<String, Object>> cellLMultiData = null;
        try {
            final String sheetName = workbook.getSheetName(wantedSheetIndex);
            Sheet sheet = workbook.getSheetAt(wantedSheetIndex);
            cellLMultiData = getData(sheet, sheetName);
        } catch (Exception e) {
        	//throw new Exception("TODO", re);
        	log.info(e.toString());
        }
        return cellLMultiData;
    }

    /**
     * 한 개의 Sheet의 데이터를 가져와 LMultiData 형태로 리턴한다.
     *
     * @param dataList
     * @param sheet
     * @param sheetName
     */
    private List<LinkedHashMap<String, Object>> getData(Sheet sheet, String sheetName) {
        List<String> cellNameList = new ArrayList<String>();
        List<LinkedHashMap<String, Object>> cellMultiData = new ArrayList<LinkedHashMap<String, Object>>();

        final int rowsize = sheet.getLastRowNum();
        int realDataCount = 0;
        for (int iny = 0; iny < (rowsize + 1); iny++) {
            Row row = sheet.getRow(iny);

            if (row != null) {
                Object[] cells = getRow(row);
                final int cellsize = cells.length;
            	if (iny == realDataCount) {
            		for (int inz = 0; inz < cellsize; inz++) {
            			if (!cells[inz].equals("")) {
                            cellNameList.add((String)cells[inz]);
                        } else {
                            if (cellNameList.size() != 0) {
                                cellNameList.add(cellNameList.get(0) + "" + inz);
                            } else {
                                cellNameList.add("" + inz);
                            }
                        }
                    }
                } else {
                	LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
                	for (int inz = 0; inz < cellsize; inz++) {
                		map.put(cellNameList.get(inz), cells[inz]);
                	}
                	cellMultiData.add(map);
                }
            } else {
                realDataCount++;
            }
        }

        return cellMultiData;
    }

    /**
     * 각각의 row 데이터를 얻어낸다.
     *
     * @param row
     * @return Object[]
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private Object[] getRow(Row row) {
        List list = new ArrayList();
        final int cellsize = row.getLastCellNum();
        for (int inx = 0; inx < cellsize; inx++) {
            Cell cell = row.getCell(inx);
            list.add(getCellValue(cell));
        }
        return list.toArray(new Object[0]);
    }

    /**
     * 타입을 구분해 준다.
     *
     * @param cell
     * @return Object
     */
    private Object getCellValue(Cell cell) {
        if (cell == null)
            return "";
        switch (cell.getCellType()) {
	        case Cell.CELL_TYPE_STRING:
	            return cell.getStringCellValue();
	        case Cell.CELL_TYPE_NUMERIC:
	        	//return Long.toString((long) cell.getNumericCellValue()) + "";
	    		
	        	//BigDecimal number = new BigDecimal(cell.getNumericCellValue());	// 2018.02.02 shm
	        	//return number.toString() + "";									// 2018.02.02 shm
	        	
	        	//return String.valueOf(new Double(cell.getNumericCellValue()).intValue());		// 2018.02.02 shm
	        	
	        	cell.setCellType(Cell.CELL_TYPE_STRING);
	        	return cell.getStringCellValue();
	        case Cell.CELL_TYPE_FORMULA:
	            // FORMULA임을 알려주기 위해서 앞에 = 을 붙인다.
	            if (isFormula) {
	            	return cell.getNumericCellValue();
	            } else {
	            	return cell.getCellFormula();
	            }
	        case Cell.CELL_TYPE_BLANK:
	            return "";
	        case Cell.CELL_TYPE_BOOLEAN:
	            return Boolean.valueOf(cell.getBooleanCellValue());
	        case Cell.CELL_TYPE_ERROR:
	            return new Byte(cell.getErrorCellValue());
	        default:
	        	return "";
        }
    }

}
