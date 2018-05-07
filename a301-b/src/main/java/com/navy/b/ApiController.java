package com.navy.b;

import com.navy.b.model.AccountB;
import com.navy.b.repository.AccountBRepository;
import com.navy.b.service.SecurityService;
import com.navy.b.pojo.SessionData;
import com.navy.common.pojo.Result;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AccountBRepository accountBRepository;
    @Autowired
    private SecurityService securityService;

    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam String cell, @RequestParam String password, @RequestParam String name) {
        if (accountBRepository.getAccountBByCell(cell).isPresent()) {
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

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam String cell, @RequestParam String password) {
        try {
            securityService.login(cell, password);
        } catch (UsernameNotFoundException e) {
            return Result.build(1, null, "用户未注册");
        }
        return Result.ok();
    }
    @GetMapping("session")
    @ResponseBody
    public Result session() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String cell = userDetails.getUsername();
        AccountB accountB = accountBRepository.getAccountBByCell(cell).orElse(null);
        if (accountB == null) {
            //TODO logo
            return Result.build(3, null, "服务内部错误");
        }
        return Result.ok(new SessionData(accountB.getName(), accountB.getCell()));
    }
}
