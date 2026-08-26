package com.ecom.order.service;

import com.ecom.order.dtos.order.OrderCreatedEvent;

public interface OrderEventProducer {
    void publishOrderCreated(OrderCreatedEvent event);
}
