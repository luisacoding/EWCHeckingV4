package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arun.app.AsnShifting;
import com.arun.app.ERPInventory;
import com.arun.app.WMSInventroy;
import com.arun.util.SelectOracle;
import com.arun.util.SelectSqlserver;

public class AsnShiftingServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		//response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		java.util.Date today = new java.util.Date();
		System.out.println("System Starting TIME： "+ today);
        // request from client 
		//String condition = "A";
		String condition =  URLDecoder.decode(request.getParameter("condition"),"utf-8");//request.getParameter("condition");
		System.out.println("request: " + condition);
		if(condition == null || condition.length() == 0)
			condition = "A3-2电子类区";
        /** SqlServer operation **/
		SelectOracle ssql = new SelectOracle();

		String sql = "select * from v_ASNShifting WHERE 库位定位分类='" + condition +"'" ;
		System.out.println(sql);
	
		List<AsnShifting> list = new ArrayList<AsnShifting>();
		ssql.connectoracle();
		ResultSet res = ssql.GetOraresult(sql);
		try {
			while (res.next()) {
				AsnShifting ERPasn = new AsnShifting();
				ERPasn.setLocation(res.getString("库位定位分类"));
				ERPasn.setERPnumber(res.getString("ERP单号"));
				ERPasn.setItemno(res.getString("物料编号"));
				ERPasn.setItemname(res.getString("物料名称"));
				ERPasn.setDescript(res.getString("规格"));
				ERPasn.setQty(res.getFloat("数量"));
				ERPasn.setQualifyTime(res.getString("质检登记时间"));
				list.add(ERPasn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ssql.closeOra();
		System.out.println("ORACLE asn ArrayList was added successfully.");  
		// print ArrayList 
		//int lineID = 0;
		//for (int i = 0; i < list.size(); i++) {
		//	lineID++;
		//	String[] str = list.get(i);
		//	System.out.println(lineID + ": "  + str[0] + " " + str[1]);
		//}
		
		/** convert to JSON(JavaScript Object Notation) format */
       StringBuilder json = new StringBuilder();
        String s="{" + "\"total\"" + ":100" + ","+"\"rows\"" +":" +"[";
        json.append(s);
        for(int i=0; i < list.size(); i++){
        	AsnShifting jsonList = list.get(i);
	        String s1="{" + "\"location\"" + ":" +"\"" + jsonList.getLocation()+  "\"" 
	        		+ "," + "\"ERPnumber\"" + ":" +"\"" + jsonList.getERPnumber()+  "\""
	        		+ "," + "\"itemno\"" + ":" +"\"" + jsonList.getItemno()+  "\""
	        		+ "," + "\"itemname\"" + ":" +"\"" + jsonList.getItemname()+  "\""
	        		+ "," + "\"descript\"" + ":" +"\"" + jsonList.getDescript()+  "\""
	        		+ "," + "\"qty\"" + ":" +"\"" + jsonList.getQty()+  "\""
	        		+ "," + "\"qualifyTime\"" + ":" +"\"" + jsonList.getQualifyTime()+  "\""
	        		+ "}";
	        json.append(s1);
	        if(i < list.size()-1) {
		        String comma=",";
		        json.append(comma);
	        }
        }
        String s3="]}"; 
        json.append(s3); 
        System.out.println("Printing json started:");
        System.out.println(json);
        System.out.println("Printing json ended.");
        out.println(json);	
	}
	
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		doGet(request,response);
	}
}
