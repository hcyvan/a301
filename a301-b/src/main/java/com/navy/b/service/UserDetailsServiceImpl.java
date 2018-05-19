package com.navy.b.service;

import com.navy.b.model.AccountB;
import com.navy.b.repository.AccountBRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.HashSet;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private AccountBRepository accountBRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        AccountB accountB = accountBRepository.getAccountBById(userId).orElseThrow(()->
                new UsernameNotFoundException("B Account Not Exist: userId: " + userId));
        return new User(accountB.getId(), accountB.getPassword(), new HashSet<>());
    }
}
