package com.youwiz.practices.repository;

import com.querydsl.core.types.Predicate;
import com.youwiz.practices.domain.Account;
import com.youwiz.practices.domain.Email;
import com.youwiz.practices.domain.QAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    private final QAccount qAccount = QAccount.account;

    @Test
    public void findByEmail_test() {
        final String email = "test@gmail.com";
        final Account account = accountRepository.findByEmail(Email.of(email));
        assertEquals(account.getEmail().getValue(), email);
    }

    @Test
    public void findById_test() {
        final Optional<Account> optionalAccount = accountRepository.findById(1L);
        final Account account = optionalAccount.get();
        assertEquals(account.getId(), 1L);
    }

    @Test
    public void isExistedEmail_test() {
        final String email = "test@gmail.com";
        final boolean existsByEmail = accountRepository.existsByEmail(Email.of(email));
        assertTrue(existsByEmail);
    }

    @Test
    public void findRecentlyRegistered_test() {
        final List<Account> accounts = accountRepository.findRecentlyRegistered(10);
        assertTrue(accounts.size() < 11);
    }

    @Test
    public void predicate_test_001() {
        //given
        final Predicate predicate = qAccount.email.eq(Email.of("test@gmail.com"));

        //when
        final boolean exists = accountRepository.exists(predicate);

        //then
        assertTrue(exists);
    }

    @Test
    public void predicate_test_002() {
        //given
        final Predicate predicate = qAccount.firstName.eq("test");

        //when
        final boolean exists = accountRepository.exists(predicate);

        //then
        assertFalse(exists);
    }

    @Test
    public void predicate_test_003() {
        //given
        final Predicate predicate = qAccount.email.value.like("test%");

        //when
        final long count = accountRepository.count(predicate);

        //then
        assertTrue(count > 1);
    }
}