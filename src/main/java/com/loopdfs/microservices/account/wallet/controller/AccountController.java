package com.loopdfs.microservices.account.wallet.controller;

import com.loopdfs.microservices.account.wallet.entities.Account;
import com.loopdfs.microservices.account.wallet.exception.CustomException;
import com.loopdfs.microservices.account.wallet.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Operation(summary = "Get list of all accounts")
    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @Operation(summary = "Get accounts by account id")
    @GetMapping("accounts/{accountId}")
    public Optional<Account> getAccountById(@PathVariable Long accountId) {
        return accountService.getAccountById(accountId);
    }
    @Operation(summary = "create a new account")
    @PostMapping("/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
    @Operation(summary = "update an account")
    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long accountId,
                                                 @RequestBody Account account) {
        try {
            Account updated = accountService.updateAccount(accountId, account);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "delete an account")
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
