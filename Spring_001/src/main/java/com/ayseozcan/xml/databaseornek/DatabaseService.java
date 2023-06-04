package com.ayseozcan.xml.databaseornek;

public class DatabaseService {
    IDatabase iDatabase;

    public DatabaseService(IDatabase iDatabase){
        this.iDatabase = iDatabase;
    }
    public void sendLog(){
        iDatabase.sendLog();
    }
}
