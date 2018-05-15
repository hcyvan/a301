package com.navy.common.repository;

import com.navy.common.model.ProductSku;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductSkuRepository extends JpaRepository<ProductSku, Long> {
}
