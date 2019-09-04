package com.ecommerce.shared.event;

import java.util.List;

public interface DomainEventRecorder {
    void recordForPublishing(DomainEvent event);

    void recordForPublishing(List<DomainEvent> events);

    List<DomainEvent> toBePublishedEvents();

    void markAsPublished(String eventId);

    void deleteAllPublishingEvents();

    void increasePublishTry(String eventId);

    boolean recordForConsuming(DomainEvent event);

    void deleteAllConsumedEvents();
}
