package com.youwiz.practices.exception;

import com.youwiz.practices.error.ErrorCode;
import lombok.Getter;

@Getter
public class PasswordFailedExceededException extends RuntimeException {
    private ErrorCode errorCode;

    public PasswordFailedExceededException() {
        this.errorCode = ErrorCode.PASSWORD_FAILED_EXCEEDED;
    }
}
