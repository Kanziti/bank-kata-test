package io.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.bank.domain.Account;
import io.bank.service.AccountService;
import io.bank.service.AccountServiceImpl;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController() {
        this.accountService = AccountServiceImpl.getINSTANCE();
    }

    @PutMapping("/deposit")
    public ResponseEntity<Account> deposit(@RequestParam long amount) {
        accountService.deposit(amount);
        return ResponseEntity.ok(accountService.getAccount());
    }

    @PutMapping("/withdraw")
    public ResponseEntity<Account> withdraw(@RequestParam long amount) {
        accountService.withdraw(amount);
        return ResponseEntity.ok(accountService.getAccount());
    }

    @GetMapping("/statement")
    public ResponseEntity<String> getAccountStatement() {
        return ResponseEntity.ok(accountService.getAccountStatement());
    }
}
