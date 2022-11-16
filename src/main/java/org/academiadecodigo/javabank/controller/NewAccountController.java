package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.jdbc.JdbcAccountService;
import org.academiadecodigo.javabank.view.NewAccountView;

/**
 * The {@link NewAccountView} controller
 */
public class NewAccountController extends AbstractController {

    private Integer newAccountId;
    private AccountService accountService;
    private AccountFactory accountFactory;
    private JdbcAccountService jdbcAccountService;

    /**
     * Gets the new account id
     *
     * @return the new account id
     */
    public Integer getNewAccountId() {
        return newAccountId;
    }

    /**
     * Sets the account service
     *
     * @param accountService the account service to set
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Sets the account factory
     *
     * @param accountFactory
     */
    public void setAccountFactory(AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    /**
     * Creates a new {@link Account}
     *
     * @see Controller#init()
     * @see AccountFactory#createAccount(AccountType)
     */
    @Override
    public void init() {

        Account newac = new Account() {
            @Override
            public double getBalance() {
                return 0;
            }

            @Override
            public AccountType getAccountType() {
                return AccountType.CHECKING;
            }

            @Override
            public void credit(double amount) {

            }

            @Override
            public void debit(double amount) {

            }

            @Override
            public boolean canCredit(double amount) {
                return false;
            }

            @Override
            public boolean canDebit(double amount) {
                return false;
            }

            @Override
            public boolean canWithdraw() {
                return false;
            }

            @Override
            public Integer getCustomerId() {
                return null;
            }

            @Override
            public void setCustomerId(Integer id) {

            }

            @Override
            public Integer getId() {
                return null;
            }

            @Override
            public void setId(Integer id) {

            }
        };
        Customer accessingCustomer = authService.getAccessingCustomer();
        accountService.add(newac);
        jdbcAccountService.add(newac);

    }

    private void createAccount() {


        Account newAccount = accountFactory.createAccount(AccountType.CHECKING);
        Customer accessingCustomer = authService.getAccessingCustomer();

        accessingCustomer.addAccount(newAccount);
        accountService.add(newAccount);


    }
}
