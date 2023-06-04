package com.ayseozcan.xml;

public class MailRepository implements INotification {

    public void sendMail() {
        System.out.println("mail sent to customer");
    }

    @Override
    public void sendNotification() {
        System.out.println("mail sent to customer");
    }
}
