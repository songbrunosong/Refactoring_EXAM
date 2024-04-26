package com.brunosong.refactoring_exam.usefeignclient.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;

import java.net.URI;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ResponseBuilder extends ParameterUtil implements ResponseEntity.BodyBuilder{

    private static final Logger logger = LoggerFactory.getLogger(ResponseBuilder.class);
    private TemplateReq REQ_DATA = null;
    private HttpStatus STATUS;
    private String STATUS_MSG;
    private String STATUS_MSG_SERVICE;
    private HttpHeaders HTTP_HEADER = null;
    private ResponseEntity.BodyBuilder HTTP_BODY = null;
    private MediaType CONTENT_TYPE = null;
    private long PROC_START = 0L;
    private long PROC_END = 0L;
    private String RES_CODE = "";
    private String RES_MSG = "";
    private String RES_MSG_SERVICE = "";
    private String REQ_DTIME = "";
    private String RES_DTIME = "";
    private String HANDLE_STIME = "";
    private String HANDLE_ETIME = "";
    private Map<String, Object> RESP_BODY = null;

    public void setRESP_BODY(Object value) {
        if (this.RESP_BODY == null) {
            this.RESP_BODY = new HashMap();
        }

        if (value != null) {
            this.RESP_BODY.put("RESP_RESULT", value);
        } else {
            this.RESP_BODY.put("RESP_RESULT", "");
        }

    }

    /*  구현 재현 안함  */

    @Override
    public ResponseEntity.BodyBuilder header(String headerName, String... headerValues) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder headers(HttpHeaders headers) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder headers(Consumer<HttpHeaders> headersConsumer) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder allow(HttpMethod... allowedMethods) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder eTag(String etag) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder lastModified(ZonedDateTime lastModified) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder lastModified(Instant lastModified) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder lastModified(long lastModified) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder location(URI location) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder cacheControl(CacheControl cacheControl) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder varyBy(String... requestHeaders) {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> build() {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder contentLength(long contentLength) {
        return null;
    }

    @Override
    public ResponseEntity.BodyBuilder contentType(MediaType contentType) {
        return null;
    }

    @Override
    public <T> ResponseEntity<T> body(T body) {
        return null;
    }
}
