package com.ayseozcan.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testxml {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //CustomerServiceAnnotation customerServiceXml =
               // new CustomerServiceAnnotation(context.getBean("notification", MessageRepositoryAnnotation.class));
        //customerServiceXml.sendNotification();

        CustomerServiceXml customerServiceXml =
                new CustomerServiceXml(context.getBean("notification", INotification.class));
        customerServiceXml.sendNotification();
    }
}

