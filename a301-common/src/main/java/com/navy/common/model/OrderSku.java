package com.navy.common.model;

import javax.persistence.*;

@Entity
@Table(name = "a_order_sku")
public class OrderSku extends BaseEntity {
    @Id
    @SequenceGenerator(name="orderProductIdSeq", sequenceName = "orderProductIdSeqGen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orderProductIdSeq")
    private Integer id;

    public Integer getId() {
        return this.id;
    }
    public void  setId(Integer id) {
        this.id = id;
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
