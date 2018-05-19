package com.navy.b.service;

import com.navy.b.model.AccountB;
import com.navy.b.repository.AccountBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class SecurityServiceImpl implements SecurityService{
    @Autowired
    AccountBRepository accountBRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void login(String cell, String password){
        UserDetails userDetails = this.loadUserByCell(cell);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails loadUserByCell(String cell) throws UsernameNotFoundException {
        AccountB accountB = accountBRepository.getAccountBByCell(cell).orElseThrow(
                () -> new UsernameNotFoundException("B Account Not Exist: cell: " + cell)
        );
        return new User(accountB.getId(), accountB.getPassword(), new HashSet<>());
    }
}
