package com.ecommerce.shared.event;

public interface DomainEventConsumingRecorder {
    void record(DomainEvent event);

    void clearAll();
}
