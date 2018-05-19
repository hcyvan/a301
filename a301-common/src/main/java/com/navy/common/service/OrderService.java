package com.navy.common.service;

import com.navy.common.model.OrderProduct;
import com.navy.common.pojo.OrderSku;

import java.util.List;

public interface OrderService {
    public void createOrder(Integer customerId, Integer sellerId, String address, String remark, List<OrderSku> orderSkus);
//    public List<OrderProduct> indexOrder();
//    public void updateOrder();
}
