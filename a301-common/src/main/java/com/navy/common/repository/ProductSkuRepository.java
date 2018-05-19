package com.navy.common.repository;

import com.navy.common.model.ProductSku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductSkuRepository extends JpaRepository<ProductSku, Long> {
    Optional<ProductSku> findById(Integer id);
    void deleteById(Integer id);
    List<ProductSku> findAllBySkuCodeIn(List<String> skuCode);
}
