package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.Services.AuthService;
import org.academiadecodigo.javabank.Services.CustomerService;
import org.academiadecodigo.javabank.Services.managers.AccountService;
import org.academiadecodigo.javabank.controller.LoginController;

public class App {

    private CustomerService customerService;

    public static void main(String[] args) {

        App app = new App();
        app.bootStrap();
    }

    private void bootStrap() {

        Bootstrap bootstrap = new Bootstrap();
        customerService = bootstrap.generateTestData();

        LoginController loginController = bootstrap.wireObjects(customerService);

        // start application
        loginController.init();
    }
}
