package com.hongik.fe_sit_ival_server.global.exception;

public record ErrorResponse(String errorCodeName, String errorMessage) {

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.name(), errorCode.getMessage());
    }

    public static ErrorResponse of(ErrorCode errorCode, String errorMessage) {
        return new ErrorResponse(errorCode.name(), errorMessage);
    }
}
