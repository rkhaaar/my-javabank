package org.academiadecodigo.javabank.Services;

import org.academiadecodigo.javabank.model.Customer;

public interface AbstractAuthService {
    boolean authenticate(Integer id);
    Customer getAccessingCustomer();
}
