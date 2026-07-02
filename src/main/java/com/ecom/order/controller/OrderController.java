package com.ecom.order.controller;

import com.ecom.order.apireponse.APIResponse;
import com.ecom.order.dtos.request.CreateOrderRequest;
import com.ecom.order.entity.Order;
import com.ecom.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<APIResponse<Order>> createOrder(CreateOrderRequest createOrderRequest){
        Order order = orderService.createOrder(createOrderRequest);
        return new ResponseEntity<>(new APIResponse<>(true, "Order created successfully", order), HttpStatus.CREATED);
    }

}
