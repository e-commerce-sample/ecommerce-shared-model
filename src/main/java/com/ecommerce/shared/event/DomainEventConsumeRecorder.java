package com.ecommerce.shared.event;

public interface DomainEventConsumeRecorder {

    boolean record(DomainEvent event);

    void deleteAll();
}
