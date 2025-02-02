package com.gemr.accounts_api.controller;

import com.gemr.accounts_api.dto.AccountCreateRequest;
import com.gemr.accounts_api.dto.AccountUpdateRequest;
import com.gemr.accounts_api.entity.Account;
import com.gemr.accounts_api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return accountService.getById(id);
    }

    @PostMapping
    public Account create(@RequestBody @Valid AccountCreateRequest account) {
        return accountService.create(account);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable Integer id, @RequestBody @Valid AccountUpdateRequest accountUpdateRequest) {
        return accountService.update(id, accountUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }
}
