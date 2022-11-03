package dschepkin.com;

import java.sql.*;

public class SqlSimple {
    public static void main(String[] args) {
        SqlSimple sqlSimple = new SqlSimple();
        sqlSimple.getSqlOutput();
    }

    String dbUrl = "jdbc:oracle:thin:@93.183.24.223:1521:thortest";
    String dbuser = "dschepkin";
    String dbpassword = "vineviten";

    public void getSqlOutput() {
        try {
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            //logger.error("Where is your Oracle JDBC Driver ?");
            e.printStackTrace();
            return;
        }

        Connection connectionDB = null;

        try {
            //step2 create  the connection object to sourceDB
            connectionDB = DriverManager.getConnection(dbUrl, dbuser, dbpassword);
            connectionDB.setAutoCommit(false);
        } catch (SQLException e) {
            //logger.error("Connection to source DB failed! Check output console!");
            e.printStackTrace();
            return;
        }

/*        if (connectionDB != null) {
            logger.debug("Connection is ok!");
        } else {
            logger.error("Failed to make Connection!");
        }*/

        String sqlSelect01 = "select * from scott.dept";

        try {
            Statement select01 = connectionDB.createStatement();
            ResultSet rs01select01 = select01.executeQuery(sqlSelect01);

            int deptnoIndex = rs01select01.findColumn("deptno");
            int dnameIndex = rs01select01.findColumn("dname");
            int locIndex = rs01select01.findColumn("loc");

            while (rs01select01.next()) {
                int     deptno  = rs01select01.getInt(deptnoIndex);
                String  dname   = rs01select01.getString(dnameIndex);
                String  loc = rs01select01.getString(locIndex);
                System.out.println(deptno + " " + dname + " " + loc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connectionDB != null) connectionDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
