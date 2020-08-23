package com.youwiz.practices.exception;

import com.youwiz.practices.domain.Email;
import lombok.Getter;

@Getter
public class EmailDupliationException extends RuntimeException {

    private Email email;
    private String field;

    public EmailDupliationException(Email email) {
        this.field = "email";
        this.email = email;
    }
}
