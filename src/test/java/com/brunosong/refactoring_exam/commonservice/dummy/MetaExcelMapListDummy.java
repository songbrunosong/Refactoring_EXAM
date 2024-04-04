package com.brunosong.refactoring_exam.commonservice.dummy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MetaExcelMapListDummy {

    protected List<LinkedHashMap<String, Object>> newExcelMapList(String[] emptyStrs) {
        return newExcelMapList(emptyStrs,1);
    }

    protected List<LinkedHashMap<String, Object>> newExcelMapList(String[] emptyStrs, int cnt) {

        List<LinkedHashMap<String, Object>> list = new ArrayList<>();

        for (int i = 0; i < cnt; i++) {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            map.put("학년","Level 1");
            map.put("학기","정규");
            map.put("주차","");
            map.put("예습/진도","진도");
            map.put("cl< >ss","");
            map.put("과목","퀴즈 놀이");
            map.put("과목차시","1-1");
            map.put("대표단원(한글/국어)","영어");
            map.put("영역","어휘 확장");

            /* 빈값을 만들어 준다. */
            if(emptyStrs != null) {
                for (String str : emptyStrs) {
                    map.put(str,"");
                }
            }

            list.add(map);
        }

        return list;
    }





}
