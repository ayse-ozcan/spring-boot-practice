package com.ayseozcan.annotation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class MessageRepositoryAnnotation implements INotification {

    public void sendMessage() {
        System.out.println("message sent to customer");
    }

    @Override
    public void sendNotification() {
        System.out.println("message sent to customer");
    }
}
