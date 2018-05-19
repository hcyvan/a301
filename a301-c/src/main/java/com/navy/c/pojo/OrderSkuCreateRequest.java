package com.navy.c.pojo;


import javax.validation.constraints.NotNull;

public class OrderSkuCreateRequest {
    @NotNull
    private String skuCode;
    @NotNull
    private Integer number;

    public String getSkuCode() {
        return this.skuCode;
    }
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    public Integer getNumber() {
        return this.number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
}
