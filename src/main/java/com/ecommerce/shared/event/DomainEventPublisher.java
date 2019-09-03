package com.ecommerce.shared.event;

import com.ecommerce.shared.utils.DistributedLockExecutor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockConfiguration;

import java.time.Instant;
import java.util.List;


@Slf4j
public abstract class DomainEventPublisher {
    private final DomainEventPublishingRecorder domainEventPublishingRecorder;
    private final DistributedLockExecutor lockExecutor;

    public DomainEventPublisher(DomainEventPublishingRecorder domainEventPublishingRecorder,
                                DistributedLockExecutor lockExecutor) {
        this.domainEventPublishingRecorder = domainEventPublishingRecorder;
        this.lockExecutor = lockExecutor;
    }

    public void publish() {
        Instant now = Instant.now();
        LockConfiguration configuration = new LockConfiguration("domain-event-publisher", now.plusSeconds(60));
        lockExecutor.execute(this::doPublish, configuration);
    }

    private Void doPublish() {
        List<DomainEvent> newestEvents = domainEventPublishingRecorder.toBePublishedEvents();
        newestEvents.forEach(event -> {
            try {
                domainEventPublishingRecorder.increasePublishTry(event.get_id());
                send(event);
                log.debug("Published {}.", event);
                domainEventPublishingRecorder.delete(event.get_id());
            } catch (Throwable t) {
                log.error("Error while publish domain event {}:{}", event, t.getMessage());
            }
        });
        return null;
    }


    //Using actual messaging mechanism(e.g. Kafka/RabbitMQ) to sent the event
    protected abstract void send(DomainEvent event);
}


