package io.bank.service;

import io.bank.domain.Account;
import io.bank.domain.Operation;

public class AccountServiceImpl implements AccountService {

    private static final AccountServiceImpl INSTANCE = new AccountServiceImpl();
    private static final long INITIAL_BALANCE = 0;
    private final Account account;

    private AccountServiceImpl() {
        account = new Account(INITIAL_BALANCE);
    }

    public static AccountServiceImpl getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void deposit(long amount) {
        account.deposit(amount);
    }

    @Override
    public void withdraw(long amount) {
        account.withdraw(amount);
    }

    @Override
    public Account getAccount() {
        return account;
    }

    @Override
    public String getAccountStatement() {
        StringBuilder statement = new StringBuilder();
        for (Operation operation : account.getOperations()) {
            statement.append(operation.getDate()).append(": ")
                    .append(operation.getType()).append(" ")
                    .append("Amount: ").append(operation.getAmount()).append(" ")
                    .append("Balance: ").append(operation.getBalance()).append("\n");
        }
        return statement.toString();
    }
}
