package com.task.subscription_task.infrastructure.persistence;

import com.task.subscription_task.application.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, String> {

    List<UserSubscription> findBySubscriptionStatusAndSubscriptionExpiresAtBefore(
            SubscriptionStatus status, Instant now);

}
