package com.navy.b.repository;

import com.navy.b.model.AccountB;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountBRepository extends CrudRepository<AccountB, Long> {
    Optional<AccountB> getAccountBByCell(String cell);
    Optional<AccountB> getAccountBById(String Id);
}
