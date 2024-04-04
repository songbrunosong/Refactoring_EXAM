package com.brunosong.refactoring_exam.commonservice.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MetaBulkParam {

    private String studyType;
    private String admId;
    private String metaCode;
    private String lv;
    private String startLevel;
    private String topUpSeq;
    private String excelFileName;
    private List<MetaBulkVo> metaBulkVoList;

}
