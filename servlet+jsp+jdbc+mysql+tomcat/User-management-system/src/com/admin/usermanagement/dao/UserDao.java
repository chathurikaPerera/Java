package com.admin.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserDao {
	
	//database connection
	private String jdbcURL = "jbdc:mysql//localhost:3306/register?useSSL=false";
	private String jdbcUsername ="root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jbdc.Driver";
	
	//store data in the database
	private static final String INSERT_USER_SQL = "INSERT INTO user" + " (name,email,country) VALUES"
	                    + "(?, ?, ?,);";
	//deleteand select query
	private static final String SELECT_USER_BY_ID = "select * from user where id= ? ";
	private static final String SELECT_ALL_USER = "select * from user";
	private static final String DELETE_USERS_SQL = "delete from user id=?;";
	private static final String UPDATE_USER_SQL = "update users set name = ?, email = ?, country =? where id = ?;";
	
	
	public UserDao() {
		
	}
	
	protected Connection getConnection() {
		
		Connection connection =null;
		try {
			Class.forName("jdbcDriver");
			connection =DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			
		}
		 
		return connection;
	}
	
	//dao used to create database connection and to write queries 
    
	
	
	
}
