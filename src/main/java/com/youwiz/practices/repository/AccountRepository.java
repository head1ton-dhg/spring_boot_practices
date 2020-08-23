package com.youwiz.practices.repository;

import com.youwiz.practices.domain.Account;
import com.youwiz.practices.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountSupportRepository, QuerydslPredicateExecutor<Account> {

    Account findByEmail(Email email);

    boolean existsByEmail(Email email);
}
