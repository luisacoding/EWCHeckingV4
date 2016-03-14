package com.arun.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SelectSqlserver {
	public static Connection conn;
	private Statement stmt = null;
    private ResultSet result = null;
    DB db=new DB();
	public  void connectSSql() {
		
		db.setDriverName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		db.setDbURL("jdbc:sqlserver://192.168.0.122; DatabaseName=NG0006");
		db.setUserName("searchInfo");
		db.setUserPwd("administrator");
		try {
			conn=db.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public ResultSet GetSSqlresult(String sql){
		try {
			result=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public void closeSSql(){
		db.closeConnection();
	}
}
