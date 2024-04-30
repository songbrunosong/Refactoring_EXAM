package com.brunosong.refactoring_exam.usefeignclient;

import com.brunosong.refactoring_exam.usefeignclient.other.CommonUtil;
import com.brunosong.refactoring_exam.usefeignclient.other.HttpManager;
import com.brunosong.refactoring_exam.usefeignclient.other.TemplateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AiCommonApi {

    @Autowired
    private HttpManager httpManager;

    /**
     * [AI] AI 서비스 정보 조회
     */
    public Map<String, Object> getAiServiceInfo(Map<String, Object> reqMap){

        Map<String, Object> aiInfo = null;

        try {

            int order_seq      	   = CommonUtil.obj2Integer(reqMap.get("order_seq"));
            TemplateReq req_data   = (TemplateReq)reqMap.get("req_data");

            //--------------------------------------------------
            //AI 서비스 정보 조회
            //--------------------------------------------------
            List<Map<String, Object>> aiInfoList = httpManager.requestGetUri("/ai/v1.0/report/aiService/orders/"+order_seq, req_data);

            if(aiInfoList != null && aiInfoList.size() > 0){
                aiInfo = aiInfoList.get(0);
            }

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

        return aiInfo;
    }


    /**
     * [AI] 준비학습 내 예측정보 조회
     */
    public Map<String, Object> getUnitEstimatedByUnit(AiApiRequest aiApiRequest) throws Exception {


        Map<String, Object> resultMap = null;

        try {

            String aiUri = "/ai/v1.0/unitEstimated/courses/"+ aiApiRequest.getCourse_code() + "/orders/" + aiApiRequest.getOrder_seq() +"/units/"+ aiApiRequest.getUnit_seq();

            List<Map<String, Object>> resultList = httpManager.requestGetUri(aiUri, aiApiRequest.getReq_data());
            if(resultList!=null && resultList.size() > 0){
                resultMap = resultList.get(0);
            }

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }

        return resultMap;
    }


}
