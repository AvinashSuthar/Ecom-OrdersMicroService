package com.ecom.order.service;

import com.ecom.order.dtos.request.CreateOrderRequest;
import com.ecom.order.entity.Order;

public interface OrderService {
    Order createOrder(CreateOrderRequest createOrderRequest);

}
