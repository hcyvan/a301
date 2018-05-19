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
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        AccountC account = accountRepository.getAccountCById(userId).orElseThrow(
                ()->new UsernameNotFoundException("C Account Not Exist: id: " + userId)
        );
        return new User(account.getId(), account.getPassword(), new HashSet<>());
    }
}
