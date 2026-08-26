package com.ecom.order.service.impl;

import com.ecom.order.apireponse.APIResponse;
import com.ecom.order.clients.ProductClient;
import com.ecom.order.clients.UserClient;
import com.ecom.order.constants.Constants;
import com.ecom.order.dtos.client.ProductDTO;
import com.ecom.order.dtos.order.OrderCreatedEvent;
import com.ecom.order.dtos.request.CreateOrderRequest;
import com.ecom.order.dtos.request.ItemInfo;
import com.ecom.order.entity.Order;
import com.ecom.order.entity.OrderItem;
import com.ecom.order.entity.OrderStatus;
import com.ecom.order.entity.ShippingAddress;
import com.ecom.order.mapper.OrderItemMapper;
import com.ecom.order.mapper.OrderMapper;
import com.ecom.order.repository.OrderRepository;
import com.ecom.order.service.OrderEventProducer;
import com.ecom.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final UserClient userClient;
    private final OrderEventProducer orderEventProducer;
    @Override
    public Order createOrder(CreateOrderRequest createOrderRequest) {
        List<OrderItem> orderItems = createOrderRequest.items().stream().map(item -> {
            APIResponse<ProductDTO> productDTO = productClient.getProduct(item.productId());
            return OrderItemMapper.mapOrderItemEntityToOrderItem(productDTO.getData(), item.quantity());
        }).toList();
        ShippingAddress shippingAddress = userClient.getShippingAddress(createOrderRequest.addressId()).getData();
        ShippingAddress shippingAddressEntity = new ShippingAddress();
        shippingAddressEntity.setAddressId(shippingAddress.getAddressId());
        shippingAddressEntity.setStreet(shippingAddress.getStreet());
        shippingAddressEntity.setCity(shippingAddress.getCity());
        shippingAddressEntity.setState(shippingAddress.getState());
        shippingAddressEntity.setPostalCode(shippingAddress.getPostalCode());
        shippingAddressEntity.setCountry(shippingAddress.getCountry());
        Order order = new Order();
        order.setOrderStatus(OrderStatus.CREATED);
        order.setOrderItems(orderItems);
        order.setShippingAddress(shippingAddressEntity);
        order.setDeliveryFee(50.0);
        order.setTotalAmount(calculateOrderAmount(order));
        order.setDiscountAmount(orderItems.stream().mapToDouble(OrderItem::getDiscount).sum());
        //TODO: userid
        order.setUserId(1L);

        Order newOrder = orderRepository.save(order);
        //TODO: create a kafka event for order created
        OrderCreatedEvent orderCreatedEvent = new OrderCreatedEvent(
                UUID.randomUUID().toString(),
                Constants.ORDER_CREATED,
                newOrder.getOrderId(),
                newOrder.getUserId(),
            newOrder.getTotalAmount(),
                Instant.now()
        );
        orderEventProducer.publishOrderCreated(orderCreatedEvent);
        return newOrder;
    }


    private Double calculateOrderAmount(Order order){
        Double totalAmount = order.getOrderItems().stream().mapToDouble(OrderItem::getTotalPrice).sum();
        return totalAmount + order.getDeliveryFee();
    }
}


