package com.brunosong.refactoring_exam.commonservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MetaItemVo {
    private String item_seq;
    private String master_seq;
    private String item_name;
    private String item_code;
}
