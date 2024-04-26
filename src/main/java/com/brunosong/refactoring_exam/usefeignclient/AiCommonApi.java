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
     * [contents] AI 영어추천
     * @param  Map<String, Object>
     * @return Map<String, Object>
     * @throws Exception
     *
     */
    public Map<String, Object> selectEnspStatus(Map<String, Object> reqMap) throws Exception{


        Map<String, Object> enspStatusInfo = null;

        try {

            TemplateReq req_data 	= (TemplateReq)reqMap.get("req_data");
            String order_seq		= CommonUtil.getString(reqMap.get("order_seq"));
            String system_code 		= CommonUtil.getString(reqMap.get("system_code"));

            //--------------------------------------------------
            //컨텐츠 정보 조회
            //--------------------------------------------------
            List<Map<String, Object>> enspStatusInfoList = httpManager.requestGetUri("/contents/v1.0/ensp/status/orders/"+order_seq+"?SYSTEM_CODE="+system_code, req_data);

            if(enspStatusInfoList != null && enspStatusInfoList.size() > 0){
                enspStatusInfo = enspStatusInfoList.get(0);
            }

        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        }

        return enspStatusInfo;
    }


}
