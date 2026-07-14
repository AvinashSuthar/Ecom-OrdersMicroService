package com.ecom.order.dtos.client;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductDTO {
    private Long productId;
    private Double price;
    private Integer quantity;
    private Double discount;
}
