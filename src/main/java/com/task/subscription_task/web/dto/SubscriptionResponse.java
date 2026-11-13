package com.task.subscription_task.web.dto;

import com.task.subscription_task.application.enums.SubscriptionStatus;
import lombok.Builder;

import java.time.Instant;

@Builder
public record SubscriptionResponse(
        String login,
        SubscriptionStatus status,
        Instant expiresAt
) {
}
