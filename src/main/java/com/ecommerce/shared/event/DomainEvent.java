package com.ecommerce.shared.event;

import com.ecommerce.shared.utils.UuidGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static java.time.Instant.now;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class DomainEvent {
    private String id;
    private Instant createdAt;
    private String aggregate;

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
