package com.gemr.accounts_api.repository;

import com.gemr.accounts_api.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    // Encuentra una cuenta por su número de cuenta
    Optional<Account> findByAccountNumber(String accountNumber);
}

