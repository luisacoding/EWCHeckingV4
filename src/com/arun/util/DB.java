package com.arun.util;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
	public Connection conn;
	public static String driverName;
	public static String dbURL;
	public static String userName; 
	public static String userPwd;
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDbURL() {
		return dbURL;
	}

	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}


	public  Connection getConnection() throws SQLException, ClassNotFoundException{

			Class.forName(driverName);
			conn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println(conn+"连接成功");

		return conn;
	}
	public  void closeConnection(){
		try{
			if(conn!=null){
				conn.close();
				System.out.println(conn+"断开连接");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
