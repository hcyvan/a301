package com.navy.b.service;

import com.navy.b.model.AccountB;
import com.navy.b.repository.AccountBRepository;
import com.navy.common.exception.ResourceNotFoundException;
import com.navy.common.service.AbstractSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl extends AbstractSessionService{
    @Autowired
    private AccountBRepository accountBRepository;

    @Override
    public AccountB getCurrentUser() {
        String id = this.getCurrentUserId();
        AccountB accountB = accountBRepository.getAccountBById(id).orElseThrow(
                ()->new ResourceNotFoundException("AccountB", "id", id)
        );
        return accountB;
    }
}
