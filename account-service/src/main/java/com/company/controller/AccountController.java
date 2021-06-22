package com.company.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

public class AccountController {
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public Principal getAccount(Principal principal) {
        System.out.println("CALLED account");
        return principal;
    }
}
