package com.ecom.order.mapper;

import com.ecom.order.dtos.request.CreateOrderRequest;
import com.ecom.order.entity.Order;

public class OrderMapper {

    public static Order mapToOrder(CreateOrderRequest createOrderRequest){
        Order  order = new Order();
        return order;
    }

}
