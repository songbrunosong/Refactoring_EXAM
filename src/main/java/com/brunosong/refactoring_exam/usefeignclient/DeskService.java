package com.brunosong.refactoring_exam.usefeignclient;

import com.brunosong.refactoring_exam.usefeignclient.other.CommonUtil;
import com.brunosong.refactoring_exam.usefeignclient.other.ResponseBuilder;
import com.brunosong.refactoring_exam.usefeignclient.other.TemplateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeskService {

	private String wjContDomainDev = "https://cont-dev.brunosong.com:9107";
	private String wjContDomain = "https://cont.brunosong.com:9107";

	@Autowired
	private AiCommonApi aiCommonApi;

	@Value("${spring.profiles.active:local}")
	private String active;


	public void selectEnspStatistics(TemplateReq REQ_DATA, ResponseBuilder RESP_BUILDER) throws Exception {

		try {

			// .... 생략

			int order_seq   = CommonUtil.obj2Integer(REQ_DATA.getU_REQ_DATA().get("ORDER_SEQ"));   //오더일련번호
			String course_code = CommonUtil.getString(REQ_DATA.getU_REQ_DATA().get("COURSE_CODE")); //과목코드
			String system_code = CommonUtil.getString(REQ_DATA.getU_REQ_DATA().get("SYSTEM_CODE")); //시스템코드
			int unit_seq    = CommonUtil.obj2Integer(REQ_DATA.getU_REQ_DATA().get("UNIT_SEQ"));    //단원일련번호
			int lesn_seq    = CommonUtil.obj2Integer(REQ_DATA.getU_REQ_DATA().get("LESN_SEQ"));    //차시일련번호
			int coner_seq   = CommonUtil.obj2Integer(REQ_DATA.getU_REQ_DATA().get("CONER_SEQ"));   //코너일련번호
			int chap_seq    = CommonUtil.obj2Integer(REQ_DATA.getU_REQ_DATA().get("CHAP_SEQ"));   //목차일련번호
			String contract_number 	= CommonUtil.getString(REQ_DATA.getH_CONTRACT_NUMBER());		//계약번호

			List<Map<String, Object>> infos 	= new ArrayList<Map<String, Object>>();
			Map<String, Object> resultMap = new HashMap<String, Object>();

			// .... 생략


			Map<String, Object> gdncByChapParaMap = new HashMap<>();
			gdncByChapParaMap.put("order_seq",   order_seq);
			gdncByChapParaMap.put("course_code", course_code);
			gdncByChapParaMap.put("system_code", system_code);
			gdncByChapParaMap.put("unit_seq",    unit_seq);
			gdncByChapParaMap.put("lesn_seq",    lesn_seq);
			infos = selectStudyGdncByChap(gdncByChapParaMap);

			if(infos != null && !infos.isEmpty()){

				AiApiRequest aiApiRequest = AiApiRequest.builder()
						.req_data(REQ_DATA)
						.order_seq(order_seq)
						.unit_seq(unit_seq)
						.course_code(course_code)
						.build();

				Map<String, Object> unitEstimatedInfo = aiCommonApi.getUnitEstimatedByUnit(aiApiRequest);
				if(unitEstimatedInfo != null){
					infos.get(0).putAll(unitEstimatedInfo);
				}
			}

			// .... 생략
			RESP_BUILDER.setRESP_BODY(resultMap);

		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}





	/* 예제 */
	public List<Map<String, Object>> selectStudyGdncByChap(Map<String, Object> reqMap) throws Exception {
		return new ArrayList<Map<String, Object>>(); // 다른 서비스 호출 예제로
	}



}