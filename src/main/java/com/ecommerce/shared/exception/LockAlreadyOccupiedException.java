package com.ecommerce.shared.exception;

import static com.google.common.collect.ImmutableMap.of;

public class LockAlreadyOccupiedException extends AppException {
    public LockAlreadyOccupiedException(String lockKey) {
        super(CommonErrorCode.LOCK_OCCUPIED, of("lockKey", lockKey));
    }
}
