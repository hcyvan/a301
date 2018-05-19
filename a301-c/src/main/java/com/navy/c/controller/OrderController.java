package com.navy.c.controller;

import com.navy.c.pojo.OrderCreateRequest;
import com.navy.c.pojo.OrderSkuCreateRequest;
import com.navy.common.pojo.OrderSkuServicePojo;
import com.navy.common.pojo.Result;
import com.navy.common.service.OrderService;
import com.navy.common.service.SessionService;
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
    @Autowired
    private SessionService sessionService;

    @PostMapping("/order")
    @ResponseBody
    public Result createOrder(@Valid @RequestBody OrderCreateRequest orderCreateRequest) {
        String customerId = sessionService.getCurrentUserId();

        List<OrderSkuCreateRequest> orderSkuCreateRequests = orderCreateRequest.getOrderSkus();
        List<OrderSkuServicePojo> orderSkus = orderSkuCreateRequests.stream().map(orderSkuCreateRequest -> new OrderSkuServicePojo(
                orderSkuCreateRequest.getSkuCode(), orderSkuCreateRequest.getNumber())
        ).collect(Collectors.toList());
        orderService.createOrder(customerId, "", orderCreateRequest.getRemark(), orderSkus);
        return Result.ok();
    }
}
