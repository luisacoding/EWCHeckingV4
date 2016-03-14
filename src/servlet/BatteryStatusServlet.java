package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.arun.app.*;
import com.arun.util.SelectOracle;
import com.arun.util.SelectSqlserver;

public class BatteryStatusServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
	//public static void main(String[] args) {	
		PrintWriter out = response.getWriter();
		//java.util.Date today = new java.util.Date();
        

		/** Oracle operation **/
		SelectOracle orasql = new SelectOracle();
		String sql=" SELECT * FROM ViewBatteryStatusas ORDER BY code";
		System.out.println(sql);
		//int count = 0;
		List<Battery> list = new ArrayList<Battery>();
		orasql.connectoracle();
		ResultSet res = orasql.GetOraresult(sql);
		try {
			while (res.next()) {
				//count++;
				Battery battery = new Battery();
				battery.setAllocatedqty(res.getFloat("allqty"));
				battery.setClassThree(res.getString("class3"));
				battery.setDescription(res.getString("description"));
				battery.setItemname(res.getString("name"));
				battery.setItemno(res.getString("code"));
				battery.setMountqty(res.getFloat("putqty"));
				battery.setQty(res.getFloat("qty"));
				battery.setStatus(res.getString("status"));
				battery.setUnit(res.getString("unit"));
				list.add(battery);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		orasql.closeOra();
		System.out.println("WMS PurchaseOrder ArrayList was added successfully.");
		
		/** convert to json format */
		System.out.println("Processing json format.......");
		java.util.Date datejson1 = new java.util.Date();
		System.out.println("json formatting Started Time: " +datejson1);
		
        StringBuilder json = new StringBuilder();
        String s="{" + "\"total\"" + ":100" + ","+"\"rows\"" +":" +"[";
        json.append(s);
        int lineid = 0, lineno = 0;
        for(int i=0; i < list.size(); i++){
        	lineno = 0;
        	lineid = lineid + 1;
        	lineno = lineid;
        	/*if (lineid == 2)
        		lineno = lineid -1;
        	if (lineid % 2 == 0 && lineid > 2) {
        		lineno =lineid / 2 -1;
        	}
        	if (lineid % 2 != 0 && lineid > 2) {
        		lineno =(lineid+1) / 2 - 1;
        	} */
        	Battery jsonList = list.get(i);
	        String s1="{" + "\"lineno\"" + ":" +"\"" + lineid +  "\"" 
	        			+ "," + "\"classThree\"" + ":" +"\"" + jsonList.getClassThree()+  "\"" 
	        			+ "," + "\"status\"" + ":" +"\"" + jsonList.getStatus()+  "\"" 
	        			+ "," + "\"qty\"" + ":" +"\"" + jsonList.getQty()+  "\"" 
	        			+ "," + "\"allqty\"" + ":" +"\"" + jsonList.getAllocatedqty()+  "\"" 
	        			+ "," + "\"putqty\"" + ":" +"\"" + jsonList.getMountqty()+  "\"" 
	        			+ "," + "\"name\"" + ":" +"\"" + jsonList.getItemname()+  "\"" 
	        			+ "," + "\"description\"" + ":" +"\"" + jsonList.getDescription()+  "\"" 
	        			+ "," + "\"code\"" + ":" +"\"" + jsonList.getItemno()+  "\"" 
	        			+ "," + "\"unit\"" + ":" +"\"" + jsonList.getUnit()+  "\"" 
	        		+ "}";
	        json.append(s1);
	        if(i < list.size()-1) {
		        String comma=",";
		        json.append(comma);
	        }
        }
        String s3="]}";
        json.append(s3);
        java.util.Date jsondate2 = new java.util.Date();
		System.out.println("End Time: " + jsondate2);
		long jsondiff = jsondate2.getTime() - datejson1.getTime();
		System.out.println("Elapsed Time: " + jsondiff);
		
		System.out.println("PrintWriting starting........");
		out.println(json);
        System.out.println(json);
        System.out.println("PrintWriting ended........");
        	
	}
}
