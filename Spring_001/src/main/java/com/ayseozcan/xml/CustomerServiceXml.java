package com.ayseozcan.xml;

public class CustomerServiceXml {

    INotification notification;
    public CustomerServiceXml(INotification notification){
        this.notification = notification;
    }

    public void sendNotification(){
        notification.sendNotification();
    }
}
