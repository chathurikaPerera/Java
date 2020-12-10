package com.admin.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.admin.usermanagement.bean.User;

public class UserDao {
	
	//dao used to create database connection and to write queries
	
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
	
	//insert query
	public void insertUser( User user) throws SQLException{
		System.out.println("INSERT_USER_SQL");
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)){
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(1, user.getCountry());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		}catch(SQLException e) {
			printSQLException(e);
		}
		
	}
	//select query
	
	public User selectUser(int id) {
		User user =null;
	 //established the connection
		try(Connection connection = getConnection();
				//prepared the statement using connection object 
				PreparedStatement preparedStatement =connection.prepareStatement(SELECT_USER_BY_ID);){
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			//execute the query
			ResultSet rs =preparedStatement.executeQuery();
			
			//process the data
			while(rs.next()) {
				String name =rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User (id, name, email,country);
			}
		
	}catch(SQLException e) {
		printSQLException(e);
		
	}
		return user;
}
	//return all users
	
	public List<User> selectAllUsers(){
		List<User> users = new ArrayList<>();
		//established the connection
		try(Connection connection = getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(SELECT_ALL_USER);){
			System.out.println(preparedstatement);
			//execute the preparedstatement
			ResultSet rs =preparedstatement.executeQuery();
			
			//process the result object
			while(rs.next()) {
				int id = rs.getInt("id");
				String name= rs.getString("name");
				String email = rs.getString("email");
				String country =rs.getString("country");
				users.add(new User(id, name, email,country));
				
			}
			
		}catch(SQLException e) {
			printSQLException(e);
			
		}
		
		return users;
	}
	//update query
	
	private boolean updateUse(User user) throws SQLException {
		boolean rowUpdated;
		//create the connection
		try(Connection connection = getConnection();
				PreparedStatement preparedstatement = connection.prepareStatement(UPDATE_USER_SQL);){
			System.out.println("update user" + preparedstatement);
			preparedstatement.setString(1, user.getName());
			preparedstatement.setString(2, user.getEmail());
			preparedstatement.setString(3, user.getCountry());
			preparedstatement.setInt(4, user.getId());
			
			rowUpdated = preparedstatement.executeUpdate()>0;
			
		}
		return rowUpdated;
	}
	//delete  query
	public boolean seleteUser(int id) throws SQLException{
		boolean rowDeleted;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
			statement.setInt(1,  id);
			rowDeleted = statement.executeUpdate() >0;
		}
		return rowDeleted;
	}

	private void printSQLException(SQLException ex) {
		// TODO Auto-generated method stub
		for(Throwable e:ex ) {
			e.printStackTrace(System.err);
			System.err.println("SQLState"+((SQLException)e).getSQLState());
			System.err.println("SQLException"+((SQLException)e).getErrorCode());
			System.err.println("Message"+e.getMessage());
			Throwable t = ex.getCause();
			while(t!=null) {
				System.out.println("Cause" + t);
				t =t.getCause();
			}
			
		}
		
	}
	
	 
    
	
	
	
}
