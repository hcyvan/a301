package com.navy.c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.navy.c.model.AccountC;
import com.navy.c.repository.AccountRepository;

import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountC account = accountRepository.getAccountByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("C Account Not Exist: email: " + email )
        );
        return new User(account.getEmail(), account.getPassword(), new HashSet<>());
    }
}
