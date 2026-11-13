package com.task.subscription_task.web.controller;

import com.task.subscription_task.application.service.SubscriptionService;
import com.task.subscription_task.web.dto.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping("/{userLogin}")
    public SubscriptionResponse get(@PathVariable String userLogin) {
        return subscriptionService.getByLogin(userLogin);
    }

}
