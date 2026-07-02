package com.ecom.order.apireponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {
    private Boolean status;
    private String message;
    private T data;
}
