package com.ecom.order.dtos.request;

import java.util.List;

public record CreateOrderRequest(
        List<ItemInfo> items,
        Long addressId
) {
}