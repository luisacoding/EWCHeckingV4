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


import com.arun.app.Purchase;
import com.arun.app.SalesOrder;
import com.arun.app.WMSPurchase;
import com.arun.app.WMSSalesLineTotal;
import com.arun.util.SelectSqlserver;

public class SalesLineTotalServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
	//public static void main(String[] args) {	
		PrintWriter out = response.getWriter();
		//java.util.Date today = new java.util.Date();
        
        /** SqlServer operation **/
		SelectSqlserver ssql = new SelectSqlserver();

		String sql ="SELECT sysno,count(sysno) total FROM orderdet WHERE sysno > 'P1505230000' GROUP BY sysno";
		System.out.println(sql);
		String sysno;
		List<SalesOrder> list = new ArrayList<SalesOrder>();
		ssql.connectSSql();
		ResultSet res = ssql.GetSSqlresult(sql);
		try {
			while (res.next()) {
				SalesOrder ERPpur = new SalesOrder();
				ERPpur.setSysno(res.getString("sysno"));
			//	ERPpur.setCompno(res.getString("compno"));
			//	ERPpur.setItemno(res.getString("itemno"));
			//	ERPpur.setPurqty(res.getFloat("purqty"));
			//	ERPpur.setPurprc(res.getFloat("purprc"));
			//	ERPpur.setLineid(res.getInt("lineid"));
				ERPpur.setLineTotal(res.getInt("total"));
				list.add(ERPpur);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ssql.closeSSql();
		System.out.println("ERP Purchase order ArrayList was added successfully.");

		/** Oracle operation **/
		WMSSalesLineTotal wmsSales = new WMSSalesLineTotal();
		List<SalesOrder> wmsList = wmsSales.itemList();
		System.out.println("WMS PurchaseOrder ArrayList was added successfully.");
		System.out.println("ERP list size: " + list.size()+ " " + "wms list size: " + wmsList.size());
		
		/* ERPInventory compare to WSMInventory,while inventory is unequal,then add to the new list. */
		List<SalesOrder> ERPunequalList = new ArrayList<SalesOrder>();
		List<SalesOrder> WMSunequalList = new ArrayList<SalesOrder>();

		SalesOrder erpTemp, wmsTemp;
		int outCount = 0, inCount = 0;
		/** 差异数 */
		float difference = 0.0f;
		
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
				
				// itemno equal but quantity unequal, then insert into unequalList.
				 if ( erpTemp.getSysno().equals(wmsTemp.getSysno()) 
						 ) {
					 
					 if ( erpTemp.getLineTotal() != wmsTemp.getLineTotal() ) {
							ERPunequalList.add(erpTemp);
							ERPunequalList.add(wmsTemp);
							break;
					 }
				}
			}
			
		}
		java.util.Date date2 = new java.util.Date();
		System.out.println("End Time: " + date2);
		long diff = date2.getTime() - date1.getTime();
		System.out.println("Elapsed TIme: " + diff);
		
		/** convert to json format */
		System.out.println("Processing json format.......");
		java.util.Date datejson1 = new java.util.Date();
		System.out.println("json formatting Started Time: " +datejson1);
		
        StringBuilder json = new StringBuilder();
        String s="{" + "\"total\"" + ":100" + ","+"\"rows\"" +":" +"[";
        json.append(s);
        int lineid = 0, lineno = 0;
        for(int i=0; i < ERPunequalList.size(); i++){
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
        	SalesOrder jsonList = ERPunequalList.get(i);
	        String s1="{" + "\"lineno\"" + ":" +"\"" + lineid +  "\"" 
	        			+ "," + "\"spurno\"" + ":" +"\"" + jsonList.getSysno()+  "\"" 
	        			+ "," + "\"lineTotal\"" + ":" +"\"" + jsonList.getLineTotal()+  "\"" 
	        		+ "}";
	        json.append(s1);
	        if(i < ERPunequalList.size()-1) {
		        String comma=",";
		        json.append(comma);
	        }
        }
        String s3="]}";
        json.append(s3);
        java.util.Date jsondate2 = new java.util.Date();
		System.out.println("End Time: " + jsondate2);
		long jsondiff = jsondate2.getTime() - datejson1.getTime();
		System.out.println("Elapsed TIme: " + jsondiff);
		
		System.out.println("PrintWriting starting........");
		out.println(json);
        System.out.println(json);
        
        System.out.println("PrintWriting ended........");
        	
	}
}
