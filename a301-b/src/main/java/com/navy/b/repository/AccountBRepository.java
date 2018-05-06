package com.navy.b.repository;

import com.navy.b.model.AccountB;
import org.springframework.data.repository.CrudRepository;

public interface AccountBRepository extends CrudRepository<AccountB, Long> {
    public AccountB getAccountBByCell(String cell);
}
