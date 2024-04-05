package com.brunosong.refactoring_exam.commonservice.service;

import com.brunosong.refactoring_exam.commonservice.common.util.MetaCheckUtil;
import com.brunosong.refactoring_exam.commonservice.dao.CommonDao;
import com.brunosong.refactoring_exam.commonservice.domain.*;
import com.brunosong.refactoring_exam.commonservice.handler.ex.MetabulkCustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MetaExcelUploadCommonService {

    private final CommonDao commonDao;

    @Transactional(value = "transactionManager")
    public List<MetaBulkVo> insertAndTempTableSetting(MetaBulkParam param , List<MetaBulkVo> metaBulkVoList ) {

        //1. 템프테이블을 지워준다.
        commonDao.deleteTempMetaTable();

        //2. 엑셀을 템프테이블에 넣어준다.
        commonDao.insertTempMetaTable(metaBulkVoList);

        return metaBulkVoList;

    }


    public List<MetaBulkVo> mapToMetaBulkConvert(List<LinkedHashMap<String, Object>> excelMapList) {

        List<MetaBulkVo> metaBulkVoList = new ArrayList<>();
        List<MetaItemVo> courseCodeList = commonDao.getCourseCode();
        List<MetaItemVo> areaCodeList = commonDao.getAreaCode();

        MetaCheckUtil.checkRowCount(excelMapList);

        Set<String> enumValues = new HashSet<>();
        for (CurriculumColumn enumValue : EnumSet.allOf(CurriculumColumn.class)) {
            enumValues.add(enumValue.getValue());
        }
        MetaCheckUtil.checkColumnName(excelMapList.get(0), enumValues);

        for (LinkedHashMap<String, Object> row : excelMapList) {

            MetaBulkVo bulkVo = new MetaBulkVo();
            bulkVo.setGrade((String) row.get("학년"));
            bulkVo.setTerm((String) row.get("학기"));
            bulkVo.setWeek((String) row.get("주차"));
            bulkVo.setPre_pro((String) row.get("예습/진도"));
            bulkVo.setClass_num((String) row.get("cl< >ss"));
            bulkVo.setCourse_code((String) row.get("과목"));
            bulkVo.setSubject_day(((String) row.get("과목차시")).replace("'", ""));
            bulkVo.setCourse_detail((String) row.get("대표단원(한글/국어)"));
            bulkVo.setArea((String) row.get("영역"));

            //과목코드 점검 및 변경
            String course_code = bulkVo.getCourse_code();
            String findCourseCode = courseCodeList.stream()
                    .filter(item -> item.getItem_name().equals(course_code))
                    .map(MetaItemVo::getItem_code)
                    .findFirst()
                    .orElse("");

            int equalCountGoodsTypeAndCourseCode = commonDao.getEqualGoodsTypeAndCourseName(bulkVo);
            if(findCourseCode.equals("") || equalCountGoodsTypeAndCourseCode == 0) {
                throw new MetabulkCustomApiException(ResultStatus.ERROR_LEVEL2_COURSE_CODE.getValue());
            }

            bulkVo.setCourse_code(findCourseCode);

            //영역(area) 점검 및 변경
            String area = bulkVo.getArea();
            String findAreaCode =
                    "".equals(area) ? "" : //공백값도 허용하기 때문에
                            "기본".equals(area) ? ("EVAI".equals(findCourseCode) ? "NN00" : "ES01")
                                    : areaCodeList.stream()
                                    .filter(metaItemVo -> metaItemVo.getItem_name().equals(area))
                                    .map(MetaItemVo::getItem_code)
                                    .findFirst()
                                    .orElse("NOT MATCH");
            if(findAreaCode.equals("NOT MATCH")) {
                throw new MetabulkCustomApiException(ResultStatus.ERROR_AREA_CODE.getValue());
            }
            bulkVo.setArea(findAreaCode);

            metaBulkVoList.add(bulkVo);
        }


        ResultStatus resultStatus = curriculumDuplicateNumCheck(metaBulkVoList);
        if(resultStatus != ResultStatus.SUCCESS) {
            throw new MetabulkCustomApiException(resultStatus.getValue());
        }

        return metaBulkVoList;
    }


    /* 순서 중복을 확인하는 메서드 */
    public ResultStatus curriculumDuplicateNumCheck(List<MetaBulkVo> metaBulkVoList){

        ResultStatus resultStatus = ResultStatus.ERROR_META_NUM_DUPLICATE;

        //단원, 차시, 코너, 목차 모두 확인되어야 함
        // 단원 -> 단원명이 다른데 단원 순서가 같은 경우(2레벨이 다르면 괜찮음), 출판사가 같거나 다를 경우도 고려
        if(compareByUnitNameAndSameUnitNum(metaBulkVoList)) return ResultStatus.ERROR_META_UNIT_NUM_DUPLICATE;

        // 차시 -> 차시명, 과목차시가 다른데 순서가 같은 경우(2레벨이 다르면 괜찮음, 단원이 다르면 괜찮음), 출판사가 같거나 다를 경우도 고려
        if(compareByDayNameContSubjectDay(metaBulkVoList)) return ResultStatus.ERROR_META_DAY_NUM_DUPLICATE;
        // 코너 -> 코너명이 다른데 순서가 같은 경우(2레벨이 다르면 괜찮음, 단원이 다르면 괜찮음, 차시 다르면 괜찮음), 출판사가 같거나 다를 경우도 고려
        if(compareByCornerNameContCornerSeq(metaBulkVoList)) return ResultStatus.ERROR_META_CORNER_NUM_DUPLICATE;

        // 목차 -> 목차명이 다른데 순서가 같은 경우(2레벨이 다르면 괜찮음, 단원이 다르면 괜찮음, 차시 다르면 괜찮음, 코너 다르면 괜찮음), 출판사가 같거나 다를 경우도 고려
        if(compareByTocNameTypeCodeTocSeq(metaBulkVoList)) return ResultStatus.ERROR_META_CHAP_NUM_DUPLICATE;

        return ResultStatus.SUCCESS;
    }


    private boolean compareByUnitNameAndSameUnitNum(List<MetaBulkVo> metaBulkVoList) {
//        return metaBulkVoList.stream().anyMatch(vo1 -> metaBulkVoList.stream()
//                .anyMatch(vo2 ->
//                        //출판사 고려
//                        (vo1.getGrade().equals(vo2.getGrade()) && vo1.getTerm().equals(vo2.getTerm()) && vo1.getPub_name().equals(vo2.getPub_name()))
//                                && !(vo1.getUnit_name().equals(vo2.getUnit_name())) && vo1.getUnit_num().equals(vo2.getUnit_num())));
        return false;

    }

    private boolean compareByDayNameContSubjectDay(List<MetaBulkVo> metaBulkVoList) {
//        return metaBulkVoList.stream().anyMatch(vo1 -> metaBulkVoList.stream()
//                .anyMatch(vo2 ->
//                        (vo1.getGrade().equals(vo2.getGrade()) && vo1.getTerm().equals(vo2.getTerm()) && vo1.getPub_name().equals(vo2.getPub_name()))
//                                && (vo1.getUnit_name().equals(vo2.getUnit_name()) && vo1.getUnit_num().equals(vo2.getUnit_num()))
//                                && (!vo1.getDay_name().equals(vo2.getDay_name()) || !vo1.getSubject_day().equals(vo2.getSubject_day()))
//                                && vo1.getDay_num().equals(vo2.getDay_num())));
        return false;
    }

    private boolean compareByCornerNameContCornerSeq(List<MetaBulkVo> metaBulkVoList) {
//        return metaBulkVoList.stream().anyMatch(vo1 -> metaBulkVoList.stream()
//                .anyMatch(vo2 ->
//                        (vo1.getGrade().equals(vo2.getGrade()) && vo1.getTerm().equals(vo2.getTerm()) && vo1.getPub_name().equals(vo2.getPub_name()))
//                                && (vo1.getUnit_name().equals(vo2.getUnit_name()) && vo1.getUnit_num().equals(vo2.getUnit_num()))
//                                && (vo1.getDay_name().equals(vo2.getDay_name()) && vo1.getDay_num().equals(vo2.getDay_num()))
//                                && !vo1.getConer_name().equals(vo2.getConer_name())
//                                && vo1.getConer_num().equals(vo2.getConer_num())));
        return false;
    }

    private boolean compareByTocNameTypeCodeTocSeq(List<MetaBulkVo> metaBulkVoList) {
//        return metaBulkVoList.stream().anyMatch(vo1 -> metaBulkVoList.stream()
//                .anyMatch(vo2 ->
//                        (vo1.getGrade().equals(vo2.getGrade()) && vo1.getTerm().equals(vo2.getTerm()) && vo1.getPub_name().equals(vo2.getPub_name()))
//                                && (vo1.getUnit_name().equals(vo2.getUnit_name()) && vo1.getUnit_num().equals(vo2.getUnit_num()))
//                                && (vo1.getDay_name().equals(vo2.getDay_name()) && vo1.getDay_num().equals(vo2.getDay_num()))
//                                && (vo1.getConer_name().equals(vo2.getConer_name()) && vo1.getConer_num().equals(vo2.getConer_num()))
//                                &&!vo1.getToc_name().equals(vo2.getToc_name())
//                                && vo1.getToc_num().equals(vo2.getToc_num())));
        return false;
    }


}
