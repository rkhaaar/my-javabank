package org.academiadecodigo.javabank.Services;
import org.academiadecodigo.javabank.Services.managers.AccountService;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import java.util.HashMap;
import java.util.Set;

/**
 * The bank entity
 */
public class CustomerService implements AbstractCostumerService {

    private AccountService accountService;
    private HashMap<Integer, Customer> customers;

    private AuthService loggedCustomer;

    /**
     * Creates a new instance of Bank
     */
    public CustomerService() {
        this.customers = new HashMap<>();
    }

    public HashMap<Integer, Customer> getCustomers() {
        return customers;
    }

    /**
     * Gets the total balance of the bank
     *
     * @return the bank total balance
     */
    @Override
    public double getBalance(int customerId) {

        double balance = 0;

        for (Account customer : customers.get(customerId).getAccounts()) {
            balance += customer.getBalance();
        }

        return balance;
    }

    /**
     * Gets the account manager
     *
     * @return the account manager
     */
    public AccountService getAccountService() {
        return accountService;
    }

    /**
     * Sets the account manager
     *
     * @param accountService the account manager to set
     */
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }


//method that returns a list of accounts
    @Override
    public Set<Integer> listGetCustomerAccounts(Integer id) {
       return  customers.get(id).getAccountIds();

    }



    /**
     * Gets the ids of the bank customers
     *
     * @return customer ids
     */

    @Override
    public Set<Integer> getCustomersIds() {
        return customers.keySet();
    }


    /**
     * Gets the logged in customer
     *
     * @return the customer
     */
    @Override
    public Customer get(Integer id) {
        return customers.get(id);
    }



    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     */
    @Override
    public void add(Customer customer) {
        customers.put(customer.getId(), customer);

    }

}
