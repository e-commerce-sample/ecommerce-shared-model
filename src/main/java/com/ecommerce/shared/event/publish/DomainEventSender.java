package com.ecommerce.shared.event.publish;

import com.ecommerce.shared.event.DomainEvent;

public interface DomainEventSender {
    void send(DomainEvent event);
}
