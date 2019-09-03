package com.ecommerce.shared.model;


import com.ecommerce.shared.event.publish.DomainEventPublishingRecorder;

public abstract class BaseRepository<AR extends BaseAggregate> {
    private DomainEventPublishingRecorder recorder;

    public BaseRepository(DomainEventPublishingRecorder recorder) {
        this.recorder = recorder;
    }

    public void save(AR aggregate) {
        recorder.record(aggregate.getEvents());
        aggregate.clearEvents();
        doSave(aggregate);
    }

    protected abstract void doSave(AR aggregate);
}
