package com.brunosong.refactoring_exam.commonservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaBulkResponseStatus<T> {

    private Status status;
    private String message;
    private String serverErrorMessage;
    private T data;

    public enum Status {
        SUCCESS,
        ERROR;
    }

}
