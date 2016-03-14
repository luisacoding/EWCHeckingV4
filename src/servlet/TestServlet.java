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

import com.arun.app.ERPInventory;
import com.arun.app.WMSInventroy;
import com.arun.util.SelectSqlserver;

public class TestServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		java.util.Date today = new java.util.Date();
       
        /** SqlServer operation **/
		SelectSqlserver ssql = new SelectSqlserver();

		String sql = "select top 10 logid, u_name from secuser";
		System.out.println(sql);
		String sysno;
		List<ERPInventory> list = new ArrayList<ERPInventory>();
		ssql.connectSSql();
		ResultSet res = ssql.GetSSqlresult(sql);
		try {
			while (res.next()) {
				ERPInventory ERPinv = new ERPInventory();
			//	ERPinv.setAllqty(res.getFloat("logid"));
				ERPinv.setItemno(res.getString("u_name"));
				list.add(ERPinv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ssql.closeSSql();
		System.out.println("ERP inventory ArrayList was added successfully.");  
		// print ArrayList 
		//int lineID = 0;
		//for (int i = 0; i < list.size(); i++) {
		//	lineID++;
		//	String[] str = list.get(i);
		//	System.out.println(lineID + ": "  + str[0] + " " + str[1]);
		//}
		
		/** Oracle operation **/
//		WMSInventroy wmsInv = new WMSInventroy();
//		List<ERPInventory> wmsList = wmsInv.itemList();
//		System.out.println("WMS inventory ArrayList was added successfully.");
		//int lineID = 0;
		//for (int i = 0; i < wmsList.size(); i++) {
		//	lineID++;
		//	String[] str = wmsList.get(i);
		//	System.out.println(lineID + ": " + str[0] + " " + str[1]);
		//}
		
		/* ERPInventory compare to WSMInventory,while inventory is unequal,then add to the new list. */
/*		List<ERPInventory> ERPunequalList = new ArrayList<ERPInventory>();
		List<ERPInventory> WMSunequalList = new ArrayList<ERPInventory>();

		ERPInventory erpTemp, wmsTemp;
		int outCount = 0, inCount = 0;
		
		System.out.println("Processing Patterns starting.......");
		java.util.Date date1 = new java.util.Date();
		System.out.println("Started Time: " + date1);
		for (int i = 0; i < list.size(); i++) {
			outCount++;
			erpTemp = list.get(i);
			//System.out.println("FOR OUTER starting.......");
			for (int j = 0; j < wmsList.size(); j++) {
				inCount++;
				wmsTemp = wmsList.get(j);
				
				// itemno equal and quantity equal, then exist
				if (erpTemp.getItemno() == wmsTemp.getItemno() && erpTemp.getQuantity() == wmsTemp.getQuantity()) {
						
					System.out.println("FOR INNER starting.......");
					break;
				}
				
				// itemno equal but quantity unequal, then insert into unequalList.
				else if (erpTemp.getItemno().equals(wmsTemp.getItemno()) && erpTemp.getQuantity() != wmsTemp.getQuantity()) {
					//ERPInventory erpTempList = new ERPInventory();
					//erpTempList.setItemno(erpTemp.getItemno());
					//erpTempList.setQuantity(erpTemp.getQuantity());
					ERPunequalList.add(erpTemp);
					ERPunequalList.add(wmsTemp);
					break;
				}
			}
			
		}   */
		/** convert to json format */
       StringBuilder json = new StringBuilder();
        String s="["; //+ "\"total\"" + ":100" + ","+"\"rows\"" +":" ,, "{"  +
        json.append(s);
        for(int i=0; i < list.size(); i++){
        	ERPInventory jsonList = list.get(i);
	        String s1="{" + "\"json\"" + ":" +"\"" + jsonList.getItemno()+  "\"" + "}";
	        json.append(s1);
	        if(i < list.size()-1) {
		        String comma=",";
		        json.append(comma);
	        }
        }
        String s3="]"; //}
        json.append(s3); 
        System.out.println(json);
        out.println(json);

	//	String	alpha ="ABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
	//	out.println(alpha);
		
	}
}
