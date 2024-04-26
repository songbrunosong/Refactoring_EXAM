package com.brunosong.refactoring_exam.usefeignclient.other;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Component
public class HttpManager {

    private final Logger logger = LoggerFactory.getLogger(HttpManager.class);

    @Value("${HttpManager.domain}")
    private String domain;

    @Value("${spring.profiles.active:local}")
    private String active;

    public String getDomain() {
        return domain;
    }

    public List<Map<String, Object>> requestGetUri(String uri, TemplateReq REQ_DATA) throws Exception {

        HttpResponse<JsonNode> internalResult = null;
        List<Map<String, Object>> resultList = new ArrayList<>();

        try{
            //null 체크
            if(REQ_DATA == null){
                throw new Exception("HttpManager를 사용하는데 REQ_DATA가 NULL입니다. 확인하세요.");
            }

            if("local".equals(active)){
                internalResult = CommonLibHttpManager.requestGetCustomDomain(domain + uri, REQ_DATA);
            } else {
                internalResult = CommonLibHttpManager.requestGetInternal(uri, REQ_DATA);
            }

            if((internalResult.getStatus() == HttpStatus.OK.value() || internalResult.getStatus() == HttpStatus.CREATED.value()) && internalResult.getBody().getObject().length() > 0){
                resultList = JsonManager.convertJsonNodeToMapList(internalResult);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }


    public List<Map<String, Object>> requestPostInternal(String uri, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> internalResult = null;
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        try{
            //null 체크
            if(REQ_DATA == null){
                throw new Exception("HttpManager를 사용하는데 REQ_DATA가 NULL입니다. 확인하세요.");
            }

            if("local".equals(active)){
                internalResult = CommonLibHttpManager.requestPostCustomDomain(domain + uri, REQ_DATA);
            } else {
                internalResult = CommonLibHttpManager.requestPostInternal(uri, REQ_DATA);
            }

            if(internalResult.getStatus() == HttpStatus.OK.value() || internalResult.getStatus() == HttpStatus.CREATED.value() && internalResult.getBody().getObject().length() > 0){
                resultList = JsonManager.convertJsonNodeToMapList(internalResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;
    }

    //--------------------------------------------------
    // API 호출 결과 RESP_RESULT 없는 경우가 있어 신규 메소드 생성
    //--------------------------------------------------
    public Map<String, Object> requestPostCustomReturn(String uri, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> internalResult = null;
        Map<String, Object> result = new HashMap<String, Object>();

        try{
            //null 체크
            if(REQ_DATA == null){
                throw new Exception("HttpManager를 사용하는데 REQ_DATA가 NULL입니다. 확인하세요.");
            }

            if("local".equals(active)){
                internalResult = CommonLibHttpManager.requestPostCustomDomain(domain + uri, REQ_DATA);
            } else {
                internalResult = CommonLibHttpManager.requestPostInternal(uri, REQ_DATA);
            }

            if((internalResult.getStatus() == HttpStatus.OK.value() || internalResult.getStatus() == HttpStatus.CREATED.value()) && internalResult.getBody().getObject().length() > 0){

                if (internalResult.getBody() != null) {
                    JSONObject respJson = internalResult.getBody().getObject();
                    if (respJson != null && respJson.length() > 0) {
                        result = JsonManager.jsonToMap(respJson.toString());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public Map<String, Object> jsonToMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if(value instanceof JSONArray) {
                value = jsonToList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                value = jsonToMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    public List<Object> jsonToList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<>();

        for(int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = jsonToList((JSONArray) value);
            }else if(value instanceof JSONObject) {
                value = jsonToMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    // Unirest 비동기 호출
    // 응답결과(future)를 가공하지 않아야 비동기 되는듯..
    // RESPONSE STATUS - 응답은 무조건 200인듯 (연동 서버가 문제가 있더라도)
    // 실제 연동 성공 여부는 콜백 함수 로그 확인
    // 타임아웃 설정 시 소켓타임아웃 설정이 중요
    /**
     * 비동기 API 통신 메서드 리펙토링 2023.06.22 - 10017154
     * HTTP 통신은 기존 Unirest -> RestTemplate  :: Unirest에선 예외처리가 충분하게 이뤄지지 않음. ex) UnknownHostException
     * @Async, CompletableFuture 활용해 비동기 메서드 고도화 진행
     */
    public void requestPostAsyncCustomDomain(String resourceUri, TemplateReq REQ_DATA) {
        logger.debug("### [POST] URL :: {}", resourceUri);
        logger.debug("### [POST] B_REQ_DATA :: {}", REQ_DATA.getB_REQ_DATA().toString());

        JSONObject strMaptoJson = JsonManager.convertMapToJson(REQ_DATA.getB_REQ_DATA());
        String reqBodyEncode = new String(Base64.encodeBase64(strMaptoJson.toString().getBytes()));
        long beforeTime = System.currentTimeMillis();

        CompletableFuture.supplyAsync(() -> {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setAll(CommonLibHttpManager.initHeader("bookclub", REQ_DATA));

            return restTemplate.postForObject(resourceUri, new HttpEntity<>(reqBodyEncode, headers), String.class);
        }).thenApply(response -> {
            try {
                return response;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).exceptionally(e -> {
            logger.error("CAUSE ERROR :: {}", e.getCause().getMessage());
            logger.error("Error :: ", e);
            return null;
        }).thenAcceptAsync(result -> {
            long afterTime = System.currentTimeMillis();
            long secDiffTime = (afterTime - beforeTime);
        });
    }

}
