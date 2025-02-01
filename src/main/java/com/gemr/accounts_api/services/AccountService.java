package com.gemr.accounts_api.services;

import com.gemr.accounts_api.entities.Account;
import com.gemr.accounts_api.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> getById(Integer id) {
        System.out.println(accountRepository.findById(id).get());
        return accountRepository.findById(id);
    }
}
