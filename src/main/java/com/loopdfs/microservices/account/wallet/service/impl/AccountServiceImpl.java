package com.loopdfs.microservices.account.wallet.service.impl;

import com.loopdfs.microservices.account.wallet.entities.Account;
import com.loopdfs.microservices.account.wallet.exception.CustomException;
import com.loopdfs.microservices.account.wallet.repositories.AccountRepository;
import com.loopdfs.microservices.account.wallet.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.loopdfs.microservices.account.wallet.utils.Constants.*;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long accountId) {
        Optional<Account> existingAccount = accountRepository.findById(accountId);
        if (!existingAccount.isPresent()) {
            throw new CustomException(NOT_FOUND, NOT_FOUND_MESSAGE);
        }
        return existingAccount;
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(Long accountId, Account updatedAccount) {
        Optional<Account> existingAccountOptional = accountRepository.findById(accountId);

        if (!existingAccountOptional.isPresent()) {
            throw new CustomException(NOT_FOUND, NOT_FOUND_MESSAGE);
        } else {
            Account existingAccount = existingAccountOptional.get();
            existingAccount.setIban(updatedAccount.getIban());
            existingAccount.setBicSwift(updatedAccount.getBicSwift());
            return accountRepository.save(existingAccount);
        }
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
