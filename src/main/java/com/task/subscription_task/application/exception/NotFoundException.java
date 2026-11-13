package com.task.subscription_task.application.exception;


import com.task.subscription_task.application.enums.ErrorCode;

public class NotFoundException extends BaseException {
    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
