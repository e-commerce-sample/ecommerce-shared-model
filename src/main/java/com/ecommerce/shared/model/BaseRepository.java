package com.ecommerce.shared.model;


import com.ecommerce.shared.event.DomainEventPublishingRecorder;

public abstract class BaseRepository<AR extends BaseAggregate> {
    private DomainEventPublishingRecorder domainEventPublishingRecorder;

    public BaseRepository(DomainEventPublishingRecorder domainEventPublishingRecorder) {
        this.domainEventPublishingRecorder = domainEventPublishingRecorder;
    }

    public void save(AR aggregate) {
        domainEventPublishingRecorder.record(aggregate.getEvents());
        aggregate.clearEvents();
        doSave(aggregate);
    }

    protected abstract void doSave(AR aggregate);
}
