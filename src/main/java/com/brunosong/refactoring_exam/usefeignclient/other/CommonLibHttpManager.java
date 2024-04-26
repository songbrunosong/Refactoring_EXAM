package com.brunosong.refactoring_exam.usefeignclient.other;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.DefaultHttpResponseFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/* 공통으로 만들어 둔 라이브러리 자바 클래스 (스프링 사용 X) */
public class CommonLibHttpManager {

    private static final Logger logger = LoggerFactory.getLogger(HttpManager.class);

    public CommonLibHttpManager() {
    }

    public static HashMap<String, String> initHeader(String serviceContext, TemplateReq REQ_DATA) {
        HashMap<String, String> headers = new HashMap();
        headers.put("if-modified-since", REQ_DATA.getH_IF_MODIFIED_SINCE());
        headers.put("serial-number", REQ_DATA.getH_SERIAL_NUMBER());
        headers.put("contract-number", REQ_DATA.getH_CONTRACT_NUMBER());
        headers.put("customer-number", REQ_DATA.getH_CUSTOMER_NUMBER());
        headers.put("content-type", REQ_DATA.getH_CONTENT_TYPE());
        headers.put("accept", REQ_DATA.getH_ACCEPT());
        headers.put("service-name", REQ_DATA.getH_SERVICE_NAME());
        headers.put("auth-token", REQ_DATA.getH_AUTH_TOKEN());
        headers.put("device-name", REQ_DATA.getH_DEVICE_NAME());
        headers.put("app-package", REQ_DATA.getH_APP_PACKAGE());
        headers.put("app-version", REQ_DATA.getH_APP_VERSION());
        headers.put("emp-number", REQ_DATA.getH_EMP_NUMBER());
        return headers;
    }

    public static HttpResponse<JsonNode> requestGetNonHeader(String resourceUrl) {
        HttpResponse<JsonNode> response = null;

        try {
            logger.debug("request url : {}", resourceUrl);
            GetRequest body = Unirest.get(resourceUrl);
            response = body.asJson();
            return response;
        } catch (Exception var3) {
            logger.error("requestGetNonHeader Exception : {}", var3.toString());
            return null;
        }
    }

    public static HttpResponse<JsonNode> requestGet(String serviceName, String resourceUri, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> response = null;

        try {
            String reqUrl = Constants.getServiceDomain(serviceName) + resourceUri;
            logger.debug("request url : {}", reqUrl);
            GetRequest body = Unirest.get(reqUrl);
            body.headers(initHeader(serviceName, REQ_DATA));
            response = body.asJson();
            return response;
        } catch (Exception var6) {
            logger.error("requestGet Exception : {}", var6.toString());
            return null;
        }
    }

    public static HttpResponse<JsonNode> requestGetUri(String resourceUri, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> response = null;

        try {
            String reqUrl = Constants.getServiceDomain() + resourceUri;
            logger.debug("request url : {}", reqUrl);
            GetRequest body = Unirest.get(reqUrl);
            if (body != null) {
                body.headers(initHeader("bookclub", REQ_DATA));
                response = body.asJson();
            } else {
                response = null;
            }

            return response;
        } catch (Exception var5) {
            logger.error("requestGetUri Exception : {}", var5.toString());
            return null;
        }
    }

    public static HttpResponse<JsonNode> requestGetNonHeaderInternal(String resourceUri) {
        HttpResponse<JsonNode> response = null;

        try {
            String reqUrl = Constants.getServiceDomain() + resourceUri;
            logger.debug("request url : {}", reqUrl);
            GetRequest body = Unirest.get(reqUrl);
            response = body.asJson();
            return response;
        } catch (Exception var4) {
            logger.error("requestGetNonHeaderInternal Exception : {}", var4.toString());
            return null;
        }
    }

    public static HttpResponse<JsonNode> requestGetInternal(String resourceUri, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> response = null;
        logger.debug("### [GET] INTERNAL_DOMAIN : {}", Constants.INTERNAL_DOMAIN);
        String reqUrl = Constants.INTERNAL_DOMAIN + resourceUri;
        response = requestGetInternalCommon(reqUrl, REQ_DATA);
        return response;
    }

    public static HttpResponse<JsonNode> requestGetCustomDomain(String resourceUrl, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> response = requestGetInternalCommon(resourceUrl, REQ_DATA);
        return response;
    }

    private static HttpResponse<JsonNode> requestGetInternalCommon(String reqUrl, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> response = null;
        int resultStatus = -1;

        try {
            logger.debug("### [GET-internal] URL :: {}", reqUrl);
            response = Unirest.get(reqUrl).headers(initHeader("bookclub", REQ_DATA)).asJson();
            resultStatus = response.getStatus();
            logger.debug("### [GET-internal] RESULT STATUS :: " + resultStatus);
        } catch (UnirestException var7) {
            logger.error("### [GET] requestGetInternalCommon UnirestException :: " + resultStatus);
            HttpResponseFactory factory = new DefaultHttpResponseFactory();
            org.apache.http.HttpResponse response1 = factory.newHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 502, (String)null), (HttpContext)null);
            response = new HttpResponse(response1, JsonNode.class);
        } catch (Exception var8) {
            /* 삭제  */
        }

        return response;
    }

    public static HttpResponse<JsonNode> requestGetQueryParam(String resourceUrl, HashMap<String, String> headers, HashMap<String, Object> fields) {
        HttpResponse<JsonNode> response = null;

        try {
            logger.debug("request url : {}", resourceUrl);
            GetRequest body = Unirest.get(resourceUrl);
            if (headers != null) {
                body.headers(headers);
            }

            if (fields != null) {
                body.queryString(fields);
            }

            response = body.asJson();
            return response;
        } catch (Exception var5) {
            logger.error("requestGetQueryParam Exception : {}", var5.toString());
            return null;
        }
    }

    public static HttpResponse<JsonNode> requestGetRouteParam(String resourceUrl, HashMap<String, String> headers, HashMap<String, Object> fields) {
        HttpResponse<JsonNode> response = null;

        try {
            logger.debug("request url : {}", resourceUrl);
            GetRequest body = Unirest.get(resourceUrl);
            if (headers != null) {
                body.headers(headers);
            }

            Set<Map.Entry<String, Object>> set1 = fields.entrySet();
            Iterator var7 = set1.iterator();

            while(var7.hasNext()) {
                Map.Entry<String, Object> me = (Map.Entry)var7.next();
                body.routeParam((String)me.getKey(), String.valueOf(me.getValue()));
            }

            response = body.asJson();
            return response;
        } catch (Exception var8) {
            logger.error("requestGetRouteParam Exception : {}", var8.toString());
            return null;
        }
    }

    public static HttpResponse<JsonNode> requestPostInternal(String resourceUri, TemplateReq REQ_DATA) {
        logger.debug("### [POST] INTERNAL_DOMAIN : {}", Constants.INTERNAL_DOMAIN);
        String reqUrl = Constants.INTERNAL_DOMAIN + resourceUri;
        HttpResponse<JsonNode> response = requestPostInternalCommon(reqUrl, REQ_DATA);
        return response;
    }

    public static HttpResponse<JsonNode> requestPostCustomDomain(String resourceUri, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> response = requestPostInternalCommon(resourceUri, REQ_DATA);
        return response;
    }

    private static HttpResponse<JsonNode> requestPostInternalCommon(String reqUrl, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> response = null;
        int resultStatus = -1;

        try {
            logger.debug("### [POST] URL :: {}", reqUrl);
            logger.debug("### [POST] B_REQ_DATA :: {}", REQ_DATA.getB_REQ_DATA().toString());
            JSONObject strMaptoJson = JsonManager.convertMapToJson(REQ_DATA.getB_REQ_DATA());
            String reqBodyEncode = ""; //삭제
            response = Unirest.post(reqUrl).headers(initHeader("bookclub", REQ_DATA)).body(reqBodyEncode).asJson();
            resultStatus = response.getStatus();
            logger.debug("### [POST] RESULT STATUS :: " + resultStatus);
        } catch (UnirestException var7) {
            logger.error("### [POST] requestPostInternalCommon UnirestException :: " + resultStatus);
            HttpResponseFactory factory = new DefaultHttpResponseFactory();
            org.apache.http.HttpResponse response1 = factory.newHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 502, (String)null), (HttpContext)null);
            response = new HttpResponse(response1, JsonNode.class);
        } catch (Exception var8) {
            /* 삭제 */
        }

        return response;
    }

    public static HttpResponse<JsonNode> requestPutInternal(String resourceUri, TemplateReq REQ_DATA) {
        logger.debug("### [PUT] INTERNAL_DOMAIN : {}", Constants.INTERNAL_DOMAIN);
        String reqUrl = Constants.INTERNAL_DOMAIN + resourceUri;
        HttpResponse<JsonNode> response = requestPostInternalCommon(reqUrl, REQ_DATA);
        return response;
    }

    public static HttpResponse<JsonNode> requestPutCustomDomain(String resourceUri, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> response = requestPutInternalCommon(resourceUri, REQ_DATA);
        return response;
    }

    private static HttpResponse<JsonNode> requestPutInternalCommon(String reqUrl, TemplateReq REQ_DATA) {
        HttpResponse<JsonNode> response = null;
        int resultStatus = -1;

        try {
            logger.debug("### [PUT] URL :: {}", reqUrl);
            logger.debug("### [PUT] B_REQ_DATA :: {}", REQ_DATA.getB_REQ_DATA().toString());
            JSONObject strMaptoJson = JsonManager.convertMapToJson(REQ_DATA.getB_REQ_DATA());
            String reqBodyEncode = ""; /* 삭제 */
            response = Unirest.put(reqUrl).headers(initHeader("bookclub", REQ_DATA)).body(reqBodyEncode).asJson();
            resultStatus = response.getStatus();
            logger.debug("### [PUT] RESULT STATUS :: " + resultStatus);
        } catch (UnirestException var7) {
            logger.error("### [PUT] requestPutInternalCommon UnirestException :: " + resultStatus);
            HttpResponseFactory factory = new DefaultHttpResponseFactory();
            org.apache.http.HttpResponse response1 = factory.newHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 502, (String)null), (HttpContext)null);
            response = new HttpResponse(response1, JsonNode.class);
        } catch (Exception var8) {
            /* 삭제 */
        }

        return response;
    }

    public void requestPostCustomDomainAsync(final String resourceUri, TemplateReq REQ_DATA) {
        try {
            logger.debug("requestPostCustomDomainAsync URL : {}", resourceUri);
            logger.debug("REQ_DATA.getB_REQ_DATA() : {}", REQ_DATA.getB_REQ_DATA().toString());
            JSONObject strMaptoJson = JsonManager.convertMapToJson(REQ_DATA.getB_REQ_DATA());
            String reqBodyEncode = ""; /* 삭제 */
            Unirest.post(resourceUri).headers(initHeader("bookclub", REQ_DATA)).body(reqBodyEncode).asJsonAsync(new Callback<JsonNode>() {
                public void failed(UnirestException e) {
                    /* 삭제 */
                }

                public void completed(HttpResponse<JsonNode> response) {
                    /* 삭제 */
                }

                public void cancelled() {
                    /* 삭제 */
                }
            });
        } catch (Exception var5) {
            var5.printStackTrace();
            logger.error("requestPostCustomDomainAsync Exception : {}", var5.toString());
        }

    }

    public static int requestPost(String pushUrl, HashMap<String, String> headers, HashMap<String, Object> fields, String bodyStr) {
        HttpResponse<JsonNode> response = null;

        try {
            HttpRequestWithBody body = Unirest.post(pushUrl);
            if (headers != null) {
                body.headers(headers);
            }

            if (fields != null) {
                body.fields(fields);
            }

            if (bodyStr != null) {
                body.body(bodyStr);
            }

            response = body.asJson();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return response.getStatus();
    }

    public static HttpResponse<JsonNode> requestPostAsync(String pushUrl, HashMap<String, String> headers, HashMap<String, Object> fields, String bodyStr) {
        HttpResponse<JsonNode> response = null;
        Future<HttpResponse<JsonNode>> future = null;

        try {
            HttpRequestWithBody body = Unirest.post(pushUrl);
            if (headers != null) {
                body.headers(headers);
            }

            if (fields != null) {
                body.fields(fields);
            }

            if (bodyStr != null) {
                body.body(bodyStr);
            }

            future = body.asJsonAsync();
            response = (HttpResponse)future.get(5L, TimeUnit.MINUTES);
            logger.debug("requestPostAsync response STATUS :: {}", response.getStatus());
        } catch (Exception var7) {
            logger.error("requestPostAsync Exception : {}", var7.toString());
        }

        return response;
    }

    public static HttpResponse<String> requestPostFileupload(String pushUrl, HashMap<String, String> header, String fieldname, String fullname) {
        HttpResponse<String> strResult = null;

        try {
            InputStream file = new FileInputStream(new File(fullname));
            String[] filenames = fullname.split("\\/");
            String filename = filenames[filenames.length - 1];
            HttpRequestWithBody body = Unirest.post(pushUrl);
            if (header != null) {
                body.headers(header);
            }

            body.field(fieldname, file, filename);
            strResult = body.asString();
            logger.debug("requestPostFileupload response STATUS :: {}", strResult.getStatus());
        } catch (Exception var9) {
            logger.error("requestPostFileupload Exception : {}", var9.toString());
        }

        return strResult;
    }

    private static void bypassSsl() {
        try {
            SSLContext sslContext = (new SSLContextBuilder()).loadTrustMaterial((KeyStore)null, new TrustSelfSignedStrategy() {
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            HttpClient creepyClient = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            Unirest.setHttpClient(creepyClient);
        } catch (KeyManagementException var2) {
            var2.printStackTrace();
        } catch (NoSuchAlgorithmException var3) {
            var3.printStackTrace();
        } catch (KeyStoreException var4) {
            var4.printStackTrace();
        }

    }

}
