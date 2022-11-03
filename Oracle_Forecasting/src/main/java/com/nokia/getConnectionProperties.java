package com.nokia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class getConnectionProperties {
        public String getSourceDbconfig (String request) {
            String result = null;
            if (request == "url") {
                return "jdbc:oracle:thin:@93.183.24.223:1521:thortest";
            } else if (request == "dbuser") {
                return "dschepkin";
            } else if (request == "password") {
                return "vineviten";
            }
            return result;
        }

        public String getTargetDbconfig (String request) {
        String result = null;
            if (request == "url") {
                return "jdbc:oracle:thin:@93.183.24.223:1521:thortest";
            } else if (request == "dbuser") {
                return "dschepkin";
            } else if (request == "password") {
                return "vineviten";
            }
            return result;
    }


        public void setSourceDbConfig () {

            Properties prop = new Properties();
            OutputStream output = null;

            try {
                output = new FileOutputStream("src/main/resources/sourceDbConfig.properties");

                // set the properties value
                prop.setProperty("database", "93.183.24.223");
                prop.setProperty("port", "1521");
                prop.setProperty("dbuser", "dschepkin");
                prop.setProperty("dbpassword", "vineviten");

                // save properties to project root folder
                prop.store(output, null);

            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        public void setTargetDbConfig () {

        Properties prop = new Properties();
        OutputStream output = null;

        try {
            output = new FileOutputStream("src/main/resources/targetDbConfig.properties");

            // set the properties value
            prop.setProperty("database", "93.183.24.223");
            prop.setProperty("port", "1521");
            prop.setProperty("dbuser", "dschepkin");
            prop.setProperty("dbpassword", "vineviten");

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}


