package com.task.subscription_task.application.service;

import com.task.subscription_task.application.enums.ErrorCode;
import com.task.subscription_task.application.enums.SubscriptionStatus;
import com.task.subscription_task.application.exception.NotFoundException;
import com.task.subscription_task.infrastructure.persistence.UserSubscription;
import com.task.subscription_task.infrastructure.persistence.UserSubscriptionRepository;
import com.task.subscription_task.web.dto.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final UserSubscriptionRepository userSubscriptionRepository;

    @Transactional(readOnly = true)
    public SubscriptionResponse getByLogin(String login) {
        return userSubscriptionRepository.findById(login)
                .map(this::toDto)
                .orElseThrow(() -> new NotFoundException("Subscription with login %s not found".formatted(login),
                        ErrorCode.SUBSCRIPTION_NOT_FOUND));
    }

    private SubscriptionResponse toDto(UserSubscription userSubscription) {
        return SubscriptionResponse.builder()
                .login(userSubscription.getUserLogin())
                .status(userSubscription.getSubscriptionStatus())
                .expiresAt(userSubscription.getSubscriptionExpiresAt())
                .build();
    }

    @Transactional
    public List<String> cancelSubscription() {
        List<UserSubscription> subs = userSubscriptionRepository.findBySubscriptionStatusAndSubscriptionExpiresAtBefore(
                SubscriptionStatus.PAID, Instant.now()
        );

        subs.forEach(sub -> {
            sub.setSubscriptionStatus(SubscriptionStatus.FREE);
            sub.setSubscriptionExpiresAt(null);
        });

        return subs.stream().map(UserSubscription::getUserLogin).toList();
    }

}
