package com.task.subscription_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class SubscriptionTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubscriptionTaskApplication.class, args);
	}

}
