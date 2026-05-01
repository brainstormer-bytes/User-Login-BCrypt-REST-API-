package com.projectmine.ECommerce.Web.repository;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class databaseConnection {
    Connection con = null;
    public Connection connect() {
        try {

            //Step1: Load the db.properties file from classPath
            InputStream input = databaseConnection.class.getClassLoader().getResourceAsStream("application.properties");

            //Step2: Read the properties
            Properties prop = new Properties();
            prop.load(input);

            //Step3: Register driver and connect
            Class.forName(prop.getProperty("spring.datasource.driverClassName"));
            con = DriverManager.getConnection(prop.getProperty("spring.datasource.url"),
                    prop.getProperty("spring.datasource.username"),
                    prop.getProperty("spring.datasource.password"));
        } catch(Exception e) { System.out.println(e.toString()); }
        return con;
    }
}
