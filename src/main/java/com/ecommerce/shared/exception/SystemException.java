package com.ecommerce.shared.exception;


import static com.google.common.collect.ImmutableMap.of;

public class SystemException extends AppException {

    public SystemException(Throwable cause) {
        super(CommonErrorCode.SYSTEM_ERROR, of("detail", cause.getMessage()), cause);
    }
}
