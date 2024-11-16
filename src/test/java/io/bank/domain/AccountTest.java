package io.bank.domain;

import io.bank.exception.InsufficientFundsException;
import io.bank.exception.InvalidAmountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {
    @Test
    void should_create_new_account_with_initial_balance() {
        Account a = new Account(100);
        assertEquals(100, a.getBalance());
    }

    @Test
    void should_make_positive_deposit() {
        Account a = new Account(0);
        long depositAmount = 50;
        a.deposit(depositAmount);
        assertEquals(50, a.getBalance());
    }

    @Test
    void should_throw_exception_for_negative_deposit() {
        Account a = new Account(0);
        long depositAmount = -50;

        assertThrows(InvalidAmountException.class, () -> a.deposit(depositAmount));
    }

    @Test
    void should_make_positive_withdraw() {
        Account a = new Account(100);
        long withdrawalAmount = 50;
        a.withdraw(withdrawalAmount);
        assertEquals(50, a.getBalance());
    }

    @Test
    void should_throw_exception_for_negative_withdraw() {
        Account a = new Account(100);
        long withdrawalAmount = -50;

        assertThrows(InvalidAmountException.class, () -> a.withdraw(withdrawalAmount));
    }

    @Test
    void should_throw_exception_for_insufficient_funds_withdraw() {
        Account a = new Account(100);
        long withdrawalAmount = 150;

        assertThrows(InsufficientFundsException.class, () -> a.withdraw(withdrawalAmount));
    }

    @Test
    void should_record_account_operations() {
        Account a = new Account(100);
        a.deposit(20);
        a.withdraw(40);

        List<Operation> operations = a.getOperations();
        assertEquals(2, operations.size());

        Assertions.assertEquals(OperationEnum.DEPOSIT, operations.get(0).getType());
        assertEquals(LocalDate.now(), operations.get(0).getDate());
        assertEquals(20, operations.get(0).getAmount());
        assertEquals(120, operations.get(0).getBalance());

        assertEquals(OperationEnum.WITHDRAWAL, operations.get(1).getType());
        assertEquals(LocalDate.now(), operations.get(1).getDate());
        assertEquals(40, operations.get(1).getAmount());
        assertEquals(80, operations.get(1).getBalance());
    }

}