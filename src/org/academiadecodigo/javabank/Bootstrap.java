package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.Services.AuthService;
import org.academiadecodigo.javabank.controller.*;
import org.academiadecodigo.javabank.controller.transaction.DepositController;
import org.academiadecodigo.javabank.controller.transaction.WithdrawalController;
import org.academiadecodigo.javabank.Services.managers.AccountService;
import org.academiadecodigo.javabank.Services.CustomerService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.view.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for wiring the objects dependencies
 */
public class Bootstrap {

    /**
     * Creates a {@code Bank} and populates it with data
     *
     * @return the bank
     */
    public CustomerService generateTestData() {

        CustomerService customerService = new CustomerService();



        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        Customer c3 = new Customer(3, "Bruno");
        customerService.add(c1);
        customerService.add(c2);
        customerService.add(c3);

        return customerService;
    }

    /**
     * Wires the necessary object dependencies
     *
     * @param customerService the bank to wire
     * @return the login controller
     */
    public LoginController wireObjects(CustomerService customerService) {

        AccountService accountService = new AccountService();
        AuthService authService= new AuthService();
        customerService.setAccountService(accountService);
        authService.setCustomerService(customerService);



        // attach all input to standard i/o
        Prompt prompt = new Prompt(System.in, System.out);

        // wire login controller and view
        LoginController loginController = new LoginController(authService);
        //Authentication Service is being initialized on the constructor of LogIn controller
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        loginView.setCustomerService(customerService);
        loginView.setLoginController(loginController);
        loginView.setAuthService(authService);
        loginView.setPrompt(prompt);

        // wire main controller and view
        MainController mainController = new MainController();
        MainView mainView = new MainView();
        mainView.setAuthService(authService);
        mainView.setCustomerService(customerService);
        mainView.setPrompt(prompt);
        mainView.setMainController(mainController);
        mainController.setView(mainView);
        loginController.setNextController(mainController);

        // wire balance controller and view
        BalanceController balanceController = new BalanceController();
        BalanceView balanceView = new BalanceView();
        balanceController.setView(balanceView);
        balanceView.setAuthService(authService);


        // wire new account controller and view
        NewAccountView newAccountView = new NewAccountView();
        NewAccountController newAccountController = new NewAccountController();
        newAccountController.setAuthService(authService);
        newAccountController.setAccountService(accountService);
        newAccountController.setView(newAccountView);
        newAccountView.setNewAccountController(newAccountController);

        // wire account transactions controllers and views
        DepositController depositController = new DepositController();
        WithdrawalController withdrawalController = new WithdrawalController();
        AccountTransactionView depositView = new AccountTransactionView();
        AccountTransactionView withdrawView = new AccountTransactionView();
        depositController.setView(depositView);
        withdrawalController.setAccountService(accountService);
        withdrawView.setAuthService(authService);
        depositView.setAuthService(authService);
        withdrawalController.setView(withdrawView);
        depositView.setCustomerService(customerService);
        depositController.setAccountService(accountService);
        depositView.setPrompt(prompt);
        depositView.setTransactionController(depositController);
        withdrawView.setCustomerService(customerService);
        withdrawView.setPrompt(prompt);
        withdrawView.setTransactionController(withdrawalController);

        // setup the controller map
        Map<Integer, Controller> controllerMap = new HashMap<>();
        controllerMap.put(UserOptions.GET_BALANCE.getOption(), balanceController);
        controllerMap.put(UserOptions.OPEN_ACCOUNT.getOption(), newAccountController);
        controllerMap.put(UserOptions.DEPOSIT.getOption(), depositController);
        controllerMap.put(UserOptions.WITHDRAW.getOption(), withdrawalController);

        mainController.setControllerMap(controllerMap);

        return loginController;
    }
}
