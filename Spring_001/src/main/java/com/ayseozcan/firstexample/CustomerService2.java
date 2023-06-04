package com.ayseozcan.firstexample;

public class CustomerService2{

    INotification notification;
    public CustomerService2(INotification notification){
        this.notification = notification;
    }

    public void sendNotification(){
        notification.sendNotification();
    }
}
