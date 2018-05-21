package com.navy.common.service;

import com.navy.common.model.Order;
import com.navy.common.model.OrderSku;
import com.navy.common.model.ProductSku;
import com.navy.common.pojo.OrderSkuServicePojo;
import com.navy.common.repository.OrderRepository;
import com.navy.common.repository.ProductSkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductSkuRepository productSkuRepository;

    @Override
    public void createOrder(String customerId, String customerName, String address, String phone, String remark,
                            List<OrderSkuServicePojo> orderSkuServicePojos) {

        List<String> skuCodes = orderSkuServicePojos.stream().map(o->o.getSkuCode()).collect(Collectors.toList());
        List<ProductSku> productSkus = productSkuRepository.findAllBySkuCodeIn(skuCodes);
        Map<String, List<ProductSku>> productSkuGroupByProductSellerId = productSkus.stream().collect(
                Collectors.groupingBy(p->p.getProduct().getSellerId()));

        for (Entry<String, List<ProductSku>> entry :
                productSkuGroupByProductSellerId.entrySet()) {
            Order order = new Order();
            order.setAddress(address);
            order.setPhone(phone);
            order.setRemark(remark);
            order.setCustomerId(customerId);
            order.setCustomerName(customerName);
            order.setSellerId(entry.getKey());

            List<OrderSku> orderSkus = new ArrayList<>();
            entry.getValue().forEach(productSku-> {
                String skuCode = productSku.getSkuCode();
                Integer skuNumber = orderSkuServicePojos.stream()
                        .filter(e->e.getSkuCode().equals(skuCode)).findFirst().get().getNumber();
                orderSkus.add(new OrderSku(skuCode, skuNumber, productSku.getPrice()));
            });

            order.setOrderSkus(orderSkus);
            orderRepository.save(order);
        }


    }
}
