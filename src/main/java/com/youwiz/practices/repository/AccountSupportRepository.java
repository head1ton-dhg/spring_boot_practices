package com.youwiz.practices.repository;

import com.youwiz.practices.domain.Account;

import java.util.List;

public interface AccountSupportRepository {

    List<Account> findRecentlyRegistered(int limit);

}
