package com.ecom.order.dtos.order;

import java.time.Instant;

public record OrderCreatedEvent(
        String eventId,
        String eventType,
        Long orderId,
        Long userId,
        Double amount,
        Instant occurredAt
) {
}
