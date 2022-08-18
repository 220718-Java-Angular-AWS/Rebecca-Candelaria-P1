package com.revature.services;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSourceService {
    //Persistence Layer
    private static Connection connection;

        private DataSourceService(){

        }
    public static Connection getConnection() {
        if (connection == null){
            //establish the connection
           connect();
        }
        return connection;
    }

    private static void connect() {
        //connect to the database here
        //The connection string we want to build for postgres

        try {//How to add a jar into pom.xml file?
            //Add the ClassLoader and the InputStream to access jdbc.properties
            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("jdbc.properties");
            props.load(input);  //props hold key value pairs after reading the file

            String host = props.getProperty("host");
            String port = props.getProperty("port");
            String driver = props.getProperty("driver");
            String username = props.getProperty("username");
            String password = props.getProperty("password");
            String dbname = props.getProperty("dbname");

            StringBuilder builder = new StringBuilder("jdbc:postgresql://");
            builder.append(host);
            builder.append(":");
            builder.append(port);
            builder.append("/");
            builder.append(dbname);
            builder.append("?user=");
            builder.append(username);
            builder.append("&password=");
            builder.append(password);
            builder.append("&currentSchema=public");
            Class.forName(driver);


            //The class that will establish the connection to the database
            connection = DriverManager.getConnection(builder.toString());

            System.out.println("Datasource Initialized...");

            //does not allow me to add a FileNotFoundException. Why?
        } catch (IOException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
