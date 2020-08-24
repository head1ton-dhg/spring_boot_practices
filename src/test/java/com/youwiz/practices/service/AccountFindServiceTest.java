package com.youwiz.practices.service;

import com.youwiz.practices.domain.Account;
import com.youwiz.practices.domain.Email;
import com.youwiz.practices.exception.AccountNotFoundException;
import com.youwiz.practices.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class AccountFindServiceTest {

    @InjectMocks
    private AccountFindService accountFindService;

    @Mock
    private AccountRepository accountRepository;
    private Account account;
    private Email email;

    @BeforeEach
    public void setUp() throws Exception {
        email = Email.of("test@gmail.com");
        account = Account.builder()
                .email(email)
                .build();
    }

    @Test
    public void findById_존재하는경우() {
        //given
        given(accountRepository.findById(any())).willReturn(Optional.of(account));

        //when
        final Account findAccount = accountFindService.findById(1L);

        //then
        assertEquals(findAccount.getEmail().getValue(), account.getEmail().getValue());
    }

    @Test
    public void findById_없는경우() {
        assertThrows(AccountNotFoundException.class, () -> {
            //given
            given(accountRepository.findById(any())).willReturn(Optional.empty());

            //when
            accountFindService.findById(1L);

            //then
        });
    }

    @Test
    public void findByEmail_없는경우() {
        assertThrows(AccountNotFoundException.class, () -> {
            //given
            given(accountRepository.findByEmail(any())).willReturn(null);

            //when
            accountFindService.findByEmail(email);

            //then
        });
    }

    @Test
    public void isExistedEmail_이메일있으면_true() {
        //given
        given(accountRepository.existsByEmail(email)).willReturn(true);

        //when
        final boolean existedEmail = accountFindService.isExistedEmail(email);

        //then
        assertTrue(existedEmail);
    }

    @Test
    public void isExistedEmail_이메일없으면_false() {
        //given
        given(accountRepository.existsByEmail(email)).willReturn(false);

        //when
        final boolean existedEmail = accountFindService.isExistedEmail(email);

        //then
        assertFalse(existedEmail);
    }

}