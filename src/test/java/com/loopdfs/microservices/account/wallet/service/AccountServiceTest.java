package com.loopdfs.microservices.account.wallet.service;

import com.loopdfs.microservices.account.wallet.entities.Account;
import com.loopdfs.microservices.account.wallet.exception.CustomException;
import com.loopdfs.microservices.account.wallet.repositories.AccountRepository;
import com.loopdfs.microservices.account.wallet.service.AccountService;
import com.loopdfs.microservices.account.wallet.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountService accountService = new AccountServiceImpl(accountRepository);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAccounts() {
        when(accountRepository.findAll()).thenReturn(Arrays.asList(new Account(), new Account()));
        List<Account> accounts = accountService.getAllAccounts();
        Assertions.assertTrue(accounts.size() > 0);
        verify(accountRepository, times(1)).findAll();
    }

    @Test
    void getAccountById() {
        Long accountId = 2L;
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(new Account()));
        Optional<Account> account = accountService.getAccountById(accountId);
        System.out.println(account.isPresent());
        Assertions.assertTrue(account.isPresent());
        verify(accountRepository, times(1)).findById(accountId);
    }

    @Test
    void getAccountByIdNotFound() {
        Long accountId = 0L;
        when(accountRepository.findById(accountId)).thenReturn(Optional.empty());
        CustomException exception = assertThrows(CustomException.class, () -> accountService.getAccountById(accountId));
        Assertions.assertEquals("404", exception.getErrorCode());
        Assertions.assertEquals("Record not found", exception.getMessage());

        verify(accountRepository, times(1)).findById(accountId);
    }

    @Test
    void createAccount() {
        Account account = new Account();
        account.setIban("664dud");
        account.setBicSwift("ttrttr");
        when(accountRepository.save(account)).thenReturn(account);
        Account createdAccount = accountService.createAccount(account);
        verify(accountRepository, times(1)).save(createdAccount);
        Assertions.assertEquals("664dud", createdAccount.getIban());
    }

    @Test
    void updateCard() {
        Long accountId = 1L;
        Account account = new Account();
        account.setIban("UpdatedIban");
        account.setBicSwift("UpdatedBicSwift");
        when(accountRepository.findById(accountId)).thenReturn(Optional.of(new Account()));
        when(accountRepository.save(any(Account.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Account updatedAccount = accountService.updateAccount(accountId, account);

        verify(accountRepository, times(1)).findById(accountId);
        verify(accountRepository, times(1)).save(updatedAccount);
        Assertions.assertNotNull(updatedAccount);
    }

    @Test
    void deleteAccount() {
        Long accountId = 1L;
        accountService.deleteAccount(accountId);
        verify(accountRepository, times(1)).deleteById(accountId);
    }
}
