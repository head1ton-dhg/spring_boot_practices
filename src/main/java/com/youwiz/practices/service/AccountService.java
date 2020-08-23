package com.youwiz.practices.service;

import com.youwiz.practices.domain.Account;
import com.youwiz.practices.domain.Email;
import com.youwiz.practices.dto.AccountDto;
import com.youwiz.practices.exception.AccountNotFoundException;
import com.youwiz.practices.exception.EmailDupliationException;
import com.youwiz.practices.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Account findById(long id) {
        final Optional<Account> account = accountRepository.findById(id);
        account.orElseThrow(() -> new AccountNotFoundException(id));
        return account.get();
    }

    @Transactional(readOnly = true)
    public Account findByEmail(final Email email) {
        final Account account = accountRepository.findByEmail(email);
        if (account == null) throw new AccountNotFoundException(email);
        return account;
    }

    @Transactional(readOnly = true)
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public boolean isExistedEmail(Email email) {
        return accountRepository.findByEmail(email) != null;
    }

    public Account updateMyAccount(long id, AccountDto.MyAccountReq dto) {
        final Account account = findById(id);
        account.updateMyAccount(dto);
        return account;
    }

    public Account create(AccountDto.SignUPReq dto) {
        if (isExistedEmail(dto.getEmail()))
            throw new EmailDupliationException(dto.getEmail());
        return accountRepository.save(dto.toEntity());
    }
}
