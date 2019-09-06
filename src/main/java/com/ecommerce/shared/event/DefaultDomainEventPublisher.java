package com.ecommerce.shared.event;

import com.ecommerce.shared.utils.DistributedLockExecutor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockConfiguration;

import java.time.Instant;
import java.util.List;


@Slf4j
public class DefaultDomainEventPublisher implements DomainEventPublisher {
    private final DomainEventDao eventDao;
    private final DistributedLockExecutor lockExecutor;
    private final DomainEventSender sender;

    public DefaultDomainEventPublisher(DomainEventDao eventDao,
                                       DistributedLockExecutor lockExecutor,
                                       DomainEventSender sender) {
        this.eventDao = eventDao;
        this.lockExecutor = lockExecutor;
        this.sender = sender;
    }

    public void publishNextBatch(int size, boolean containsPreviousFailed) {
        Instant now = Instant.now();
        LockConfiguration configuration = new LockConfiguration("default-domain-event-publisher", now.plusSeconds(60));
        lockExecutor.execute(() -> doPublish(size, containsPreviousFailed), configuration);
    }

    private Void doPublish(int size, boolean containsPreviousFailed) {
        List<DomainEvent> newestEvents = eventDao.nextPublishBatch(size, containsPreviousFailed);
        newestEvents.forEach(event -> {
            try {
                sender.send(event);
                log.debug("Published {}.", event);
                eventDao.markAsPublished(event.getId());
            } catch (Throwable t) {
                eventDao.markAsPublishFailed(event.getId());
                log.error("Error while publish domain event {}.", event, t);
            }
        });
        return null;
    }

    @Override
    public void forcePublish(String eventId) {
        try {
            DomainEvent event = eventDao.get(eventId);
            sender.send(event);
            eventDao.markAsPublished(eventId);
        } catch (Throwable t) {
            eventDao.markAsPublishFailed(eventId);
            log.error("Error while force publish domain event {}.", eventId, t);
        }
    }
}


