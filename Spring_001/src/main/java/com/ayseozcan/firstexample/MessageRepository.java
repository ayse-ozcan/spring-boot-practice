package com.ayseozcan.firstexample;

public class MessageRepository implements INotification{

    public void sendMessage() {
        System.out.println("message sent to customer");
    }

    @Override
    public void sendNotification() {
        System.out.println("message sent to customer");
    }
}
