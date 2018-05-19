package com.navy.c.service;

import com.navy.c.model.AccountC;
import com.navy.c.repository.AccountCRepository;
import com.navy.common.exception.ResourceNotFoundException;
import com.navy.common.service.AbstractSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl extends AbstractSessionService{
    @Autowired
    private AccountCRepository accountCRepository;

    @Override
    public AccountC getCurrentUser() {
        String id = this.getCurrentUserId();
        AccountC accountC = accountCRepository.getAccountCById(id).orElseThrow(
                ()->new ResourceNotFoundException("AccountC", "id", id)
        );
        return accountC;
    }
}
