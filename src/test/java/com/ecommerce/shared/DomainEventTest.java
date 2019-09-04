package com.ecommerce.shared;

import com.ecommerce.shared.event.DomainEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DomainEventTest {

    @Test
    public void shouldCreateEvent() {
        DomainEvent domainEvent = new DomainEvent("order") {
        };

        assertNotNull(domainEvent);
    }

}