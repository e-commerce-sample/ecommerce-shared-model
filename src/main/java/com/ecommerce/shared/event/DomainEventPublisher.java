package com.ecommerce.shared.event;

public interface DomainEventPublisher {
    void publishNextBatch(int size, boolean containsPreviousFailed);

    void forcePublish(String eventId);
}
