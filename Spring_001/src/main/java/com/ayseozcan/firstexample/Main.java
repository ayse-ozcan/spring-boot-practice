package com.ayseozcan.firstexample;

public class Main {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        customerService.sendMessage();

        CustomerService customerService1 = new CustomerService(new MailRepository());
        customerService1.sendMail();

        CustomerService2 customerService2 = new CustomerService2(new MessageRepository());
        customerService2.sendNotification();

    }
}