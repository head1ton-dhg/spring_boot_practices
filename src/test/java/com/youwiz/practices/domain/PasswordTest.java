package com.youwiz.practices.domain;

import com.youwiz.practices.exception.PasswordFailedExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class PasswordTest {

    private final long TTL = 7776_004L;
    private String passwordValue;
    private Password password;

    @BeforeEach
    public void setUp() {
        passwordValue = "password001";
        password = Password.builder().value(passwordValue).build();
    }

    @Test
    public void testPassword() {
        assertTrue(password.isMatched(passwordValue));
        assertFalse(password.isExpiration());
        assertNotNull(password.getValue());
        assertEquals(password.getFailedCount(), 0);
        assertNotNull(password.getExpirationDate());
    }

    @Test
    public void resetFailedCount_비밀번호가일치하는경우_실패카운트가초기회된다() {
        password.isMatched("notMatchedPassword");
        password.isMatched("notMatchedPassword");
        password.isMatched("notMatchedPassword");

        assertEquals(password.getFailedCount(), 3);

        password.isMatched(passwordValue);
        assertEquals(password.getFailedCount(), 0);
    }

    @Test
    public void increaseFailCount_비밀번호가일치하지않는경우_실패카운트가증가된다() {
        password.isMatched("notMatchedPassword");
        password.isMatched("notMatchedPassword");
        password.isMatched("notMatchedPassword");
        assertEquals(password.getFailedCount(), 3);
    }

    @Test
    public void increaseFailCount_6회이상_PasswordFailedExceededException() {
        assertThrows(PasswordFailedExceededException.class, () -> {
            password.isMatched("notMatchedPassword");
            password.isMatched("notMatchedPassword");
            password.isMatched("notMatchedPassword");
            password.isMatched("notMatchedPassword");
            password.isMatched("notMatchedPassword");

            password.isMatched("notMatchedPassword");
        });
    }

    @Test
    public void changePassword_비밀번호_일치하는경우_비밀번호가변경된다() {
        final String newPassword = "newPassword";
        password.changePassword(newPassword, passwordValue);

        assertTrue(password.isMatched(newPassword));
        assertEquals(password.getFailedCount(), 0);
        assertEquals(password.getTtl(), TTL);
        assertTrue(password.getExpirationDate().isAfter(LocalDateTime.now().plusDays(90)));
    }

    @Test
    public void changePassword_실패카운트가4일경우_일치하는경우_비밀번호가변경되며_실패카운트가초기화된다() {
        password.isMatched("netMatchedPassword");
        password.isMatched("netMatchedPassword");

        final String newPassword = "newPassword";
        password.changePassword(newPassword, passwordValue);
        assertEquals(password.getFailedCount(), 0);
    }

    @Test
    public void changePassword_비밀번호변경이_5회이상일치하지않으면_PasswordFailedExceededException() {
        assertThrows(PasswordFailedExceededException.class, () -> {
            final String newPassword = "newPassword";
            final String oldPassword = "oldPassword";

            password.changePassword(newPassword, oldPassword);
            password.changePassword(newPassword, oldPassword);
            password.changePassword(newPassword, oldPassword);
            password.changePassword(newPassword, oldPassword);
            password.changePassword(newPassword, oldPassword);
            password.changePassword(newPassword, oldPassword);
        });
    }

    @Test
    public void changePassword_비밀번호변경이_4회일치하지않더라도_5회에서일치하면_실패카운트초기화() {
        final String newPassword = "newPassword";
        final String oldPassword = "oldPassword";

        password.changePassword(newPassword, oldPassword);
        password.changePassword(newPassword, oldPassword);
        password.changePassword(newPassword, oldPassword);
        password.changePassword(newPassword, passwordValue);

        assertTrue(password.isMatched(newPassword));
        assertEquals(password.getFailedCount(), 0);
        assertEquals(password.getTtl(), TTL);
    }
}