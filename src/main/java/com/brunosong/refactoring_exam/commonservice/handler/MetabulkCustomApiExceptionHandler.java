package com.brunosong.refactoring_exam.commonservice.handler;

import com.brunosong.refactoring_exam.commonservice.handler.ex.ExcelMetaCheckException;
import com.brunosong.refactoring_exam.commonservice.handler.ex.MetabulkCustomApiException;
import com.brunosong.refactoring_exam.commonservice.response.MetaBulkResponseStatus;
import com.brunosong.refactoring_exam.commonservice.common.util.MetaBulkUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(1)
@ControllerAdvice
public class MetabulkCustomApiExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(MetabulkCustomApiExceptionHandler.class);

    @ExceptionHandler(ExcelMetaCheckException.class)
    public ResponseEntity<?> excelMetaCheckException(ExcelMetaCheckException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(
                MetaBulkUtil.setupSuccessResponse(MetaBulkResponseStatus.Status.ERROR, e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MetabulkCustomApiException.class)
    public ResponseEntity<?> apiException(MetabulkCustomApiException e) {
        logger.error(e.getMessage());
        return new ResponseEntity<>(
                MetaBulkUtil.setupSuccessResponse(MetaBulkResponseStatus.Status.ERROR, e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

}
