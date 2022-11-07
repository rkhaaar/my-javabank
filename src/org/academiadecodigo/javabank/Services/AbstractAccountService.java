package org.academiadecodigo.javabank.Services;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

public interface AbstractAccountService {
    Account addAccount(AccountType accountType);
    void deposit(int id,double amount);
    void withdraw(int id,double amount);
    void transfer(int srcId,int dstId,double amount);
}
