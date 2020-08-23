package com.youwiz.practices.dto;

import com.youwiz.practices.domain.Account;
import com.youwiz.practices.domain.Address;
import com.youwiz.practices.domain.Email;
import com.youwiz.practices.domain.Password;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class AccountDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUPReq {

        @Valid
        private Email email;

        @NotEmpty
        private String firstName;

        @NotEmpty
        private String lastName;

        private String password;

        @Valid
        private Address address;

        @Builder
        public SignUPReq(Email email, String firstName, String lastName, String password, Address address) {
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.address = address;
        }

        public Account toEntity() {
            return Account.builder()
                    .email(this.email)
                    .firstName(this.firstName)
                    .lastName(this.lastName)
                    .password(Password.builder().value(this.password).build())
                    .address(this.address)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class MyAccountReq {
        private Address address;

        @Builder
        public MyAccountReq(final Address address) {
            this.address = address;
        }
    }

    @Getter
    public static class Res {

        private Email email;
        private Password password;
        private String firstName;
        private String lastName;
        private Address address;

        public Res(Account account) {
            this.email = account.getEmail();
            this.firstName = account.getFirstName();
            this.lastName = account.getLastName();
            this.address = account.getAddress();
            this.password = account.getPassword();
        }
    }
}
