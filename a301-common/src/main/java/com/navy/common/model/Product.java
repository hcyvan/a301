package com.navy.common.model;

import javax.persistence.*;
import java.util.*;

@Entity
public class Product extends BaseEntity{
    @Id
    @SequenceGenerator(name="product_id_seq", sequenceName = "product_id_seq_gen", initialValue = 100000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    private Integer id;
    private String name;
    private String itemNumber;
    private String brands;
    @Column(columnDefinition = "TEXT")
    private String description;

    public Product() {}
    public Product(String name, String itemNumber, String brands, String description) {
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
    public String getName() {
        return this.name;
    }
    void setName(String name) {
        this.name = name;
    }
    public String getItemNumber() {
        return  this.itemNumber;
    }
    void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }
    public String getBrands() {
        return this.brands;
    }
    void setBrands(String brands) {
        this.brands = brands;
    }
    public String getDescription() {
        return this.description;
    }
    void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
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
