package com.brunosong.refactoring_exam.usefeignclient;

import com.brunosong.refactoring_exam.usefeignclient.other.TemplateReq;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AiApiRequest {

    private TemplateReq req_data;
    private int order_seq;
    private String course_code;
    private int unit_seq;

}
