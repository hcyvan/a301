package com.navy.c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;

import com.navy.common.pojo.SessionData;
import com.navy.common.pojo.Result;
import com.navy.c.service.SecurityService;
import com.navy.c.model.AccountC;
import com.navy.c.repository.AccountRepository;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SecurityService securityService;

    @PostMapping("/register")
    public Result register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        String bcryptPassword = bCryptPasswordEncoder.encode(password);
        AccountC newAccount = new AccountC();
        newAccount.setEmail(email);
        newAccount.setName(name);
        newAccount.setPassword(bcryptPassword);
        accountRepository.save(newAccount);
        return Result.ok();
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam String email, @RequestParam String password) {
        securityService.login(email, password);
        return Result.ok();
    }
    @PostMapping("/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, authentication);
        return Result.ok();
    }
    @GetMapping("/session")
    @ResponseBody
    public Result session() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String email = userDetails.getUsername();
        AccountC account = accountRepository.getAccountByEmail(email);
        return Result.ok(new SessionData(account.getName(), account.getEmail()));
    }
}