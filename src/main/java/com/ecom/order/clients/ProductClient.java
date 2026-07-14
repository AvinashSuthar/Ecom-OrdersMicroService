package com.ecom.order.clients;

import com.ecom.order.apireponse.APIResponse;
import com.ecom.order.dtos.client.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service" , url = "${product.service.url}")
public interface ProductClient {
    @GetMapping("/api/v1/products/{productId}")
    APIResponse<ProductDTO> getProduct(@PathVariable Long productId);

}
