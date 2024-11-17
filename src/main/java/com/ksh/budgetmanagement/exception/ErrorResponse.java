package com.ksh.budgetmanagement.exception;

import com.ksh.budgetmanagement.utils.DateUtil;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponse {
    private final int status;
    private final String message;
    private final String timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = DateUtil.LocalDateTimeToString(LocalDateTime.now());
    }
}
