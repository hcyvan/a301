package com.navy.c.repository;

import com.navy.c.model.AccountC;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<AccountC, Long> {
    Optional<AccountC> getAccountCByEmail(String email);
    Optional<AccountC> getAccountCById(String id);
}
