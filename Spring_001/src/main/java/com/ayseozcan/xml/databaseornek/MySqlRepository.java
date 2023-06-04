package com.ayseozcan.xml.databaseornek;

public class MySqlRepository implements IDatabase{

    @Override
    public void sendLog() {
        System.out.println("mysql e loglandi");
    }
}
