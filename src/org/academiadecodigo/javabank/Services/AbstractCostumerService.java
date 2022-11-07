package org.academiadecodigo.javabank.Services;

import org.academiadecodigo.javabank.model.Customer;

import java.util.Set;

public interface AbstractCostumerService {
    //
  Customer get(Integer id);
  //this method should return on it's implementation the accounts that belong to the id passed
  // as an argument , this information is present and stored on the costumer class in a Map obtained externally by
  // the getAccountIds method that returns a Set;
  Set<Integer> listGetCustomerAccounts(Integer id);
  //this method implementation should return a double format Integer of the balance
    double getBalance(int costumerId);
    //this method implementation should return a list of the customers id
    // ,on this case as a set
    Set<Integer> getCustomersIds();
    //this method implementation should originate an addition of a new customer;taking as an argument a customer;
    void add(Customer customer);


}
