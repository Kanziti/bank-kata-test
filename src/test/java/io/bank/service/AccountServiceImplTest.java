package io.bank.service;

import io.bank.exception.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountServiceImplTest {

    private AccountServiceImpl accountServiceImpl;
    long initialBalance;

    @BeforeEach
    void setup() {
        accountServiceImpl = AccountServiceImpl.getINSTANCE();
        initialBalance = accountServiceImpl.getAccount().getBalance();
    }

    @Test
    void should_deposit() {
        long amount = 100;
        accountServiceImpl.deposit(amount);
        assertEquals(initialBalance + 100, accountServiceImpl.getAccount().getBalance());
    }

    @Test
    void withdraw() {
        long amount = 50;
        accountServiceImpl.withdraw(amount);
        assertEquals(initialBalance - 50, accountServiceImpl.getAccount().getBalance());
    }

    @Test
    void should_throws_invalid_amount_exception_for_insufficient_funds() {
        assertThrows(InsufficientFundsException.class, () -> accountServiceImpl.withdraw(initialBalance + 100));
        assertEquals(initialBalance, accountServiceImpl.getAccount().getBalance());
    }
}