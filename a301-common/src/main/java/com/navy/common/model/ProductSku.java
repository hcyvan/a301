package com.navy.common.model;

import org.hibernate.annotations.GenericGenerator;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class ProductSku extends BaseEntity {
    @Id
    @SequenceGenerator(name="skuIdSeq", sequenceName = "skuIdSeqGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skuIdSeq")
    private Integer id;
    @GenericGenerator(name="systemUUID",strategy="uuid")
    @GeneratedValue(generator="systemUUID")
    private String skuCode; // sku
    private String propertiesName; // 规格等属性
    private Float price;    // 价格
    private Integer stockNumber; // 库存

    public ProductSku() {}
    public ProductSku(String propertiesName, Float price, Integer stockNumber, String skuCode) {
        this.propertiesName = propertiesName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.skuCode = skuCode;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSkuCode() {
        return this.skuCode;
    }
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    public String getPropertiesName() {
        return this.propertiesName;
    }
    public void setPropertiesName(String propertiesName) {
        this.propertiesName = propertiesName;
    }
    public Float getPrice() {
        return this.price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public Integer getStockNumber() {
        return this.stockNumber;
    }
    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference
    private Product product;
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
}
