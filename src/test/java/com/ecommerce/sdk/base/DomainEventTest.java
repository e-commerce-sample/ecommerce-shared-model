package com.ecommerce.sdk.base;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class DomainEventTest {

    @Test
    public void shouldCreateEvent() {
        DomainEvent domainEvent = new DomainEvent() {
        };

        assertNotNull(domainEvent);
    }

}