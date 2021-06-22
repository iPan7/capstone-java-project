package com.company.controller;

import com.company.model.Account;
import com.company.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@RequestBody Account newAccount) {
        Account savedAccount = accountRepository.save(newAccount);
        return savedAccount;
    }

    @GetMapping("/account")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> getAllAccounts(@RequestParam(required = false) String make, String color) {
        return accountRepository.findAll();
    }

    @GetMapping("/account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable int id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get();
        }
        throw new RuntimeException("No account with id" + id);
    }
}
