package com.ecommerce.shared.event;

import com.ecommerce.shared.utils.UuidGenerator;
import lombok.Getter;

import java.time.Instant;

import static java.time.Instant.now;


@Getter
public abstract class DomainEvent {
    private String _id = UuidGenerator.newUuid();
    private Instant _createdAt = now();

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" + _id + "]";
    }

}
