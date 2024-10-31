package com.workintech.s19d2.service;


import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> find(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(long id, Account account) {
        if(id >=0){
           Optional<Account> acc = accountRepository.findById(id);
           if (acc.isPresent()){
              return  accountRepository.save(account);
           }
        }
        return null;
    }

    @Override
    public Account delete(long id) {
        Optional<Account> deletedAccount = accountRepository.findById(id);
      if(deletedAccount.isPresent()){
          accountRepository.deleteById(id);
      }
      return null;
    }
}
