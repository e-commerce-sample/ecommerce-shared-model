package com.ecommerce.shared.event;

public interface DomainEventPublisher {
    void publishNextBatch();

    void publishNextBatch(int size);

    void forcePublish(String eventId);
}
