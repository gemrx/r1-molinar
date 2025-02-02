package com.gemr.accounts_api.service;

import com.gemr.accounts_api.dto.AccountCreateRequest;
import com.gemr.accounts_api.dto.AccountUpdateRequest;
import com.gemr.accounts_api.entity.Account;
import com.gemr.accounts_api.exception.ResourceAlreadyExistException;
import com.gemr.accounts_api.exception.ResourceNotFoundException;
import com.gemr.accounts_api.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    // Constructor para inyectar el repositorio de cuentas
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Obtiene todas las cuentas
    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    // Obtiene una cuenta por su ID, lanza excepción si no se encuentra
    public Account getById(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new ResourceNotFoundException("Account with ID " + id + " not found");
        }
        return optionalAccount.get();
    }

    // Crea una nueva cuenta, lanza excepción si ya existe una cuenta con el mismo número
    public Account create(AccountCreateRequest accountCreateRequest) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountCreateRequest.accountNumber());
        if (optionalAccount.isPresent()) {
            throw new ResourceAlreadyExistException("An account with the account number " + accountCreateRequest.accountNumber() + " already exists.");
        }
        Account newAccount = new Account();
        newAccount.setClientName(accountCreateRequest.clientName());
        newAccount.setAccountNumber(accountCreateRequest.accountNumber());
        newAccount.setBalance(accountCreateRequest.balance());
        return accountRepository.save(newAccount);
    }

    // Actualiza el saldo de una cuenta, lanza excepción si no se encuentra la cuenta
    public Account update(Integer id, AccountUpdateRequest accountUpdateRequest) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new ResourceNotFoundException("Account with ID " + id + " not found");
        }
        Account account = optionalAccount.get();
        account.setBalance(accountUpdateRequest.newBalance());
        return accountRepository.save(account);
    }

    // Desactiva una cuenta (cambia su estado a 'inactivo') si existe, lanza excepción si no se encuentra
    public void delete(Integer id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new ResourceNotFoundException("Account with ID " + id + " not found");
        }
        Account account = optionalAccount.get();
        account.setStatus("inactive");
        accountRepository.save(account);
    }
}