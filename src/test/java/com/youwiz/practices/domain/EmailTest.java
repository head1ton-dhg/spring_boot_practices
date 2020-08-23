package com.youwiz.practices.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
public class EmailTest {

    @Test
    public void email() {
        final String id = "test";
        final String host = "@test.com";
        final Email email = Email.builder()
                .value(id + host)
                .build();

        assertThat(email.getHost(), is(host));
        assertThat(email.getId(), is(id));
    }
}