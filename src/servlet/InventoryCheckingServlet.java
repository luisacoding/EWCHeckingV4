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

public class InventoryCheckingServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		//java.util.Date today = new java.util.Date();
        
        /** SqlServer operation **/
		SelectSqlserver ssql = new SelectSqlserver();

		String sql = "select itemno, whouseOne, sumqty, quantified, unquantified," +
		        "whouseTwo,whouseFour,whouseFive,whouseSix,whouseSeven,whouseEight,"
				+"whouseNine,whouseTen,whouseEleven,whouseTwelve,whouseThirteen,"
		        +"whouseFifteen,whouseSixteen,whouseEighteen,whouseTwenty,otherqty,allstring"
				+ " from ERPInv";
		System.out.println(sql);
		String sysno;
		List<ERPInventory> list = new ArrayList<ERPInventory>();
		ssql.connectSSql();
		ResultSet res = ssql.GetSSqlresult(sql);
		try {
			while (res.next()) {
				ERPInventory ERPinv = new ERPInventory();
				ERPinv.setItemno(res.getString("itemno"));
				ERPinv.setQuantity(res.getFloat("sumqty"));				
				ERPinv.setQuantified(res.getFloat("quantified"));
				ERPinv.setUnquantified(res.getFloat("unquantified"));
				ERPinv.setOtherqty(res.getFloat("otherqty"));
				ERPinv.setAllstring(res.getString("allstring"));
				ERPinv.setWhouseOne(res.getFloat("whouseOne"));
				ERPinv.setWhouseTwo(res.getFloat("whouseTwo"));
				ERPinv.setWhouseFour(res.getFloat("whouseFour"));
				ERPinv.setWhouseFive(res.getFloat("whouseFive"));
				ERPinv.setWhouseSix(res.getFloat("whouseSix"));
				ERPinv.setWhouseEight(res.getFloat("whouseEight"));
				ERPinv.setWhouseNine(res.getFloat("whouseNine"));
				ERPinv.setWhouseTen(res.getFloat("whouseTen"));
				ERPinv.setWhouseEleven(res.getFloat("whouseEleven"));
				ERPinv.setWhouseTwelve(res.getFloat("whouseTwelve"));
				ERPinv.setWhouseThirteen(res.getFloat("whouseThirteen"));
				ERPinv.setWhouseFifteen(res.getFloat("whouseFifteen"));
				ERPinv.setWhouseSixteen(res.getFloat("whouseSixteen"));
				ERPinv.setWhouseEighteen(res.getFloat("whouseEighteen"));
				ERPinv.setWhouseTwenty(res.getFloat("whouseTwenty"));
				list.add(ERPinv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ssql.closeSSql();
		System.out.println("ERP inventory ArrayList was added successfully.");

		/** Oracle operation **/
		WMSInventroy wmsInv = new WMSInventroy();
		List<ERPInventory> wmsList = wmsInv.itemList();
		System.out.println("WMS inventory ArrayList was added successfully.");
		
		/* ERPInventory compare to WSMInventory,while inventory is unequal,then add to the new list. */
		List<ERPInventory> ERPunequalList = new ArrayList<ERPInventory>();
		List<ERPInventory> WMSunequalList = new ArrayList<ERPInventory>();

		ERPInventory erpTemp, wmsTemp;
		int outCount = 0, inCount = 0;
		/** 差异数 */
		float difference = 0.0f;
		
		System.out.println("Processing Patterns starting.......");
		java.util.Date date1 = new java.util.Date();
		System.out.println("Started Time: " + date1);
		for (int i = 0; i < list.size(); i++) {
			outCount++;
			erpTemp = list.get(i);
			boolean isExist = false;
			//System.out.println("FOR OUTER starting.......");
			for (int j = 0; j < wmsList.size(); j++) {
				inCount++;
				wmsTemp = wmsList.get(j);
				
				// itemno equal but quantity unequal, then insert into unequalList.
				 if (erpTemp.getItemno().equals(wmsTemp.getItemno()) ) {
					//ERPInventory erpTempList = new ERPInventory();
					//erpTempList.setItemno(erpTemp.getItemno());
					//erpTempList.setQuantity(erpTemp.getQuantity());
					isExist = true;
					
					if (erpTemp.getQuantity() != wmsTemp.getQuantity()) {
							difference = erpTemp.getQuantity() - wmsTemp.getQuantity();
							erpTemp.setDifference(difference);
							wmsTemp.setDifference(difference);
							ERPunequalList.add(erpTemp);
							ERPunequalList.add(wmsTemp);
							break;
					}
				}
				if (!isExist && (j == list.size() - 1) )
					WMSunequalList.add(wmsTemp);
	
			}
			
		}
		java.util.Date date2 = new java.util.Date();
		System.out.println("End Time: " + date2);
		long diff = date2.getTime() - date1.getTime();
		System.out.println("Elapsed TIme: " + diff);
		
		/** convert to json format */
		System.out.println("Processing json format.......");
		java.util.Date datejson1 = new java.util.Date();
		System.out.println("Started Time: " +datejson1);
		
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
        	ERPInventory jsonList = ERPunequalList.get(i);
	        String s1="{" + "\"lineno\"" + ":" +"\"" + lineid +  "\"" 
	        			+ "," + "\"itemno\"" + ":" +"\"" + jsonList.getItemno()+  "\"" 
	        			+ "," + "\"qty\"" + ":" +"\"" + jsonList.getQuantity()+  "\"" 
	        			+ "," + "\"difference\"" + ":" +"\"" + jsonList.getDifference()+  "\"" 
	        			+ "," + "\"allqty\"" + ":" +"\"" + jsonList.getAllqty()+  "\"" 
	        			+ "," + "\"mqty\"" + ":" +"\"" + jsonList.getMountqty()+  "\"" 
	        			+ "," + "\"fqty\"" + ":" +"\"" + jsonList.getFeedbackqty()+  "\"" 
	        			+ "," + "\"ufqty\"" + ":" +"\"" + jsonList.getUfeedbackqty()+  "\"" 
	        			+ "," + "\"quantified\"" + ":" +"\"" + jsonList.getQuantified()+  "\""
	        			+ "," + "\"unquantified\"" + ":" +"\"" + jsonList.getUnquantified()+  "\""
	        			+ "," + "\"otherqty\"" + ":" +"\"" + jsonList.getOtherqty()+  "\""
	        			+ "," + "\"allstring\"" + ":" +"\"" + jsonList.getAllstring()+  "\""
	        			+ "," + "\"whouseOne\"" + ":" +"\"" + jsonList.getWhouseOne()+  "\""
	        			+ "," + "\"whouseTwo\"" + ":" +"\"" + jsonList.getWhouseTwo()+  "\""
	        			+ "," + "\"whouseFour\"" + ":" +"\"" + jsonList.getWhouseFour()+  "\""
	        			+ "," + "\"whouseFive\"" + ":" +"\"" + jsonList.getWhouseFive()+  "\""
	        			+ "," + "\"whouseSix\"" + ":" +"\"" + jsonList.getWhouseSix()+  "\""
	        			+ "," + "\"whouseSeven\"" + ":" +"\"" + jsonList.getWhouseSeven()+  "\""
	        			+ "," + "\"whouseEight\"" + ":" +"\"" + jsonList.getWhouseEight()+  "\""
	        			+ "," + "\"whouseNine\"" + ":" +"\"" + jsonList.getWhouseNine()+  "\""
	        			+ "," + "\"whouseTen\"" + ":" +"\"" + jsonList.getWhouseTen()+  "\""
	        			+ "," + "\"whouseEleven\"" + ":" +"\"" + jsonList.getWhouseEleven()+  "\""
	        			+ "," + "\"whouseTwelve\"" + ":" +"\"" + jsonList.getWhouseTwelve()+  "\""
	        			+ "," + "\"whouseThirteen\"" + ":" +"\"" + jsonList.getWhouseThirteen()+  "\""
	        			+ "," + "\"whouseFifteen\"" + ":" +"\"" + jsonList.getWhouseFifteen()+  "\""
	        			+ "," + "\"whouseSixteen\"" + ":" +"\"" + jsonList.getWhouseSixteen()+  "\""
	        			+ "," + "\"whouseEighteen\"" + ":" +"\"" + jsonList.getWhouseEighteen()+  "\""
	        			+ "," + "\"whouseTwenty\"" + ":" +"\"" + jsonList.getWhouseTwenty()+  "\""
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
