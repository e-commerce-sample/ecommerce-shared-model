package com.ecommerce.shared.model;

import com.ecommerce.shared.event.DomainEvent;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public abstract class BaseAggregate {
    private List<DomainEvent> _events;

    protected final void raiseEvent(DomainEvent event) {
        get_events().add(event);
    }

    final void clearEvents() {
        get_events().clear();
    }

    final List<DomainEvent> get_events() {
        if (_events == null) {
            _events = newArrayList();
        }
        return _events;
    }

}
