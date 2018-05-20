package com.navy.c.repository;

import com.navy.c.model.AccountC;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountCRepository extends CrudRepository<AccountC, Long> {
    Optional<AccountC> getAccountCByEmail(String email);
    Optional<AccountC> getAccountCById(String id);
}
