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
    public UserDetails loadUserByUsername(String cell) throws UsernameNotFoundException {
        AccountB accountB = accountBRepository.getAccountBByCell(cell).orElseThrow(()->
                new UsernameNotFoundException("B Account Not Exist: cell: " + cell));
        return new User(accountB.getCell(), accountB.getPassword(), new HashSet<>());
    }
}
