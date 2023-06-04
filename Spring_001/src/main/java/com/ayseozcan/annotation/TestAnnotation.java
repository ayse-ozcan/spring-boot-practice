package com.ayseozcan.annotation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotation {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        CustomerServiceAnnotation customerServiceAnnotation = context.getBean(CustomerServiceAnnotation.class);
        customerServiceAnnotation.sendNotification();
    }
}
