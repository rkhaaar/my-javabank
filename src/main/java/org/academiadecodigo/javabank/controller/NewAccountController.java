package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.Services.AuthService;
import org.academiadecodigo.javabank.Services.CustomerService;
import org.academiadecodigo.javabank.Services.managers.AccountService;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.view.NewAccountView;

/**
 * The {@link NewAccountView} controller
 */
public class NewAccountController extends AbstractController {

    private AuthService authService;
    private Integer newAccountId;
    private AccountService accountService;



    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Gets the new account id
     *
     * @return the new account id
     */
    public Integer getNewAccountId() {
        return newAccountId;
    }

    /**
     * Creates a new {@link Account}
     *
     * @see Controller#init()
     * @see AccountService#addAccount(AccountType)
     */
    @Override
    public void init() {

        newAccountId = createAccount();
        super.init();
    }

    private int createAccount() {

        Account newAccount = accountService.addAccount(AccountType.CHECKING);
        authService.getAccessingCustomer().addAccount(newAccount);

        return newAccount.getId();
    }

}
