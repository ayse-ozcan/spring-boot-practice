package com.ayseozcan.xml.databaseornek;

public class PostgreRepository implements IDatabase{

    @Override
    public void sendLog() {
        System.out.println("postgre ye loglandi");
    }
}
