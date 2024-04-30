package com.brunosong.refactoring_exam.usefeignclient;

import com.brunosong.refactoring_exam.usefeignclient.other.HttpManager;
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
    public Map<String, Object> selectEnspStatus(AiRequestParam aiRequestParam) throws Exception{


        Map<String, Object> enspStatusInfo = null;

        try {

            //--------------------------------------------------
            //컨텐츠 정보 조회
            //--------------------------------------------------
            List<Map<String, Object>> enspStatusInfoList
                    = httpManager.requestGetUri("/contents/v1.0/ensp/status/orders/"+ aiRequestParam.getOrder_seq() + "?SYSTEM_CODE=" + aiRequestParam.getSystem_code(), aiRequestParam.getReq_data());

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
