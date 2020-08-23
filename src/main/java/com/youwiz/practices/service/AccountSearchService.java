package com.youwiz.practices.service;

import com.querydsl.jpa.impl.JPAQuery;
import com.youwiz.practices.domain.Account;
import com.youwiz.practices.domain.QAccount;
import com.youwiz.practices.dto.AccountSearchType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AccountSearchService extends QuerydslRepositorySupport {

    public AccountSearchService() {
        super(Account.class);
    }

    public Page<Account> search(final AccountSearchType type, final String value, final Pageable pageable) {
        final QAccount account = QAccount.account;
        final JPAQuery<Account> query;

        switch (type) {
            case EMAIL:
                query = (JPAQuery<Account>) from(account)
                        .where(account.email.value.likeIgnoreCase(value + "%"));
                break;
            case NAME:
                query = (JPAQuery<Account>) from(account)
                        .where(account.firstName.likeIgnoreCase(value + "%")
                                .or(account.lastName.likeIgnoreCase(value + "%")));
                break;
            case ALL:
                query = (JPAQuery<Account>) from(account).fetchAll();
                break;
            default:
                throw new IllegalArgumentException();
        }
        final List<Account> accounts = getQuerydsl().applyPagination(pageable, query).fetch();
        return new PageImpl<>(accounts, pageable, query.fetchCount());
    }
}
