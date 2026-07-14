package com.ecom.order.clients;

import com.ecom.order.apireponse.APIResponse;
import com.ecom.order.entity.ShippingAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service" , url = "${user.service.url}")
public interface UserClient {
    @GetMapping("/api/v1/users/address/{addressId}")
    APIResponse<ShippingAddress>  getShippingAddress(@PathVariable Long addressId);
}
