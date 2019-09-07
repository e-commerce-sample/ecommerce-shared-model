package com.ecommerce.shared.utils;

import com.ecommerce.shared.exception.LockAlreadyOccupiedException;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockConfiguration;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.core.SimpleLock;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.function.Supplier;

// Used directly by client code to run with distributed lock
@Slf4j
public class DistributedLockExecutor {
    private static final Duration MAX_RUN_TIME = Duration.ofMinutes(5);

    private final LockProvider lockProvider;

    public DistributedLockExecutor(LockProvider lockProvider) {
        this.lockProvider = lockProvider;
    }

    public <T> T execute(Supplier<T> supplier, LockConfiguration configuration) {
        Optional<SimpleLock> lock = lockProvider.lock(configuration);
        if (!lock.isPresent()) {
            throw new LockAlreadyOccupiedException(configuration.getName());
        }

        log.debug("Obtained lock {}.", configuration.getName());

        try {
            return supplier.get();
        } finally {
            lock.get().unlock();
            log.debug("Released lock {}.", configuration.getName());
        }
    }

    public <T> T execute(Supplier<T> supplier, String lockKey) {
        LockConfiguration lockConfiguration = new LockConfiguration(lockKey, Instant.now().plus(MAX_RUN_TIME));
        return this.execute(supplier, lockConfiguration);

    }


}
