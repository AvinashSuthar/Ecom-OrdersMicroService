package com.ecom.order.mapper;

import com.ecom.order.dtos.client.ProductDTO;
import com.ecom.order.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItem mapOrderItemEntityToOrderItem(ProductDTO productDTO , Integer quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productDTO.getProductId());
        orderItem.setPrice(productDTO.getPrice());
        orderItem.setTotalPrice(( productDTO.getPrice() - productDTO.getDiscount() ) * quantity);
        orderItem.setDiscount(productDTO.getDiscount() *  quantity);
        orderItem.setQuantity(quantity);
        return orderItem;
    }
}
