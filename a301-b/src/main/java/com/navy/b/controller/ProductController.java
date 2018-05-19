package com.navy.b.controller;

import com.navy.b.pojo.ProductPutRequest;
import com.navy.b.pojo.ProductRequest;
import com.navy.b.pojo.ProductSkuRequest;
import com.navy.common.model.Product;
import com.navy.common.model.ProductSku;
import com.navy.common.pojo.Result;
import com.navy.common.repository.ProductRepository;
import com.navy.common.exception.ResourceNotFoundException;
import com.navy.common.repository.ProductSkuRepository;
import com.navy.common.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SessionService sessionService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductSkuRepository productSkuRepository;

    @PostMapping("/products")
    @ResponseBody
    public Result createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = new Product(
                sessionService.getCurrentUserId(),
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
    public Result indexProduct(@PageableDefault(value = 1, sort = {"created"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Product> pages = productRepository.findAll(pageable);
        return Result.ok(pages.getContent());
    }

    @GetMapping("/products/{productId}")
    @ResponseBody
    public Result getProductByID(@PathVariable Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        return Result.ok(product);
    }

    @PutMapping("/products/{productId}")
    @ResponseBody
    public Result updateProduct(@PathVariable Integer productId, @RequestBody ProductPutRequest productPutRequest) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        String name = productPutRequest.getName();
        String itemNumber = productPutRequest.getItemNumber();
        String brands = productPutRequest.getBrands();
        String description = productPutRequest.getDescription();
        if (name != null) {
            product.setName(name);
        }
        if (itemNumber != null) {
            product.setItemNumber(itemNumber);
        }
        if (brands != null) {
            product.setBrands(brands);
        }
        if (description != null) {
            product.setDescription(description);
        }
        if (name != null || itemNumber != null || brands != null || description != null) {
            productRepository.save(product);
        }
        return Result.ok();
    }

    @DeleteMapping("/products/{productId}")
    @ResponseBody
    @Transactional
    public Result deleteProduct(@PathVariable Integer productId) {
        productRepository.deleteById(productId);
        return Result.ok();
    }
    @PostMapping("/products/{productId}/productSkus")
    @ResponseBody
    public Result createProductSku(@RequestBody ProductSkuRequest productSkuRequest, @PathVariable Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        ProductSku productSku = new ProductSku(
                productSkuRequest.getPropertiesName(),
                productSkuRequest.getPrice(),
                productSkuRequest.getStockNumber(),
                UUID.randomUUID().toString().replace("-", "")
        );
        product.addProductSkus(productSku);
        productRepository.save(product);
        return Result.ok();
    }
    @PutMapping("/productSkus/{productSkuId}")
    @ResponseBody
    public Result updateProductSkus(@RequestBody ProductSkuRequest productSkuRequest, @PathVariable Integer productSkuId){
        ProductSku productSku = productSkuRepository.findById(productSkuId).orElseThrow(() -> new ResourceNotFoundException("ProductSku", "id", productSkuId));
        String propertiesName = productSkuRequest.getPropertiesName();
        Float price = productSkuRequest.getPrice();
        Integer stockNumber = productSkuRequest.getStockNumber();
        if (propertiesName != null) {
            productSku.setPropertiesName(propertiesName);
        }
        if (price != null) {
            productSku.setPrice(price);
        }
        if (stockNumber != null) {
            productSku.setStockNumber(stockNumber);
        }
        if (propertiesName != null || price != null || stockNumber != null) {
            productSkuRepository.save(productSku);
        }
        return Result.ok();
    }
    @DeleteMapping("/productSkus/{productSkuId}")
    @ResponseBody
    @Transactional
    public Result deleteProductSkus(@PathVariable Integer productSkuId) {
        productSkuRepository.deleteById(productSkuId);
        return Result.ok();
    }
}
