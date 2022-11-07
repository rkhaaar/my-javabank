package org.academiadecodigo.javabank.Services;

import org.academiadecodigo.javabank.model.Customer;

public class AuthService implements AbstractAuthService{
 //remember to store the acess log in somewhere, i was thinking at first here
    //but perhaps makes sense to store it on class commom to all services



    //this class is being initialized together with the LogIncontroller
    private int loggedCustomer;

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void setLoginCustomer(int id) {
        this.loggedCustomer = id;
    }

    @Override
    public boolean authenticate(Integer id) {
        return customerService.getCustomers().containsKey(id);
    }

    @Override
    public Customer getAccessingCustomer() {
        return customerService.get(loggedCustomer);
    }
}
