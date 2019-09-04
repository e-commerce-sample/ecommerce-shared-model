package com.ecommerce.shared.event;

import com.ecommerce.shared.utils.DistributedLockExecutor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockConfiguration;

import java.time.Instant;
import java.util.List;


@Slf4j
public class DomainEventPublisher {
    private final DomainEventRecorder eventRecorder;
    private final DistributedLockExecutor lockExecutor;
    private final DomainEventSender sender;

    public DomainEventPublisher(DomainEventRecorder eventRecorder,
                                DistributedLockExecutor lockExecutor,
                                DomainEventSender sender) {
        this.eventRecorder = eventRecorder;
        this.lockExecutor = lockExecutor;
        this.sender = sender;
    }

    public void publish() {
        Instant now = Instant.now();
        LockConfiguration configuration = new LockConfiguration("domain-event-publisher", now.plusSeconds(60));
        lockExecutor.execute(this::doPublish, configuration);
    }

    private Void doPublish() {
        List<DomainEvent> newestEvents = eventRecorder.toBePublishedEvents();
        newestEvents.forEach(event -> {
            try {
                eventRecorder.increasePublishTry(event.getId());
                sender.send(event);
                log.debug("Published {}.", event);
                eventRecorder.markAsPublished(event.getId());
            } catch (Throwable t) {
                log.error("Error while publish domain event {}:{}", event, t.getMessage());
            }
        });
        return null;
    }

}


