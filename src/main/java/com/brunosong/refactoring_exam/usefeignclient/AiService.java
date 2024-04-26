package com.brunosong.refactoring_exam.usefeignclient;

import com.brunosong.refactoring_exam.usefeignclient.other.CommonUtil;
import com.brunosong.refactoring_exam.usefeignclient.other.ResponseBuilder;
import com.brunosong.refactoring_exam.usefeignclient.other.TemplateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiService {

	private String wjContDomainDev = "https://cont-dev.brunosong.com:9107";
	private String wjContDomain = "https://cont.brunosong.com:9107";

	@Autowired
	private AiCommonApi aiCommonApi;

	@Value("${spring.profiles.active:local}")
	private String active;

	
	/**ai학습센터 영어***********/
	public void selectEnspStatistics(TemplateReq REQ_DATA, ResponseBuilder RESP_BUILDER) throws Exception {

		try {

			int order_seq      	= CommonUtil.obj2Integer(REQ_DATA.getU_REQ_DATA().get("ORDER_SEQ"));		//오더일련번호
			String system_code 	= CommonUtil.getString(REQ_DATA.getU_REQ_DATA().get("SYSTEM_CODE"));		//시스템코드


			Map<String, Object> resultMap = new HashMap<String, Object>();

			/* 500 줄 삭제 */

			// 수준별 영어 도서관
			Map<String, Object> enspParam = new HashMap<String, Object>();
			enspParam.put("order_seq", order_seq);
			enspParam.put("system_code", system_code);
			enspParam.put("req_data", REQ_DATA);

			Map<String, Object> enspStatusInfo = aiCommonApi.selectEnspStatus(enspParam);
			resultMap.put("ENSP_STATUS_INFO", enspStatusInfo);

			RESP_BUILDER.setRESP_BODY(resultMap);

		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void selectEnaiAiRcmnCourse(TemplateReq REQ_DATA, ResponseBuilder RESP_BUILDER) throws Exception {
		
		try {

			int order_seq      	= CommonUtil.obj2Integer(REQ_DATA.getU_REQ_DATA().get("ORDER_SEQ"));
			String system_code 	= CommonUtil.getString(REQ_DATA.getU_REQ_DATA().get("SYSTEM_CODE"));

			Map<String, Object> result = new HashMap<String, Object>();

			String svc_ymd = "서비스로직";

			/* 코드 삭제 */

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("req_data", REQ_DATA);
			paramMap.put("order_seq", order_seq);
			paramMap.put("system_code", system_code);
			paramMap.put("svc_ymd", svc_ymd);

			/* 1000줄 이상 코드 삭제 */

			/* TARGET */
			Map<String, Object> enspStatusInfo = aiCommonApi.selectEnspStatus(paramMap);
			result.put("ENSP_STATUS_INFO", enspStatusInfo);

			/* 수많은 후 작업 코드 삭제 */

			RESP_BUILDER.setRESP_BODY(result);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public void selectEnaiMyLevels(TemplateReq REQ_DATA, ResponseBuilder RESP_BUILDER) throws Exception {
		
		try {
			int order_seq      = CommonUtil.obj2Integer(REQ_DATA.getU_REQ_DATA().get("ORDER_SEQ"));		//오더일련번호
			String system_code = CommonUtil.getString(REQ_DATA.getU_REQ_DATA().get("SYSTEM_CODE"));		//시스템코드

			Map<String, Object> result = new HashMap<String, Object>();

			/* 코드 200줄 삭제 */

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("order_seq", order_seq);
			paramMap.put("system_code", system_code);


			/* 코드 1000줄 삭제 */


			paramMap.clear();
			paramMap.put("order_seq", order_seq);
			paramMap.put("system_code", system_code);
			paramMap.put("req_data", REQ_DATA);
			paramMap.put("course_code", "ENRD");

			Map<String, Object> enspInfo = new HashMap<String, Object>();

			// 수준별 영어도서관 현재 단계 정보
			Map<String, Object> enspStatusInfo = aiCommonApi.selectEnspStatus(paramMap);
			enspInfo.put("ENSP_STATUS_INFO", enspStatusInfo);

			result.put("ENSP_INFO", enspInfo);

			RESP_BUILDER.setRESP_BODY(result);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



}