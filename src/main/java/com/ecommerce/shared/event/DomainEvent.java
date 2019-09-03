package com.ecommerce.shared.event;

import com.ecommerce.shared.utils.UuidGenerator;

import java.time.Instant;

import static java.time.Instant.now;


public abstract class DomainEvent {
    private final String _id;
    private final Instant _createdAt;

    protected DomainEvent() {
        this._id = UuidGenerator.newUuid();
        this._createdAt = now();
    }

    public String get_id() {
        return _id;
    }


    public Instant get_createdAt() {
        return _createdAt;
    }

    @Override
    public String
    toString() {
        return "DomainEvent{" +
                "_id='" + _id + '\'' +
                ", _createdAt=" + _createdAt +
                '}';
    }
}
