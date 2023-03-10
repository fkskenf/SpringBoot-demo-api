package com.blog.demo.common.exception.handler;

import com.blog.demo.common.dto.ErrorResponse;
import com.blog.demo.controller.test.GlobalExceptionHandlerController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice(basePackageClasses = GlobalExceptionHandlerController.class) // body 사용가능
@RestControllerAdvice // body 사용가능
@Order(Ordered.HIGHEST_PRECEDENCE) //  보다 먼저
public class GlobalExceptionHandler {
    protected Logger logger = LogManager.getLogger(this.getClass());

    @ResponseStatus(HttpStatus.BAD_REQUEST) // HTTP Status Code 적용
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse processValidationError(MethodArgumentNotValidException exception) {
        BindingResult bindingResult = exception.getBindingResult();

        ErrorResponse errorResponse = new ErrorResponse();

        StringBuffer errorMsg = new StringBuffer();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMsg.append(fieldError.getField()).append(", ");
        }

        errorResponse.setResultCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setResultMsg("[" + errorMsg.substring(0, errorMsg.length() - 2).toString() + "] 필수파라미터 체크 바랍니다.");

        return errorResponse;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // HTTP Status Code 적용
    @ExceptionHandler({ Exception.class })
    protected ErrorResponse handleServerException(Exception exception) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setResultCode(HttpStatus.BAD_REQUEST.value());
        errorResponse.setResultMsg(exception.getMessage());
        return errorResponse;
    }
}
