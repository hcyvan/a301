package com.navy.b.pojo;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductRequest {
    @NotNull
    private String name;
    @NotNull
    private String itemNumber;
    @NotNull
    private String brands;
    @NotNull
    private String description;
    @Size(max = 50)
    private List<ProductSkuRequest> productSkus;

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getItemNumber() {
        return this.itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getBrands() {
        return this.brands;
    }
    public void setBrands(String brands) {
        this.brands = brands;
    }
    public List<ProductSkuRequest> getProductSkus() {
        return this.productSkus;
    }
    public void setProductSkus(List<ProductSkuRequest> productSkus) {
        this.productSkus = productSkus;
    }
}
