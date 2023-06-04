package com.ayseozcan.annotation;

import org.springframework.stereotype.Component;

@Component
public class CustomerServiceAnnotation {

    INotification notification;
    public CustomerServiceAnnotation(INotification notification){
        this.notification = notification;
    }

    public void sendNotification(){
        notification.sendNotification();
    }
}
