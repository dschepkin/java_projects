package com.nokia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class PullData {
    public static void main(String args[]) throws SQLException {
        Logger logger = LoggerFactory.getLogger(ConnectionProperties.class);

        ConnectionProperties properties = new ConnectionProperties();

        String sourceUrl =        properties.getSourceDbconfig("url");
        String sourceDbUser =     properties.getSourceDbconfig("dbuser");
        String sourceDbPassword = properties.getSourceDbconfig("password");

        String targetUrl =        properties.getTargetDbconfig("url");
        String targetDbUser =     properties.getTargetDbconfig("dbuser");
        String targetDbPassword = properties.getTargetDbconfig("password");

        try {
        //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
                logger.error("Where is your Oracle JDBC Driver ?");
                e.printStackTrace();
                return;
            }

        logger.debug("Oracle JDBC Driver Registered!");

        Connection connectionSourceDb = null;
        Connection connectionTargetDb = null;

        try {
            //step2 create  the connection object to sourceDB
            connectionSourceDb = DriverManager.getConnection(
                    sourceUrl, sourceDbUser, sourceDbPassword);
        } catch (SQLException e) {
            logger.error("Connection to source DB failed! Check output console!");
            e.printStackTrace();
            return;
        }

        try {
            //step2 create  the connection object to targetDB
            connectionTargetDb = DriverManager.getConnection(
                    targetUrl, targetDbUser, targetDbPassword);
            connectionTargetDb.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("Connection to target DB failed! Check output console!");
            e.printStackTrace();
            return;
        }

        if (connectionSourceDb != null) {
            logger.debug("Connection is ok!");
        } else {
            logger.error("Failed to make Connection!");
        }

        String sqlSelect00 = "select sysdate -5/24/60 as BEGIN_TIME, \n" +
                "       sysdate as END_TIME,\n" +
                "       dschepkin.forecast_perf_SEQ01.NEXTVAL as SAMPLE\n" +
                "       from dual";
        String sqlSelect01 = "SELECT \n" +
                "      CASE METRIC_NAME\n" +
                "            WHEN 'Average Active Sessions' then 'Average_Active_Sessions'\n" +
                "            WHEN 'Database Time Per Sec' then 'DB_Time_sec'\n" +
                "            WHEN 'Response Time Per Txn' then 'Response_Time_Txn_sec'\n" +
                "            WHEN 'I/O Requests per Second' then 'Total_IOps'\n" +
                "            WHEN 'Physical Read IO Requests Per Sec' then 'Physical_Read_IO_sec'\n" +
                "            WHEN 'Physical Write IO Requests Per Sec' then 'Physical_Write_IO_sec'\n" +
                "            WHEN 'I/O Megabytes per Second' then 'IO_MB_sec'\n" +
                "            WHEN 'Physical Read Bytes Per Sec' then 'Physical_Read_Bytes_sec'\n" +
                "            WHEN 'Physical Write Bytes Per Sec' then 'Physical_Write_Bytes_sec'\n" +
                "            WHEN 'Hard Parse Count Per Sec' then 'Hard_Parse_Count_sec'\n" +
                "            WHEN 'Logical Reads Per Sec' then 'Logical_Reads_sec'\n" +
                "            WHEN 'DB Block Gets Per Sec' then 'DB_Block_Gets_sec'\n" +
                "            WHEN 'DB Block Changes Per Sec' then 'DB_Block_Changes_Per_Sec'\n" +
                "            WHEN 'Redo Generated Per Sec' then 'Redo_Generated_Bytes_sec'\n" +
                "            WHEN 'Logons Per Sec' then 'Logons_sec'\n" +
                "            WHEN 'User Transaction Per Sec' then 'User_Transaction_sec'\n" +
                "            WHEN 'User Calls Per Sec' then 'User_Calls_sec'\n" +
                "            WHEN 'Executions Per Sec' then 'Executions_sec'\n" +
                "            WHEN 'User Commits Per Sec' then 'User_Commits_sec'\n" +
                "            WHEN 'User Rollbacks Per Sec' then 'User_Rollbacks_sec'\n" +
                "            WHEN 'Recursive Calls Per Sec' then 'Recursive_Calls_sec'\n" +
                "            WHEN 'Total Table Scans Per Sec' then 'Total_Table_Scans_sec'\n" +
                "            WHEN 'Full Index Scans Per Sec' then 'Full_Index_Scans_sec'\n" +
                "            ELSE METRIC_NAME\n" +
                "            END METRIC_NAME,\n" +
                "       CASE METRIC_NAME\n" +
                "            WHEN 'Database Time Per Sec' then ROUND(((SUM(VALUE) / 5) / 100),3)\n" +
                "            ELSE ROUND((SUM(VALUE) / 5),3)\n" +
                "            END VALUE\n" +
                "from V$SYSMETRIC_HISTORY\n" +
                "where group_id = 2\n" +
                "and  metric_name IN (\n" +
                "                    'Average Active Sessions',\n" +
                "                    'Database Time Per Sec',      --(CentiSeconds Per Second)\n" +
                "                    'Response Time Per Txn',\n" +
                "                    'I/O Requests per Second',\n" +
                "                    'Physical Read IO Requests Per Sec',\n" +
                "                    'Physical Write IO Requests Per Sec',\n" +
                "                    'I/O Megabytes per Second',\n" +
                "                    'Physical Read Bytes Per Sec', --Bytes Per Second\n" +
                "                    'Physical Write Bytes Per Sec', --Bytes Per Second\n" +
                "                    'Hard Parse Count Per Sec',\n" +
                "                    'Logical Reads Per Sec',\n" +
                "                    'DB Block Gets Per Sec',\n" +
                "                    'DB Block Changes Per Sec',\n" +
                "                    'Redo Generated Per Sec', --Bytes Per Second\n" +
                "                    'Logons Per Sec',\n" +
                "                    'User Transaction Per Sec',\n" +
                "                    'User Calls Per Sec',\n" +
                "                    'Executions Per Sec',\n" +
                "                    'User Commits Per Sec',\n" +
                "                    'User Rollbacks Per Sec',\n" +
                "                    'Recursive Calls Per Sec',\n" +
                "                    'Total Table Scans Per Sec',\n" +
                "                    'Full Index Scans Per Sec'\n" +
                "                    )\n" +
                "and end_time > sysdate - 5/24/60\n" +
                "group by metric_name\n";

        try {
            //create statement object for. Return only 2 value - 'END_TIME', 'BEGIN_TIME'
            Statement select00 = connectionSourceDb.createStatement();

            //step3 create the statement object for select data from source DB
            Statement select01 = connectionSourceDb.createStatement();

            ResultSet rs00Select00 = select00.executeQuery(sqlSelect00);
            ResultSet rs01Select01 = select01.executeQuery(sqlSelect01);

            String sqlInsert00 = "insert INTO dschepkin.forecast_perf (END_TIME, BEGIN_TIME, SAMPLE, METRIC_NAME, VALUE) VALUES (?,?,?,?,?)";
            PreparedStatement preparedSqlInsert00 = connectionTargetDb.prepareStatement(sqlInsert00);

            rs00Select00.next();
            logger.debug(rs00Select00.getDate("BEGIN_TIME") + " " + rs00Select00.getDate("END_TIME") + " " + rs00Select00.getInt("SAMPLE"));

            while (rs01Select01.next()) {
                logger.debug("Add to batch - " + "sample: " + rs00Select00.getInt("SAMPLE") + "Metric_name: " + rs01Select01.getString("metric_name") + " value: " + rs01Select01.getFloat("value"));

                preparedSqlInsert00.setDate(1,rs00Select00.getDate("END_TIME"));
                preparedSqlInsert00.setDate(2,rs00Select00.getDate("BEGIN_TIME"));
                preparedSqlInsert00.setInt(3,rs00Select00.getInt("SAMPLE"));
                preparedSqlInsert00.setString(4,rs01Select01.getString("METRIC_NAME"));
                preparedSqlInsert00.setFloat(5,rs01Select01.getFloat("VALUE"));

                preparedSqlInsert00.addBatch();
            }

            int[] recordsAffected = preparedSqlInsert00.executeBatch();
            System.out.println("Insert records affected" + recordsAffected);

            connectionTargetDb.commit();
            logger.debug("Commit successfully");

        } catch (SQLException e) {
            logger.error("Failed execution SQL");
            connectionTargetDb.rollback();
            e.printStackTrace();
        } finally {
            try {
                if (connectionSourceDb != null) connectionSourceDb.close(); //step5 close the connection object
                if (connectionTargetDb != null) connectionSourceDb.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
