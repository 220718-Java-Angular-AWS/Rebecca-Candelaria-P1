package com.revature.connectionManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection connection;

    //private constructor for Singleton Design Pattern
    private ConnectionManager(){

    }

    public static Connection getConnection() {
        if (connection == null){
            //establish the connection
            connection = connect();
        }
        return connection;
    }

    private static Connection connect() {
        //connect to the database here
        //The connection string we want to build for postgres

        try {//How to add a jar into pom.xml file?
            //Add the ClassLoader and the InputStream to access jdbc.properties
            Properties props = new Properties();
            FileReader reader = new FileReader("src/main/resources/jdbc.properties");
            props.load(reader);  //props hold key value pairs after reading the file

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


            //The class that will establish the connection to the database
           connection = DriverManager.getConnection(builder.toString());


            //does not allow me to add a FileNotFoundException. Why?
        } catch (IOException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
