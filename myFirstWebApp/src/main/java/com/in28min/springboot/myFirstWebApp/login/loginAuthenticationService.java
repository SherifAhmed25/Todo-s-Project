package com.in28min.springboot.myFirstWebApp.login;

import org.springframework.stereotype.Service;

@Service
public class loginAuthenticationService {
    public boolean authenticate(String username, String password) {

        boolean isValidUserName = username.equalsIgnoreCase("admin");
        boolean isValidPassword = password.equalsIgnoreCase("admin");
        return isValidUserName && isValidPassword;

    }
}
