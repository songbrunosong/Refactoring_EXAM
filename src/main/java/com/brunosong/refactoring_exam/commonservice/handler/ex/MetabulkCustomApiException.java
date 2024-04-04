package com.brunosong.refactoring_exam.commonservice.handler.ex;

/* API 로 호출할 때 에러가 나면 처리 된다.*/
public class MetabulkCustomApiException extends RuntimeException {

    public MetabulkCustomApiException(String message) {
        super(message);
    }

}
