package com.navy.c.controller;

import com.navy.c.pojo.OrderCreateRequest;
import com.navy.c.pojo.OrderSkuCreateRequest;
import com.navy.common.pojo.OrderSku;
import com.navy.common.pojo.Result;
import com.navy.common.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    @ResponseBody
    public Result createOrder(@Valid @RequestBody OrderCreateRequest orderCreateRequest) {
        List<OrderSkuCreateRequest> orderSkuCreateRequests = orderCreateRequest.getOrderSkus();
        List<OrderSku> orderSkus = orderSkuCreateRequests.stream().map(orderSkuCreateRequest -> new OrderSku(
                orderSkuCreateRequest.getSkuCode(), orderSkuCreateRequest.getNumber())
        ).collect(Collectors.toList());
        orderService.createOrder(0, 0, "",orderCreateRequest.getRemark(), orderSkus);
        return Result.ok();
    }
}
