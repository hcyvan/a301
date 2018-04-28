package com.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class apiController {
    @Autowired

    private AccountRepository accountRepository;

    @PostMapping("/api/register")
    public String register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        Account new_account = new Account();
        new_account.setEmail(email);
        new_account.setName(name);
        new_account.setPassword(password);
        accountRepository.save(new_account);
        return "Saved";
    }

    @PostMapping("/api/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        Account account = accountRepository.getAccountByEmail(email);
        if (account == null) {
            return "User not exist";
        }
        String account_password = account.getPassword();
        if (account_password.equals(password)) {
            return "Login Successful";
        }
        return "Wrong password";
    }
}
