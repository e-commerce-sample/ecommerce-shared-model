package com.ecommerce.shared.event;

public interface DomainEventSender {
    void send(DomainEvent event);
}
