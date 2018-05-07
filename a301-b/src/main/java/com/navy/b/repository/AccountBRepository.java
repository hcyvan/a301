package com.navy.b.repository;

import com.navy.b.model.AccountB;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountBRepository extends CrudRepository<AccountB, Long> {
    Optional<AccountB> getAccountBByCell(String cell);
}
