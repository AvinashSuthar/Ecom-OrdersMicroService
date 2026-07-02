package com.ecom.order.service.impl;

import com.ecom.order.dtos.request.CreateOrderRequest;
import com.ecom.order.entity.Order;
import com.ecom.order.mapper.OrderMapper;
import com.ecom.order.repository.OrderRepository;
import com.ecom.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(CreateOrderRequest createOrderRequest) {
        Order  order = OrderMapper.mapToOrder(createOrderRequest);
        return orderRepository.save(order);
    }

}
