package com.workintech.s19d2.service;


import com.workintech.s19d2.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> findAll();

    Optional<Account> find(long id);

    Account save(Account account);

    Account update(long id , Account account);

    Account delete(long id);
}
