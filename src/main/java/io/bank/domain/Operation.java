package io.bank.domain;

import java.time.LocalDate;

public class Operation {
    private final OperationEnum type;
    private final LocalDate date;
    private final double amount;
    private final double balance;

    public Operation(OperationEnum type, double amount, double balance) {
        this.type = type;
        this.date = LocalDate.now();
        this.amount = amount;
        this.balance = balance;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalance() {
        return balance;
    }

    public OperationEnum getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }
}
