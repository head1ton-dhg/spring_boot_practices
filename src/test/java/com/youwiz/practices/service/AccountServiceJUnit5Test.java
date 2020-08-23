package com.youwiz.practices.service;

import com.youwiz.practices.domain.Account;
import com.youwiz.practices.domain.Address;
import com.youwiz.practices.domain.Email;
import com.youwiz.practices.dto.AccountDto;
import com.youwiz.practices.repository.AccountRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountServiceJUnit5Test {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    @DisplayName("findById_존재하는 경우_회원리턴")
    public void findBy_not_existed_test() {
        //given
        final AccountDto.SignUPReq dto = buildSignUpReq();
        given(accountRepository.findById(anyLong())).willReturn(Optional.of(dto.toEntity()));

        //when
        final Account account = accountService.findById(anyLong());

        //then
        verify(accountRepository, atLeastOnce()).findById(anyLong());
        assertThatEqual(dto, account);
    }

    private void assertThatEqual(AccountDto.SignUPReq signUPReq, Account account) {
        assertEquals(signUPReq.getAddress().getAddress1(), account.getAddress().getAddress1());
        assertEquals(signUPReq.getAddress().getAddress2(), account.getAddress().getAddress2());
        assertEquals(signUPReq.getAddress().getZip(), account.getAddress().getZip());
        assertEquals(signUPReq.getEmail(), account.getEmail());
        assertEquals(signUPReq.getFirstName(), account.getFirstName());
        assertEquals(signUPReq.getLastName(), account.getLastName());
    }

    private AccountDto.SignUPReq buildSignUpReq() {
        return AccountDto.SignUPReq.builder()
                .address(buildAddress("서울", "성동구", "052-2344"))
                .email(buildEmail("email"))
                .firstName("두산")
                .lastName("백")
                .password("password111")
                .build();
    }

    private Email buildEmail(final String email) {
        return Email.builder().value(email).build();
    }

    private Address buildAddress(String address1, String address2, String zip) {
        return Address.builder()
                .address1(address1)
                .address2(address2)
                .zip(zip)
                .build();
    }

}