package com.navy.c.controller;

import com.navy.common.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import com.navy.c.repository.AccountCRepository;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountCRepository accountCRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private SessionService sessionService;

    @PostMapping("/register")
    public Result register(@RequestParam String name, @RequestParam String email, @RequestParam String password) {
        String bcryptPassword = bCryptPasswordEncoder.encode(password);
        AccountC newAccount = new AccountC();
        newAccount.setEmail(email);
        newAccount.setName(name);
        newAccount.setPassword(bcryptPassword);
        accountCRepository.save(newAccount);
        return Result.ok();
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam String email, @RequestParam String password) {
        try {
            securityService.login(email, password);
        } catch (UsernameNotFoundException e) {
            return Result.build(1, null, "用户未注册");
        }
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
        String id = sessionService.getCurrentUserId();
        AccountC account = accountCRepository.getAccountCByEmail(id).orElseThrow(
                ()-> new UsernameNotFoundException("C Account Not Exist: id: " + id )
        );
        return Result.ok(new SessionData(account.getName(), account.getEmail()));
    }
}
