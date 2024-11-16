package io.bank.domain;

import io.bank.exception.InsufficientFundsException;
import io.bank.exception.InvalidAmountException;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private long balance;
    private final List<Operation> operations = new ArrayList<>();

    public Account(long initBalance) {
        this.balance = initBalance;
    }

    public long getBalance() {
        return balance;
    }

    public synchronized void deposit(long amount) {
        validateAmount(amount);

        balance += amount;
        operations.add(new Operation(OperationEnum.DEPOSIT, amount, balance));

    }

    public synchronized void withdraw(long amount) {
        validateAmount(amount);
        validateSufficientFunds(amount);

        this.balance -= amount;
        operations.add(new Operation(OperationEnum.WITHDRAWAL, amount, balance));
    }

    private void validateSufficientFunds(long amount) {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
    }

    private static void validateAmount(long amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid withdrawal amount");
        }
    }

    public List<Operation> getOperations() {
        return List.copyOf(operations);
    }
}
