package org.academiadecodigo.javabank.test;

import org.academiadecodigo.javabank.Services.AuthService;
import org.academiadecodigo.javabank.Services.CustomerService;
import org.academiadecodigo.javabank.Services.managers.AccountService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

public class BankTest {

    public boolean test() {

        AccountService accountService = new AccountService();
        CustomerService customerService = new CustomerService();
        AuthService authService =new AuthService();
        customerService.setAccountService(accountService);
        authService.setCustomerService(customerService);


        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        customerService.add(c1);
        customerService.add(c2);

        Account a1 = accountService.addAccount(AccountType.CHECKING);
        Account a2 = accountService.addAccount(AccountType.CHECKING);

        c1.addAccount(a1);
        c2.addAccount(a2);

        accountService.deposit(a1.getId(), 100);
        accountService.deposit(a2.getId(), 100);

        // bank balance should equal sum of all customers balance
        if (customerService.getBalance(authService.getAccessingCustomer().getId()) != 200) {
            return false;
        }

        return true;
    }
}
