package com.ecommerce.shared.event.publish;

import com.ecommerce.shared.event.DomainEvent;

import java.util.List;

public interface DomainEventPublishingRecorder {
    void record(DomainEvent event);

    void record(List<DomainEvent> events);

    List<DomainEvent> toBePublishedEvents();

    void delete(String eventId);

    void increasePublishTry(String eventId);

}
