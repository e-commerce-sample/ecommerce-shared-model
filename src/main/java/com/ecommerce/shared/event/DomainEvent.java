package com.ecommerce.shared.event;

import com.ecommerce.shared.utils.UuidGenerator;
import lombok.Getter;

import java.time.Instant;

import static java.time.Instant.now;


@Getter
public abstract class DomainEvent {
    private String id;
    private Instant createdAt;

    protected DomainEvent() {
        this.id = UuidGenerator.newUuid();
        this.createdAt = now();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + id + "]";
    }

}
