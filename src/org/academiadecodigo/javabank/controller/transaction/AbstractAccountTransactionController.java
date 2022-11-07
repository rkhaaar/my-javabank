package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.Services.AuthService;
import org.academiadecodigo.javabank.Services.CustomerService;
import org.academiadecodigo.javabank.Services.managers.AccountService;
import org.academiadecodigo.javabank.controller.AbstractController;

/**
 * A generic account transaction controller to be used as a base for concrete transaction controller implementations
 * @see AbstractController
 * @see AccountTransactionController
 */
public abstract class AbstractAccountTransactionController extends AbstractController implements AccountTransactionController {


protected AccountService accountService;

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
