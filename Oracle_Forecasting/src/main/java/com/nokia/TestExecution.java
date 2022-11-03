package com.nokia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestExecution {
    public static void main(String[] args) {
        System.out.println(TestExecution.url);
        System.out.println(TestExecution.dbuser);
        System.out.println(TestExecution.dbpassword);
    }

    static String url;
    static String dbuser;
    static String dbpassword;

    static {
        Properties properties00 = new Properties();
        try {
            FileInputStream inToFile = new FileInputStream("src/main/resources/sourceDbConfig.properties");
            properties00.load(inToFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = properties00.getProperty("url");
        dbuser = properties00.getProperty("dbuser");
        dbpassword = properties00.getProperty("dbpassword");
    }
}


