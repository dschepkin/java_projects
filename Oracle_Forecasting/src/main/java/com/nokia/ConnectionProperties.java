package com.nokia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class ConnectionProperties {
    Logger logger = LoggerFactory.getLogger(ConnectionProperties.class);

    public String getSourceDbconfig(String request) {
        Properties properties01 = new Properties();

        try {
            FileInputStream inputToFile = new FileInputStream("src/main/resources/sourceDbConfig.properties");
            properties01.load(inputToFile);
        } catch (IOException e) {
            logger.error("file sourceDbConfig.properties not found");
            e.printStackTrace();
        }

        String result = null;
        if (request == "url") {
            return properties01.getProperty("url");
        } else if (request == "dbuser") {
            return properties01.getProperty("dbuser");
        } else if (request == "password") {
            return properties01.getProperty("dbpassword");
        }
        return result;
    }

    public String getTargetDbconfig(String request) {
        Properties properties02 = new Properties();

        try {
            FileInputStream inputToFile = new FileInputStream("src/main/resources/targetDbConfig.properties");
            properties02.load(inputToFile);
        } catch (IOException e) {
            logger.error("file targetDbConfig.properties not found");
            e.printStackTrace();
        }

        String result = null;
        if (request == "url") {
            return properties02.getProperty("url");
        } else if (request == "dbuser") {
            return properties02.getProperty("dbuser");
        } else if (request == "password") {
            return properties02.getProperty("dbpassword");
        }
        return result;
    }
}
