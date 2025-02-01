package com.gemr.accounts_api.controller;

import com.gemr.accounts_api.dto.AccountCreateRequest;
import com.gemr.accounts_api.dto.AccountUpdateRequest;
import com.gemr.accounts_api.entity.Account;
import com.gemr.accounts_api.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {
    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Integer id) {
        Optional<Account> optionalAccount = accountService.getById(id);
        if (optionalAccount.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with ID: " + id);
        }
        return optionalAccount.get();
    }

    @PostMapping
    public Account create(@RequestBody AccountCreateRequest account) {
        return accountService.create(account);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable Integer id, @RequestBody AccountUpdateRequest accountUpdateRequest) {
        return accountService.update(id, accountUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }
}
