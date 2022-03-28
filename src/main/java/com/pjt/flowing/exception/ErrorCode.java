package com.pjt.flowing.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_NOT_PURMITTED(HttpStatus.NOT_FOUND, "404_Authorization_0", "아이디가 일치하지 않거나 존재하지 않습니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;
}
