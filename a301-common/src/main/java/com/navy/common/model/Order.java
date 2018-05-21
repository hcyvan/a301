package com.navy.common.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "a_order")
public class Order extends BaseEntity {
    @Id
    @SequenceGenerator(name="orderIdSeq", sequenceName = "orderIdSeqGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderIdSeq")
    private Integer id;
    private String customerId;
    private String customerName;
    private String sellerId;
    private String sellerName;
    private Float totalPrice;
    private Integer status;
    private String address;
    private String phone;
    @Column(columnDefinition = "TEXT")
    private String remark;


    public Integer getId() {
        return this.id;
    }
    public void  setId(Integer id) {
        this.id = id;
    }
    public String getCustomerId() {
        return this.customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return this.customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getSellerId() {
        return this.sellerId;
    }
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    public String getSellerName() {
        return this.sellerName;
    }
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
    public Float getTotalPrice() {
        return this.totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Integer getStatus() {
        return this.status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderSku> orderSkus;
    public List<OrderSku> getOrderSkus() {
        return this.orderSkus;
    }
    public void setOrderSkus(List<OrderSku> orderSkus) {
        this.orderSkus = orderSkus;
    }
}
