package com.brunosong.refactoring_exam.usefeignclient.other;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ParameterUtil {

    private static final Logger logger = LoggerFactory.getLogger(ParameterUtil.class);
    private static Gson gson = new Gson();

    public ParameterUtil() {
    }

    public static Map<String, Object> setHeaders(HttpServletRequest request, JsonObject jsonObj) {
        Enumeration<String> headers = request.getHeaderNames();
        Map<String, Object> requestHeadersMap = new ConcurrentHashMap();

        while(true) {
            String name;
            String value;
            do {
                if (!headers.hasMoreElements()) {
                    return requestHeadersMap;
                }

                name = (String)headers.nextElement();
                value = request.getHeader(name);
                requestHeadersMap.put(name, CommonUtil.isNotEmpty(value) ? "1" : "0");
                jsonObj.addProperty("H_" + name.replaceAll("-", "_").toUpperCase(), value);
            } while(!name.equals("x-forwarded-host"));

            logger.debug("x-forwarded-host :: {} ", value);
            if (value.contains(":")) {
                String[] strForwardedHost = value.split(":");

                for(int i = 0; i < strForwardedHost.length; ++i) {
                    value = strForwardedHost[0];
                }
            }

            Constants.getInternalDomain(value);
            logger.debug("setHeaders INTERNAL_DOMAIN - {} ", Constants.INTERNAL_DOMAIN);
        }
    }

    public static void setUriParameters(HttpServletRequest request, JsonObject jsonObj) {
        jsonObj.add("U_REQ_DATA", (JsonElement)gson.fromJson(gson.toJson(request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE)), JsonObject.class));
    }

    public static void setParameters(HttpServletRequest request, JsonObject jsonObj) {
        Enumeration<String> parameters = request.getParameterNames();
        JsonObject jo = new JsonObject();

        while(parameters.hasMoreElements()) {
            String name = (String)parameters.nextElement();
            String value = request.getParameter(name);
            jsonObj.addProperty("G_" + name.toUpperCase(), value);
            jo.addProperty(name.toUpperCase(), value);
        }

        jsonObj.add("G_REQ_DATA", jo);
    }

    public static void setBody(HttpServletRequest request, JsonObject jsonObj) {
        StringBuffer reqBodyBuffer = new StringBuffer();
        String reqBodyStr = null;
        String line = null;
        boolean b64decode = true;
        BufferedReader reqBodyReader = null;

        try {
            reqBodyReader = request.getReader();

            while((line = reqBodyReader.readLine()) != null) {
                reqBodyBuffer.append(line);
            }

            if (reqBodyBuffer.length() > 0) {
                if (b64decode) {
                    reqBodyStr = new String(Base64.decodeBase64(reqBodyBuffer.toString()));
                } else {
                    reqBodyStr = reqBodyBuffer.toString();
                }
            }

            jsonObj.add("B_REQ_DATA", (JsonElement)gson.fromJson(reqBodyStr, JsonObject.class));
        } catch (IOException var16) {
            var16.printStackTrace();
        } finally {
            if (reqBodyReader != null) {
                try {
                    reqBodyReader.close();
                } catch (Exception var15) {
                    logger.debug("setBody Exception - {}", var15.toString());
                }
            }

        }

    }

    public static JsonObject createRequestParameters(HttpServletRequest httpRequest) {
        JsonObject jsonObj = null;
        boolean status = false;

        try {
            jsonObj = new JsonObject();
            Map<String, Object> requestHeadersMap = setHeaders(httpRequest, jsonObj);
            status = true; //삽입
            if (status) {
                setUriParameters(httpRequest, jsonObj);
                setParameters(httpRequest, jsonObj);
                setBody(httpRequest, jsonObj);
            } else {
                jsonObj = null;
            }
        } catch (Exception var4) {
            /* 삭제 */
            jsonObj = null;
        }

        return jsonObj;
    }

    public JsonObject createRequestParametersWeb(HttpServletRequest request) {
        JsonObject jsonObj = null;

        try {
            jsonObj = new JsonObject();
            setHeaders(request, jsonObj);
            setUriParameters(request, jsonObj);
            setParameters(request, jsonObj);
            setBody(request, jsonObj);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return jsonObj;
    }

    public static TemplateReq getReqData(HttpServletRequest request) {
        JsonObject jsonObj = createRequestParameters(request);
        TemplateReq tempalteRequestParameters = null;
        if (CommonUtil.isNotEmpty(jsonObj)) {
            tempalteRequestParameters = (TemplateReq)gson.fromJson(jsonObj, TemplateReq.class);
        }

        return tempalteRequestParameters;
    }

}
