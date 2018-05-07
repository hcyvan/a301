package com.navy.b;

import com.navy.b.model.AccountB;
import com.navy.b.repository.AccountBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.navy.common.pojo.Result;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AccountBRepository accountBRepository;

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam String cell, @RequestParam String password, @RequestParam String name) {
        AccountB accountB = accountBRepository.getAccountBByCell(cell);
        if (accountB != null) {
            return Result.build(1, null,"手机号已经注册");
        }

        String bcryptPassword = bCryptPasswordEncoder.encode(password);
        AccountB newAccount = new AccountB();
        newAccount.setCell(cell);
        newAccount.setName(name);
        newAccount.setPassword(bcryptPassword);
        accountBRepository.save(newAccount);
        return Result.ok();
    }

    @GetMapping("/login")
    @ResponseBody
    public Result login(@RequestParam String cell, @RequestParam String password) {
        return Result.ok();
    }
}
