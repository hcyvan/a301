package com.navy.common.service;

import com.navy.common.pojo.OrderSkuServicePojo;

import java.util.List;

public interface OrderService {
    public void createOrder(String customerId, String address, String remark,
                            List<OrderSkuServicePojo> orderSkuServicePojoList);
//    public List<OrderSku> indexOrder();
//    public void updateOrder();
}
