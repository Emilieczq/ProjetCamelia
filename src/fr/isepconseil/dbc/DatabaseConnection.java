package fr.isepconseil.dbc;

import java.sql.Connection;  
import java.sql.DriverManager; 

public class DatabaseConnection {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; // need to download MySQL JDBC jar
    static final String DB_URL = "jdbc:mysql://localhost:3306/Camelia?autoReconnect=true&useSSL=false"; // correct the project name

    static final String USER = "root"; // your server user name
    static final String PASSWORD = "root"; // your password
    private Connection connection=null; 
    
	public DatabaseConnection() throws Exception{  
        try{  
        		Class.forName("com.mysql.jdbc.Driver");
        		connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        		System.out.println("Connected successfully");
        }catch(Exception exception ){  
            throw exception;  
        } finally {  
        }     
    }  
    public Connection getConnection(){  
        return connection;  
    }  
    public void close() throws Exception{  
        if(connection!=null){  
            try {  
                connection.close();           
            } catch (Exception e) {  
                throw e;  
            }  
        }  
    }  
}
