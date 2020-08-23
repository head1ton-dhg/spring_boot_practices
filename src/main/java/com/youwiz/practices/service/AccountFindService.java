package com.youwiz.practices.service;

import com.youwiz.practices.domain.Account;
import com.youwiz.practices.domain.Email;
import com.youwiz.practices.exception.AccountNotFoundException;
import com.youwiz.practices.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountFindService {

    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public Account findById(long id) {
        return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Account findByEmail(final Email email) {
        final Account account = accountRepository.findByEmail(email);
        if (account == null) throw new AccountNotFoundException(email);
        return account;
    }

    @Transactional(readOnly = true)
    public boolean isExistedEmail(Email email) {
        return accountRepository.existsByEmail(email);
    }
}
