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

import static java.util.concurrent.TimeUnit.MILLISECONDS;

// Used directly by client code to run with distributed lock
@Slf4j
public class DistributedLockExecutor {
    private static final Duration MAX_RUN_TIME = Duration.ofMinutes(5);

    private final LockProvider lockProvider;

    public DistributedLockExecutor(LockProvider lockProvider) {
        this.lockProvider = lockProvider;
    }

    public <T> T tryExecute(Supplier<T> supplier, LockConfiguration configuration) {
        Optional<SimpleLock> lock = lockProvider.lock(configuration);
        if (!lock.isPresent()) {
            throw new LockAlreadyOccupiedException(configuration.getName());
        }

        try {
            return supplier.get();
        } finally {
            lock.get().unlock();
        }
    }

    public <T> T tryExecute(Supplier<T> supplier, String lockKey) {
        LockConfiguration lockConfiguration = new LockConfiguration(lockKey, Instant.now().plus(MAX_RUN_TIME));
        return this.tryExecute(supplier, lockConfiguration);

    }

    public <T> T execute(Supplier<T> supplier, LockConfiguration configuration) {
        String lockName = configuration.getName();
        Optional<SimpleLock> lock;
        while (true) {
            lock = lockProvider.lock(configuration);
            if (lock.isPresent()) {
                log.debug("Obtained lock {}.", lockName);
                break;
            }
            try {
                log.debug("Failed to obtain lock {}. ", lockName);
                MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            log.debug("Lock and execute {}.", lockName);
            return supplier.get();
        } finally {
            log.debug("Release lock {}.", lockName);
            lock.get().unlock();
        }
    }

    public <T> T execute(Supplier<T> supplier, String lockKey) {
        LockConfiguration lockConfiguration = new LockConfiguration(lockKey, Instant.now().plus(MAX_RUN_TIME));
        return this.execute(supplier, lockConfiguration);
    }


}
