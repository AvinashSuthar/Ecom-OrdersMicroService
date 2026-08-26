package com.ecom.order.service.impl;

import com.ecom.order.dtos.order.OrderCreatedEvent;
import com.ecom.order.service.OrderEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventProducerImpl implements OrderEventProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Override
    public void publishOrderCreated(OrderCreatedEvent event) {
        kafkaTemplate.send("order.events" , event.eventId() , event);
        log.info("Order event sent to topic: {}", event.eventId());
    }

}
