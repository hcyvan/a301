package com.navy.b.controller;

import com.navy.b.pojo.ProductRequest;
import com.navy.common.model.Product;
import com.navy.common.model.ProductSku;
import com.navy.common.pojo.Result;
import com.navy.common.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/products")
    @ResponseBody
    public Result createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = new Product(
                productRequest.getName(),
                productRequest.getItemNumber(),
                productRequest.getBrands(),
                productRequest.getDescription()
        );
        productRequest.getProductSkus().forEach(productSkuRequest ->
            product.addProductSkus(
                    new ProductSku(
                            productSkuRequest.getPropertiesName(),
                            productSkuRequest.getPrice(),
                            productSkuRequest.getStockNumber(),
                            UUID.randomUUID().toString().replace("-", "")

                    )));
        Product result = productRepository.save(product);
        return Result.ok(result.getId());
    }
    @GetMapping("/products")
    @ResponseBody
    public Result indexProduct(Pageable pageable) {
        Page<Product> pages = productRepository.findAll(pageable);
        return Result.ok(pages);
    }
}
