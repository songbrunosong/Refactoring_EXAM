package com.brunosong.refactoring_exam.usefeignclient.other;

public class CommonUtil {

    public static String getString(Object str) {
        return str == null ? "" : str.toString();
    }

    public static int obj2Integer(Object obj) {
        return isNotEmpty(obj) && isNumberValidate(obj) ? Integer.parseInt(String.valueOf(obj).trim()) : 0;
    }

    public static boolean isNotEmpty(Object obj) {
        boolean bResp = false;
        if (obj != null) {
            bResp = true;
            if (obj instanceof String && "".equals(obj)) {
                bResp = false;
            }
        }

        return bResp;
    }

    public static boolean isNumberValidate(Object s) {
        return isNumberValidate(obj2String(s));
    }

    public static String obj2String(Object obj) {
        return isNotEmpty(obj) ? String.valueOf(obj) : "";
    }

}
