package com.brunosong.refactoring_exam.usefeignclient;

import com.brunosong.refactoring_exam.usefeignclient.other.TemplateReq;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AiRequestParam {

    private TemplateReq req_data;
    private int order_seq;
    private String system_code;
    private String svc_ymd;
    private String course_code;

}
