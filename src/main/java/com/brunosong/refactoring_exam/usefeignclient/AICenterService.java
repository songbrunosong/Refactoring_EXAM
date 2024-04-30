package com.brunosong.refactoring_exam.usefeignclient;

import com.brunosong.refactoring_exam.usefeignclient.other.CommonUtil;
import com.brunosong.refactoring_exam.usefeignclient.other.ResponseBuilder;
import com.brunosong.refactoring_exam.usefeignclient.other.TemplateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AICenterService {

    @Autowired
    private AiCommonApi aiCommonApi;


    public void selectAIRecommendToday(TemplateReq REQ_DATA, ResponseBuilder RESP_BUILDER) throws Exception {

        try{

            int order_seq         = CommonUtil.obj2Integer(REQ_DATA.getU_REQ_DATA().get("ORDER_SEQ"));   	//오더일련번호
            String system_code    = CommonUtil.getString(REQ_DATA.getU_REQ_DATA().get("SYSTEM_CODE")); 		//시스템코드

            Map<String, Object> paraMap = new HashMap<String, Object>(); //set param
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            List<Map<String, Object>> infos = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> studyList = new ArrayList<Map<String, Object>>();

            // .... 생략

            // 예측정보 조회 & 성취코스 정보 추가
            for(int i=0; i<studyList.size(); i++) {
                Map<String, Object> infoMap = studyList.get(i);

                paraMap.clear();
                paraMap.put("req_data", REQ_DATA);
                paraMap.put("order_seq", order_seq);
                paraMap.put("course_code", infoMap.get("COURSE_CODE").toString());
                paraMap.put("unit_seq", infoMap.get("UNIT_SEQ").toString());

                tmpMap = aiCommonApi.getUnitEstimatedByUnit(paraMap);

                studyList.get(i).put("DF_DATA", tmpMap);

                //성취코스 조회
                paraMap.put("grad_cd", infoMap.get("GRAD_CD").toString());
                paraMap.put("smst_cd", CommonUtil.obj2Integer(infoMap.get("SMST_CD")));

                // .... 생략
            }

            // .... 생략
            RESP_BUILDER.setRESP_BODY(infos);

        } catch(Exception e) {
            e.printStackTrace();

            throw e;
        }
    }

}
