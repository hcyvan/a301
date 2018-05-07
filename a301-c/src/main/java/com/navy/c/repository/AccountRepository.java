package com.navy.c.repository;

import com.navy.c.model.AccountC;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountC, Long> {
    AccountC getAccountByEmail(String email);
}
