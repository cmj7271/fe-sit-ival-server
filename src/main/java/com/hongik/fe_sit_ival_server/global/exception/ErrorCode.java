package com.hongik.fe_sit_ival_server.global.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400 BAD_REQUEST
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "유효하지 않은 인자입니다."),
    METHOD_ILLEGAL_ACCESS(HttpStatus.BAD_REQUEST, "잘못된 접근입니다."),

    // 401 UNAUTHORIZED
    UNAUTHORIZED_DOMAIN(HttpStatus.UNAUTHORIZED, "이 도메인에 접근할 권한이 없습니다."),

    // 403 FORBIDDEN
    BANNED(HttpStatus.FORBIDDEN, "정지 상태입니다."),

    // 404 NOT_FOUND
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않습니다."),

    // 500 INTERNAL_SERVER_ERROR
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");

    private final HttpStatus status;
    private final String message;
}
