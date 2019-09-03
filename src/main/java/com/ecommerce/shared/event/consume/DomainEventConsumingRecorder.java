package com.ecommerce.shared.event.consume;

import com.ecommerce.shared.event.DomainEvent;

public interface DomainEventConsumingRecorder {
    boolean record(DomainEvent event);

    void clearAll();
}
