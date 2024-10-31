package com.workintech.s19d2.controller;


import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.service.AccountService;
import com.workintech.s19d2.service.AccountServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;


    @GetMapping
    public List<Account> getAccount(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Account> getById(@PathVariable long id){
        return accountService.find(id);
    }

    @PostMapping
    public Account saveAccount(@RequestBody Account account){
        return accountService.save(account);
    }


    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable long id ,@RequestBody Account account){
        Optional<Account> foundAccount = accountService.find(id);
        if(foundAccount.isPresent()){
            return accountService.save(account);
        }
        return null;
    }


    @DeleteMapping("/{id}")
    public Account deleteAccount(@PathVariable long id ){
        return accountService.delete(id);
    }
}
