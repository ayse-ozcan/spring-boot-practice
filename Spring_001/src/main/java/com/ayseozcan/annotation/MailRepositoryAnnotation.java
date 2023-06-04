package com.ayseozcan.annotation;

import org.springframework.stereotype.Component;

@Component
public class MailRepositoryAnnotation implements INotification {

    public void sendMail() {
        System.out.println("mail sent to customer");
    }

    @Override
    public void sendNotification() {
        System.out.println("mail sent to customer");
    }
}
