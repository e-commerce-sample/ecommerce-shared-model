package com.ecommerce.shared.event;

import com.ecommerce.shared.utils.UuidGenerator;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.Instant;

import static java.time.Instant.now;


@Getter
public abstract class DomainEvent {
    private String id;
    private Instant createdAt;

    @JsonCreator
    private DomainEvent(@JsonProperty("id") String id,
                        @JsonProperty("createdAt") Instant createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    protected DomainEvent() {
        this.id = UuidGenerator.newUuid();
        this.createdAt = now();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + id + "]";
    }

}
