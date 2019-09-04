package com.ecommerce.shared.event;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class DomainEventConsumingWrapper {

    private DomainEventRecorder eventRecorder;

    public DomainEventConsumingWrapper(DomainEventRecorder eventRecorder) {
        this.eventRecorder = eventRecorder;
    }

    public Object recordAndConsume(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Optional<Object> optionalEvent = Arrays.stream(args)
                .filter(o -> o instanceof DomainEvent)
                .findFirst();

        if (!optionalEvent.isPresent()) {
            return joinPoint.proceed();
        }

        DomainEvent event = (DomainEvent) optionalEvent.get();
        if (!eventRecorder.recordForConsuming(event)) {
            log.warn("Duplicated {} skipped.", event);
            return null;
        }

        return joinPoint.proceed();

    }
}
