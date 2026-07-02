package com.ecom.order.dtos.request;

import com.ecom.order.entity.OrderItem;
import com.ecom.order.entity.ShippingAddress;

import java.util.List;

public record CreateOrderRequest(
        List<OrderItem> orderItems,
        Long addressId
) {
}
