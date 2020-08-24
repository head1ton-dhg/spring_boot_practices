package com.youwiz.practices.service;

import com.youwiz.practices.domain.Account;
import com.youwiz.practices.domain.Address;
import com.youwiz.practices.domain.Email;
import com.youwiz.practices.dto.AccountDto;
import com.youwiz.practices.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void create_회원가입_성공() {
        //given
        final AccountDto.SignUPReq dto = buildSignUpReq();
        given(accountRepository.save(any(Account.class))).willReturn(dto.toEntity());

        //when
        final Account account = accountService.create(dto);

        //then
        verify(accountRepository, atLeastOnce()).save(any(Account.class));
        assertThatEqual(dto, account);

        account.getId();
        account.getCreatedAt();
        account.getUpdatedAt();
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
                .email(buildEmail("test@gmail.com"))
                .firstName("두산")
                .lastName("백")
                .password("password111")
                .build();
    }

    private Address buildAddress(String address1, String address2, String zip) {
        return Address.builder()
                .address1(address1)
                .address2(address2)
                .zip(zip)
                .build();
    }

    private Email buildEmail(final String email) {
        return Email.builder().value(email).build();
    }
}