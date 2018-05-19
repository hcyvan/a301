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
    private Integer customerId;
    private String customerName;
    private Integer sellerId;
    private Float totalPrice;
    private Integer status;
    private String address;
    @Column(columnDefinition = "TEXT")
    private String remark;


    public Integer getId() {
        return this.id;
    }
    public void  setId(Integer id) {
        this.id = id;
    }
    public Integer getCustomerId() {
        return this.customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return this.customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public Integer getSellerId() {
        return this.sellerId;
    }
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;
    public List<OrderProduct> getOrderProducts() {
        return this.orderProducts;
    }
    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
