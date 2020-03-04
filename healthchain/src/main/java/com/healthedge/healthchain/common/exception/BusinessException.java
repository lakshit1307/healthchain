package com.healthedge.healthchain.common.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private String errorCode;

    private HttpStatus httpStatus;

    public String getErrorCode () {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public static BusinessException throwBusinessException (HttpStatus httpStatus, String errorCode) throws BusinessException {
        return throwBusinessException(errorCode, null, httpStatus);
    }

    public static BusinessException throwBusinessException (String errorCode) throws BusinessException {
        return throwBusinessException(errorCode, null, null);
    }

    public static BusinessException throwBusinessException (String errorCode, Throwable cause, HttpStatus httpStatus) throws BusinessException {
        String message = ExceptionResourceBundle.getInstance().getErrorMessage(errorCode);
        throw new BusinessException(errorCode, message, cause, httpStatus);
    }

    public BusinessException (String errorCode, String message, Throwable cause, HttpStatus httpStatus) {
        super(message, cause == null ? null : cause);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;

    }



}

