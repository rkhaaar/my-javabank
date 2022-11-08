package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.Services.AuthService;
import org.academiadecodigo.javabank.Services.CustomerService;
import org.academiadecodigo.javabank.view.LoginView;
import org.academiadecodigo.javabank.view.Messages;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController {
//bank disapears and the service interface comes to action!!
    private Controller nextController;


    //to disappear
    //container for the authentication;
    private AuthService authService;



    //constructor that initiates the authentication service with the log in controller
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Sets the next controller
     *
     * @param nextController the next controller to be set
     */


    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }

    /**
     * Sets the bank
     *
     * @param customerService the bank to be set
     */


    /**
     * Identifies the logged in customer
     *
     * @param id the customer id
     */
    public void onLogin(int id) {
        //customerService.setLoginCustomer(id);

           authService.setLoginCustomer(id);
           nextController.init();
    }



}
