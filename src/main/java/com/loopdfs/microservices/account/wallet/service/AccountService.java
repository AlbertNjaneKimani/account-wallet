package com.loopdfs.microservices.account.wallet.service;

import com.loopdfs.microservices.account.wallet.entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllAccounts();

    Optional<Account> getAccountById(Long accountId);

    Account createAccount(Account account);

    Account updateAccount(Long accountId, Account updatedAccount);

    void deleteAccount(Long accountId);
}
