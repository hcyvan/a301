package com.navy.c.pojo;

import javax.validation.constraints.Size;
import java.util.List;

public class OrderCreateRequest {
    private String remark;
    private String addressId;
    @Size(min = 1, max = 50)
    private List<OrderSkuCreateRequest> orderSkus;

    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getAddressId() {
        return this.addressId;
    }
    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
    public List<OrderSkuCreateRequest> getOrderSkus() {
        return this.orderSkus;
    }
    public void setOrderSkus(List<OrderSkuCreateRequest> orderSkuCreateRequests) {
        this.orderSkus = orderSkuCreateRequests;
    }
}
