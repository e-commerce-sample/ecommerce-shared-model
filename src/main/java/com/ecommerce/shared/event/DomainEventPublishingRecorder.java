package com.ecommerce.shared.event;

import java.util.List;

public interface DomainEventPublishingRecorder {
    void record(DomainEvent event);

    void record(List<DomainEvent> events);

    List<DomainEvent> toBePublishedEvents();

    void delete(String eventId);

    void increasePublishTry(String eventId);

}
