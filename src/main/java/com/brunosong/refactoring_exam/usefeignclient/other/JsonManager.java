package com.brunosong.refactoring_exam.usefeignclient.other;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonManager {

    private static final Logger logger = LoggerFactory.getLogger(JsonManager.class);
    private static final Gson gson = new Gson();

    public JsonManager() {
    }

    public static Map<String, Object> jsonToMap(String json) {
        return (Map)gson.fromJson(json, Map.class);
    }

    public static JSONObject convertMapToJson(Map<String, Object> map) {
        JSONObject json = new JSONObject();
        Iterator var3 = map.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry)var3.next();
            String key = (String)entry.getKey();
            Object value = entry.getValue();
            json.put(key, value);
        }

        return json;
    }

    public static JSONObject convertJsonNodeToJson(JsonNode jsonNode) {
        JSONObject jsonObj = new JSONObject();

        try {
            if (jsonNode.isArray()) {
                throw new UnirestException("The request returns a JSON Array. Json: " + jsonNode.getArray().toString(4));
            }

            jsonObj = jsonNode.getObject();
        } catch (Exception var3) {

        }

        return jsonObj;
    }


    public static List<Map<String, Object>> convertJsonNodeToMapList(HttpResponse<JsonNode> jsonNode) {
        List<Map<String, Object>> returnListMap = new ArrayList();
        if (jsonNode.getBody() != null) {
            JSONObject respJson = convertJsonNodeToJson((JsonNode)jsonNode.getBody());
            if (respJson != null && respJson.length() > 0) {
                Object object = respJson.get("RESP_RESULT");
                if (object != null) {
                    returnListMap = convertObjToMapList(object);
                }
            }
        }

        return (List)returnListMap;
    }


    public static List<Map<String, Object>> convertObjToMapList(Object object) {
        List<Map<String, Object>> returnListMap = null;
        Map<String, Object> resultMap = null;
        returnListMap = new ArrayList();
        if (object instanceof JSONObject) {
            returnListMap.add(jsonToMap(object.toString()));
        } else if (object instanceof JSONArray) {
            new JSONArray();
            JSONArray jsonArr = (JSONArray)object;
            returnListMap = new ArrayList();
            if (jsonArr.length() > 0) {
                for(int i = 0; i < jsonArr.length(); ++i) {
                    JSONObject jobj = jsonArr.getJSONObject(i);
                    resultMap = jsonToMap(jobj.toString());
                    returnListMap.add(resultMap);
                }
            }
        }

        return returnListMap;
    }

}
