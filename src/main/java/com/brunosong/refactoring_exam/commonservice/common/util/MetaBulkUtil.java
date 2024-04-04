package com.brunosong.refactoring_exam.commonservice.common.util;


import com.brunosong.refactoring_exam.commonservice.domain.ResultStatus;
import com.brunosong.refactoring_exam.commonservice.handler.ex.MetabulkCustomApiException;
import com.brunosong.refactoring_exam.commonservice.response.MetaBulkResponseStatus;

import java.util.function.Supplier;

import static com.brunosong.refactoring_exam.commonservice.response.MetaBulkResponseStatus.*;

public class MetaBulkUtil {

    public static ResultStatus executeAndGetStatus(Supplier<ResultStatus> supplier , ResultStatus errorMsg){

        ResultStatus resultStatus = supplier.get();

        if(resultStatus != ResultStatus.SUCCESS) {
                throw new MetabulkCustomApiException(errorMsg.getValue() + " : " + resultStatus.getValue());
        }

        return resultStatus;
    }

    /* 성공 리스폰스 셋팅 */
    public static MetaBulkResponseStatus setupSuccessResponse(Status success, String message) {

        MetaBulkResponseStatus responseStatus = new MetaBulkResponseStatus();
        responseStatus.setStatus(success);
        responseStatus.setMessage(message);

        return responseStatus;
    }


    /* 성공 리스폰스 리턴객체 있을때 */
    public static <T> MetaBulkResponseStatus setupSuccessResponse(Status success, String message, T t ) {

        MetaBulkResponseStatus responseStatus = new MetaBulkResponseStatus();
        responseStatus.setStatus(success);
        responseStatus.setMessage(message);
        responseStatus.setData(t);

        return responseStatus;
    }

    /* 에러 리스폰스 셋팅 */
    public static MetaBulkResponseStatus setupErrorResponse(Status error, String message , Exception e) {

        MetaBulkResponseStatus responseStatus = setupSuccessResponse(error, message);
        responseStatus.setServerErrorMessage(e.getMessage());

        e.printStackTrace();

        return responseStatus;

    }

}
