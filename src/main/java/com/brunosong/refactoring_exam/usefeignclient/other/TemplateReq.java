package com.brunosong.refactoring_exam.usefeignclient.other;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class TemplateReq {

    private String SESSION_ID;
    private String H_CONTENT_TYPE;
    private String H_ACCEPT;
    private String H_HOST;
    private String H_IF_MODIFIED_SINCE;
    private String H_SERIAL_NUMBER;
    private String H_SERVICE_NAME;
    private String H_AUTH_TOKEN;
    private String H_TOKEN_SEQ;
    private String H_PUSH_TOKEN;
    private String H_SERVICE_TOKEN;
    private String H_CONTENT_LENGTH;
    private String H_CONTRACT_NUMBER;
    private String H_CUSTOMER_NUMBER;
    private String H_DEVICE_NAME;
    private String H_LAST_MODIFIED;
    private String H_REFERER;
    private String H_MOVED_URI;
    private String H_APP_PACKAGE;
    private String H_APP_VERSION;
    private String H_EMP_NUMBER;
    private Map<String, Object> G_REQ_DATA;
    private Map<String, Object> B_REQ_DATA;
    private Map<String, Object> U_REQ_DATA;


}
