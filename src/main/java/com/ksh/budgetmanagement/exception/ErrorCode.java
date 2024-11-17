package com.ksh.budgetmanagement.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUNT("회원을 찾을 수 없습니다.", NOT_FOUND),
    ALREADY_USED_EMAIL("이미 사용중인 이메일입니다.", HttpStatus.BAD_REQUEST),
    ALREADY_USED_NICKNAME("이미 사용중인 닉네임입니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;
}
