package com.navy.b.pojo;

public class ProductSkuRequest {
    private String propertiesName;
    private Float price;
    private Integer stockNumber;

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
}
