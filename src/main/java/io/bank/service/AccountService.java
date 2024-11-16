package io.bank.service;

import io.bank.domain.Account;

public interface AccountService {
    void deposit(long amount);

    void withdraw(long amount);

    Account getAccount();

    String getAccountStatement();
}
