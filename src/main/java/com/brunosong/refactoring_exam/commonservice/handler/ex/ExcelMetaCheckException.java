package com.brunosong.refactoring_exam.commonservice.handler.ex;

/* 엑셀에서 객체로 변할때 익셉션이 나면 호출 된다. */
public class ExcelMetaCheckException extends RuntimeException {

    public ExcelMetaCheckException(String message) {
        super(message);
    }

}
