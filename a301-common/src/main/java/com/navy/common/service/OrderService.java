package com.navy.common.service;

import com.navy.common.pojo.OrderSkuServicePojo;

import java.util.List;

public interface OrderService {
    void createOrder(String customerId, String customerName, String address, String phone, String remark,
                            List<OrderSkuServicePojo> orderSkuServicePojoList);
//    public List<OrderSku> indexOrder();
//    public void updateOrder();
}
