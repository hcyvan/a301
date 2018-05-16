package com.navy.b.pojo;

public class ProductPutRequest {
    private String name;
    private String itemNumber;
    private String brands;
    private String description;

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
}
