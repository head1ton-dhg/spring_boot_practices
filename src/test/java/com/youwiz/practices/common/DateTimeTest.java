package com.youwiz.practices.common;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DateTimeTest {

    @Test
    public void DateTime() {
        final DateTime dateTime = new DateTime();

        assertNull(dateTime.getCreatedAt());
        assertNull(dateTime.getUpdatedAt());
    }
}