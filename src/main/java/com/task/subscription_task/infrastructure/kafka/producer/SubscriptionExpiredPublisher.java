package com.task.subscription_task.infrastructure.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionExpiredPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.topics.output}")
    String outputTopic;

    public void send(List<String> logins) {
        List<CompletableFuture<SendResult<String, String>>> futures = logins.stream()
                .map(login -> kafkaTemplate.send(outputTopic, login, login))
                .toList();

        try {
            CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new))
                    .get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while sending evict events", e);
        } catch (TimeoutException | ExecutionException e) {
            throw new IllegalStateException("Kafka send failed", e);
        }
    }

}
