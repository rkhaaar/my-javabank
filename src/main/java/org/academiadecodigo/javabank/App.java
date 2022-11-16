package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.persistence.DbManager;
import org.academiadecodigo.javabank.services.jdbc.JdbcAccountService;
import org.academiadecodigo.javabank.services.jdbc.JdbcCustomerService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;

public class App {

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        DbManager dbManager = new DbManager("bank_db");
        AccountFactory accountFactory = new AccountFactory();
        JdbcAccountService accountService = new JdbcAccountService(dbManager, accountFactory);
        JdbcCustomerService customerService = new JdbcCustomerService(dbManager);
        customerService.setAccountService(accountService);


        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(accountService);
        bootstrap.setCustomerService(customerService);

        Controller controller = bootstrap.wireObjects();

        Customer ole = new Customer();
        ole.setFirstName("Ruben");
        ole.setLastName("Fernandes");
        ole.setEmail("madeira@wtf.com");
        ole.setPhone("965897456");
        ole.setId(1);
        customerService.add(ole);
        AccountFactory oi = new AccountFactory();
        Account haja =accountFactory.createAccount(AccountType.CHECKING);
        ole.addAccount(haja);
        accountService.add(haja);

        // start application
        controller.init();


    }
}
