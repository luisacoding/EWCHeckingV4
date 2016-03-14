package com.arun.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arun.util.SelectSqlserver;

public class PurchaseChecking {
	public static void main(String[] args) {
		/** SqlServer operation **/
		SelectSqlserver ssql = new SelectSqlserver();
		/*
		String sql = "SELECT mst.spurno, mst.compno, dtl.lineid, dtl.itemno, dtl.purqty, dtl.purprc ,line.total " +
				"FROM purmst mst LEFT JOIN purdec dtl ON mst.spurno = dtl.spurno " +
				"	LEFT JOIN " +
				"("	 +
					"SELECT spurno,count(spurno) total" +
				"	FROM purdec" +
				"	GROUP BY spurno" +
				"	)line ON mst.spurno = line.spurno" +
				"	ORDER BY mst.spurno"; */
		String sql ="SELECT spurno,count(spurno) total FROM purdec GROUP BY spurno";
		System.out.println(sql);
		String sysno;
		List<Purchase> list = new ArrayList<Purchase>();
		ssql.connectSSql();
		ResultSet res = ssql.GetSSqlresult(sql);
		try {
			while (res.next()) {
				Purchase ERPpur = new Purchase();
				ERPpur.setSpurno(res.getString("spurno"));
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
		// print ArrayList 
		//int lineID = 0;
		//for (int i = 0; i < list.size(); i++) {
		//	lineID++;
		//	String[] str = list.get(i);
		//	System.out.println(lineID + ": "  + str[0] + " " + str[1]);
		//}
		
		/** Oracle operation **/
		WMSPurchase wmsSales = new WMSPurchase();
		List<Purchase> wmsList = wmsSales.itemList();
		System.out.println("WMS PurchaseOrder ArrayList was added successfully.");
		//int lineID = 0;
		//for (int i = 0; i < wmsList.size(); i++) {
		//	lineID++;
		//	String[] str = wmsList.get(i);
		//	System.out.println(lineID + ": " + str[0] + " " + str[1]);
		//}
		
		/* ERPInventory compare to WSMInventory,while inventory is unequal,then add to the new list. */
		List<Purchase> ERPunequalList = new ArrayList<Purchase>();
		List<Purchase> WMSunequalList = new ArrayList<Purchase>();

		Purchase erpTemp, wmsTemp;
		int outCount = 0, inCount = 0;
		
		System.out.println("Processing Patterns starting.......");
		java.util.Date date1 = new java.util.Date();
		System.out.println("Started Time: " + date1);
		System.out.println("erp size:" + list.size() + " WMS size:" + wmsList.size());
		for (int i = 0; i < list.size(); i++) {
			outCount++;
			erpTemp = list.get(i);
			//System.out.println("FOR OUTER starting.......");
			for (int j = 0; j < wmsList.size(); j++) {
				inCount++;
				wmsTemp = wmsList.get(j);
				
				// itemno equal and quantity equal, then exist
				//if (erpTemp.getSysno().equals(wmsTemp.getSysno()) && erpTemp.getLineno() == wmsTemp.getLineno()  && 
				//		erpTemp.getQty() == wmsTemp.getQty()) {
				//		
				//	System.out.println("FOR INNER starting.......");
				//	break;
				//}
				
				// itemno equal but quantity unequal, then insert into unequalList.
				 if ( erpTemp.getSpurno().equals(wmsTemp.getSpurno()) 
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
		
		System.out.println("Processing Patterns end.......");
		System.out.println("outCount: " + outCount);
		System.out.println("inCount: " + inCount);
		// print ArrayList
		System.out.println("---------------- Printing Results Started-------------------------------");
		
		int lineID = 0;
		for (int i = 0; i < ERPunequalList.size(); i++) {
			lineID++;
			Purchase pur = ERPunequalList.get(i);
			System.out.println(lineID + ": " + pur.getSpurno() + " " + pur.getLineTotal());
			//System.out.println(lineID + "---------------- Printing Results ----------------");
		}
		/** WMS purchase order */
		//System.out.println("---------------- ---------------- -------------------------------");
		//for (int i = 0; i < WMSunequalList.size(); i++) {
		//	lineID++;
		//	ERPInventory inv = WMSunequalList.get(i);
		//	System.out.println(lineID + ": " + inv.getItemno() + " " + inv.getQuantity());
		//	//System.out.println(lineID + "---------------- Printing Results ----------------");
		//}
		
		System.out.println("---------------- Printing Results Ended -------------------------------");
		System.out.println("Elapsed TIme: " + diff);
	}
}
