package com.navy.common.service;

import com.navy.common.model.Order;
import com.navy.common.model.ProductSku;
import com.navy.common.pojo.OrderSku;
import com.navy.common.repository.OrderRepository;
import com.navy.common.repository.ProductSkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductSkuRepository productSkuRepository;

    @Override
    public void createOrder(Integer customerId, Integer sellerId, String address, String remark,
                            List<OrderSku> orderSkus) {
//        Order order = new Order();
//        order.setAddress(address);
//        order.setRemark(remark);
        List<String> skuCodes = orderSkus.stream().map(orderSku ->  orderSku.getSkuCode()).collect(Collectors.toList());
        List<ProductSku> productSkus = productSkuRepository.findAllBySkuCodeIn(skuCodes);
    }
}
