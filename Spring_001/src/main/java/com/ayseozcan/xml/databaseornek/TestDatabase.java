package com.ayseozcan.xml.databaseornek;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDatabase {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        DatabaseService databaseService = new DatabaseService(context.getBean("database",IDatabase.class));
        databaseService.sendLog();

        DatabaseService databaseService1 = context.getBean("dbservice",DatabaseService.class);
        databaseService1.sendLog();
    }
}
