package com.ecom.order.mapper;

import com.ecom.order.dtos.client.ProductDTO;
import com.ecom.order.dtos.request.CreateOrderRequest;
import com.ecom.order.entity.Order;
import com.ecom.order.entity.OrderItem;
import com.ecom.order.entity.OrderStatus;
import com.ecom.order.entity.ShippingAddress;

import java.util.List;

public class OrderMapper {

    public static Order mapToOrder(List<OrderItem> orderItems , ShippingAddress shippingAddress) {
        Order  order = new Order();
        order.setOrderItems(orderItems);
        order.setShippingAddress(shippingAddress);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setDeliveryFee(50.0);
        return order;
    }
    public static OrderItem mapToOrderItem(ProductDTO productDTO){
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productDTO.getProductId());
        orderItem.setPrice(productDTO.getPrice());
        orderItem.setQuantity(productDTO.getQuantity());
        orderItem.setDiscount(productDTO.getDiscount());
        return orderItem;
    }

}
