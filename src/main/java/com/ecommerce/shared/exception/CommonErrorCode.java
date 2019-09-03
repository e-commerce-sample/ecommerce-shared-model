package com.ecommerce.shared.exception;

public enum CommonErrorCode implements ErrorCode {
    LOCK_OCCUPIED(409, "任务已被锁定，请稍后重试"),
    SYSTEM_ERROR(500, "系统错误");
    private int status;
    private String message;

    CommonErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
    }
