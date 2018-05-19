package com.navy.common.repository;

import com.navy.common.model.OrderSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderSku, Long> {
}
