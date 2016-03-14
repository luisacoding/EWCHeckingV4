package com.arun.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SelectOracle {
	public static Connection conn;
	private Statement stmt = null;
    private ResultSet result = null;
    DB db=new DB();
	public  void connectoracle() {
		db.setDriverName("oracle.jdbc.driver.OracleDriver");
		db.setDbURL("jdbc:oracle:" + "thin:@192.168.0.123:1521/ORCL");
		db.setUserName("hltwms");
		db.setUserPwd("hltwms");
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
	public ResultSet GetOraresult(String sql){
		try {
			result=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public void closeOra(){
		db.closeConnection();
	}
}
