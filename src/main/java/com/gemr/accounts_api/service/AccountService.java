package com.gemr.accounts_api.service;

import com.gemr.accounts_api.dto.AccountCreateRequest;
import com.gemr.accounts_api.dto.AccountUpdateRequest;
import com.gemr.accounts_api.entity.Account;
import com.gemr.accounts_api.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        return accountRepository.findById(id);
    }

    public Account create(AccountCreateRequest accountCreateRequest) {
        Account newAccount = new Account();
        newAccount.setClientName(accountCreateRequest.clientName());
        newAccount.setAccountNumber(accountCreateRequest.accountNumber());
        newAccount.setBalance(accountCreateRequest.balance());
        return accountRepository.save(newAccount);
    }

    public Account update(Integer id, AccountUpdateRequest accountUpdateRequest) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found with ID: " + id);
        }
        Account account = optionalAccount.get();
        account.setBalance(accountUpdateRequest.newBalance());
        return accountRepository.save(account);
    }
}
