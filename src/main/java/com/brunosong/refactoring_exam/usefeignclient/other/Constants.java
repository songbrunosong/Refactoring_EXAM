package com.brunosong.refactoring_exam.usefeignclient.other;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Constants {

    private static Properties readSystemConfig = null;
    private static Properties readServiceConfig = null;
    private static final Logger logger = LoggerFactory.getLogger(Constants.class);
    public static final String FLAG_NOT_EMPTY = "1";
    public static final String FLAG_EMPTY = "0";
    public static final String CHARSET = "UTF-8";
    public static final String NAME_SYSTEM = "wjthinkbig";
    public static final String NAME_SYSTEM_H_SERVICE_NAME = "H_SERVICE_NAME";
    public static final String NAME_SYSTEM_H_AUTH_TOKEN = "H_AUTH_TOKEN";
    public static final String NAME_SYSTEM_G_DATA = "G_REQ_DATA";
    public static final String NAME_SYSTEM_U_DATA = "U_REQ_DATA";
    public static final String NAME_SYSTEM_B_DATA = "B_REQ_DATA";
    public static final String NAME_REQ_H_HOST = "host";
    public static final String NAME_REQ_H_CONTENT_TYPE = "content-type";
    public static final String NAME_REQ_H_ACCEPT = "accept";
    public static final String NAME_REQ_H_IFMODIFIED_SINCE = "if-modified-since";
    public static final String NAME_REQ_H_CONTRACT_NUMBER = "contract-number";
    public static final String NAME_REQ_H_CUSTOMER_NUMBER = "customer-number";
    public static final String NAME_REQ_H_SERIAL_NUMBER = "serial-number";
    public static final String NAME_REQ_H_SERVICE_NAME = "service-name";
    public static final String NAME_REQ_H_AUTH_TOKEN = "auth-token";
    public static final String NAME_REQ_H_PUSH_TOKEN = "push-token";
    public static final String NAME_REQ_H_SERVICE_TOKEN = "service-token";
    public static final String NAME_REQ_H_DEVICE_NAME = "device-name";
    public static final String NAME_REQ_H_APP_PACKAGE = "app-package";
    public static final String NAME_REQ_H_APP_VERSION = "app-version";
    public static final String NAME_REQ_H_EMP_NUMBER = "emp-number";
    public static final String NAME_RES_H_CODE = "code";
    public static final String NAME_RES_H_MSG = "msg";
    public static final String NAME_RES_H_MSG_SERVICE = "msg-service";
    public static final String NAME_RES_H_DTIME = "resp-dTime";
    public static final String NAME_RES_H_HANDLE_STIME = "handle-sTime";
    public static final String NAME_RES_H_HANDLE_ETIME = "handle-eTime";
    public static final String NAME_RES_B_DATA = "RESP_RESULT";
    public static final String ALIAS_PARAMETER_HEADER = "H";
    public static final String ALIAS_PARAMETER_BODY = "B";
    public static final String ALIAS_PARAMETER_GET = "G";
    public static final String ALIAS_PARAMETER_URI = "U";
    public static final String TYPE_PUSH_ERROR = "E";
    public static final String TYPE_PUSH_FATAL = "F";
    public static final String TYPE_PUSH_WARNING = "W";
    public static final String TYPE_PUSH_INFO = "I";
    public static final String TYPE_PUSH_DEBUG = "D";
    public static final String SEPARATOR_HEADER_NAME = "-";
    public static final String SEPARATOR_PARAMETER_NAME = "_";
    public static final String SEPARATOR_URI = "/";
    public static final double MEASURE_MSEC_PER_NS = 1000000.0;
    public static final double MEASURE_SEC_PER_NS = 1.0E9;
    public static final long MEASURE_SEC_PER_MS = 1000L;
    public static final long MEASURE_MIN_PER_MS = 60000L;
    public static final long MEASURE_HOUR_PER_MS = 3600000L;
    public static final long MEASURE_DAY_PER_MS = 86400000L;
    public static final long MEASURE_MONTH_PER_MS = 2592000000L;
    public static final String FORMAT_DECIMAL_1 = "%.1f";
    public static final String FORMAT_DECIMAL_2 = "%.2f";
    public static final String FORMAT_DECIMAL_3 = "%.3f";
    public static final String FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss";
    public static JSONObject CONF_FILE_INFO;
    public static String INTERNAL_DOMAIN;

    static {
        readSystemConfig = new Properties(); //ServerProfiles.readSystemConfig();
        readServiceConfig = new Properties(); //ServerProfiles.readCurrentConfig2();
        INTERNAL_DOMAIN = "https://gw-dev.brunosong.com";
    }

    public Constants() {
    }

    public static void init() {
    }

    protected static String getSystemProperty(String key) {
        String value = CommonUtil.getString(readSystemConfig.getProperty(key));
        return value;
    }

    public static String getServiceDomain() {
        String value = getSystemProperty("serviceDomain");
        return value;
    }

    public static String getServiceProperty(String key) {
        String value = CommonUtil.getString(readServiceConfig.getProperty(key));
        return value;
    }

    public static String getServiceDomain(String serviceName) {
        String value = getSystemProperty("serviceDomain");
        return value;
    }

    public static void getInternalDomain(String gwType) {
        INTERNAL_DOMAIN = getSystemProperty("internal_domain." + gwType);
    }
}
