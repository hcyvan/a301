package com.navy.common.repository;

import com.navy.common.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account getAccountByEmail(String email);
}
