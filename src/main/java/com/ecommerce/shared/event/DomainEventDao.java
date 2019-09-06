package com.ecommerce.shared.event;

import java.util.List;

public interface DomainEventDao {
    void save(List<DomainEvent> events);

    void delete(String eventId);

    DomainEvent get(String eventId);

    List<DomainEvent> nextPublishBatch(int size);

    void markAsPublished(String eventId);

    void markAsPublishFailed(String eventId);

    void deleteAll();

}
