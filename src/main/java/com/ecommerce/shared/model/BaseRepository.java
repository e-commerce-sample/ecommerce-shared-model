package com.ecommerce.shared.model;


import com.ecommerce.shared.event.publish.DomainEventPublishingRecorder;

import javax.inject.Inject;

public abstract class BaseRepository<AR extends BaseAggregate> {

    @Inject
    private DomainEventPublishingRecorder recorder;

    public void save(AR aggregate) {
        recorder.record(aggregate.getEvents());
        aggregate.clearEvents();
        doSave(aggregate);
    }

    protected abstract void doSave(AR aggregate);
}
