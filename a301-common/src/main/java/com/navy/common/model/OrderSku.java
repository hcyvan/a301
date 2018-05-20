package com.navy.common.model;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "a_order_sku")
public class OrderSku extends BaseEntity {
    @Id
    @SequenceGenerator(name="orderProductIdSeq", sequenceName = "orderProductIdSeqGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderProductIdSeq")
    private Integer id;
    private String skuCode;
    private Integer number;
    private Float price;

    public OrderSku(String skuCode, Integer number, Float price) {
        this.skuCode = skuCode;
        this.number = number;
        this.price = price;
    }

    public Integer getId() {
        return this.id;
    }
    public void  setId(Integer id) {
        this.id = id;
    }
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
    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @ManyToOne
    private Order order;
    public Order getOrder() {
        return this.order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}
