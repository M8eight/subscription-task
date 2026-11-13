package com.task.subscription_task.infrastructure.persistence;

import com.task.subscription_task.application.enums.SubscriptionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user_subscriptions")
@EntityListeners(AuditingEntityListener.class)
public class UserSubscription {

    @Id
    private String userLogin;

    @Enumerated(EnumType.STRING)
    private SubscriptionStatus subscriptionStatus;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    private Instant subscriptionExpiresAt;

}
