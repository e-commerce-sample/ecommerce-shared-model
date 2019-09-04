package com.ecommerce.shared.model;


import com.ecommerce.shared.event.DomainEventRecorder;

public abstract class BaseRepository<AR extends BaseAggregate> {

    private DomainEventRecorder eventRecorder;

    public BaseRepository(DomainEventRecorder eventRecorder) {
        this.eventRecorder = eventRecorder;
    }

    public void save(AR aggregate) {
        eventRecorder.recordForPublishing(aggregate.getEvents());
        aggregate.clearEvents();
        doSave(aggregate);
    }

    protected abstract void doSave(AR aggregate);
}
