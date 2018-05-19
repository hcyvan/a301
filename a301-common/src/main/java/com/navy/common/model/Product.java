package com.navy.common.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "a_product")
public class Product extends BaseEntity{
    @Id
    @SequenceGenerator(name="productIdSeq", sequenceName = "productIdSeqGen", initialValue = 100000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdSeq")
    private Integer id;
    private String sellerId;
    private String name;
    private String itemNumber;
    private String brands;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Product() {}
    public Product(String sellerId, String name, String itemNumber, String brands, String description) {
        this.sellerId = sellerId;
        this.name = name;
        this.itemNumber = itemNumber;
        this.brands = brands;
        this.description = description;
    }

    public Integer getId() {
        return this.id;
    }
    void setId(Integer id) {
        this.id = id;
    }
    public String getSellerId() {
        return this.sellerId;
    }
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getItemNumber() {
        return  this.itemNumber;
    }
    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }
    public String getBrands() {
        return this.brands;
    }
    public void setBrands(String brands) {
        this.brands = brands;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductSku> productSkus = new ArrayList<>();
    public List<ProductSku> getProductSkus() {
        return this.productSkus;
    }
    public void setProductSkus(List<ProductSku> productSkus) {
        this.productSkus = productSkus;
    }
    public void addProductSkus(ProductSku productSku) {
        this.productSkus.add(productSku);
        productSku.setProduct(this);
    }
}
