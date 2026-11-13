package com.task.subscription_task.application.exception;

import com.task.subscription_task.application.enums.ErrorCode;

import java.time.Instant;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String message,
        ErrorCode code,
        String path
) {
}
