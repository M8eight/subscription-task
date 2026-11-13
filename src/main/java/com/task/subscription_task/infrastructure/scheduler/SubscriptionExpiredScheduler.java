package com.task.subscription_task.infrastructure.scheduler;

import com.task.subscription_task.application.service.SubscriptionService;
import com.task.subscription_task.infrastructure.kafka.producer.SubscriptionExpiredPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SubscriptionExpiredScheduler {

    private final SubscriptionService subscriptionService;
    private final SubscriptionExpiredPublisher subscriptionExpiredPublisher;

    @Scheduled(cron = "0 0 * * * *")
    @SchedulerLock(name = "cancelExpiredSubscription",
                   lockAtMostFor = "PT10M",
                   lockAtLeastFor = "PT1M")
    public void cleanupProcessedMessages() {

        List<String> cancelUsers = subscriptionService.cancelSubscription();

        log.info("Cleanup processed messages {}", cancelUsers);

        subscriptionExpiredPublisher.send(cancelUsers);

    }

}
