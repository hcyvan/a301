package com.navy.c.service;

import com.navy.c.repository.AccountCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.HashSet;

import com.navy.c.model.AccountC;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountCRepository accountCRepository;

    @Override
    public void login(String email, String password){
        UserDetails userDetails = this.loadUserByEmail(email);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    private UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        AccountC accountC = accountCRepository.getAccountCByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("B Account Not Exist: email: " + email)
        );
        return new User(accountC.getId(), accountC.getPassword(), new HashSet<>());
    }
}
