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

			AiRequestParam aiRequestParam = AiRequestParam.builder()
					.req_data(REQ_DATA)
					.order_seq(order_seq)
					.system_code(system_code)
					.build();


			Map<String, Object> enspStatusInfo = aiCommonApi.selectEnspStatus(aiRequestParam);
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

			/* 1000줄 이상 코드 삭제 */


			AiRequestParam aiRequestParam = AiRequestParam.builder()
					.req_data(REQ_DATA)
					.order_seq(order_seq)
					.system_code(system_code)
					.svc_ymd(svc_ymd)
					.build();

			/* TARGET */
			Map<String, Object> enspStatusInfo = aiCommonApi.selectEnspStatus(aiRequestParam);
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

			/* 코드 1000줄 삭제 */

			AiRequestParam aiRequestParam = AiRequestParam.builder()
					.req_data(REQ_DATA)
					.order_seq(order_seq)
					.system_code(system_code)
					.svc_ymd("ENRD")
					.build();

			/* TARGET */
			Map<String, Object> enspStatusInfo = aiCommonApi.selectEnspStatus(aiRequestParam);

			Map<String, Object> enspInfo = new HashMap<String, Object>();
			enspInfo.put("ENSP_STATUS_INFO", enspStatusInfo);

			result.put("ENSP_INFO", enspInfo);

			RESP_BUILDER.setRESP_BODY(result);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



}