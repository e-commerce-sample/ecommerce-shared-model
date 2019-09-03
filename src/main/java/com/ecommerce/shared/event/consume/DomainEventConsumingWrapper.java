package com.ecommerce.shared.event.consume;

import com.ecommerce.shared.event.DomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.Optional;

@Slf4j
public class DomainEventConsumingWrapper {

    private DomainEventConsumingRecorder recorder;

    public DomainEventConsumingWrapper(DomainEventConsumingRecorder recorder) {
        this.recorder = recorder;
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
        if (!recorder.record(event)) {
            log.warn("Duplicated {} skipped.", event);
            return null;
        }

        return joinPoint.proceed();


    }
}
