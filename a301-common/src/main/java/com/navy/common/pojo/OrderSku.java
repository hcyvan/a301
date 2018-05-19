package com.navy.common.pojo;


public class OrderSku {
    private String skuCode;
    private Integer number;

    public OrderSku(String skuCode, Integer number) {
        this.skuCode = skuCode;
        this.number = number;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
