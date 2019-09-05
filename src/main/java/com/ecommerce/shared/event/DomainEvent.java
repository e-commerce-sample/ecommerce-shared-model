package com.ecommerce.shared.event;

import com.ecommerce.shared.utils.UuidGenerator;
import lombok.Getter;

import java.time.Instant;

import static java.time.Instant.now;


@Getter
public abstract class DomainEvent {
    private String id;
    private Instant createdAt;
    private String aggregate;

    @Deprecated
    protected DomainEvent() {
    }

    protected DomainEvent(String aggregate) {
        this.id = UuidGenerator.newUuid();
        this.createdAt = now();
        this.aggregate = aggregate;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + id + "]";
    }

}
