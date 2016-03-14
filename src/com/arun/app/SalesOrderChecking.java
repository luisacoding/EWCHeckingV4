package com.arun.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arun.util.SelectSqlserver;

public class SalesOrderChecking {
	public static void main(String[] args) {
		/** SqlServer operation **/
		SelectSqlserver ssql = new SelectSqlserver();

		String sql = "SELECT mst.sysno, mst.compno,dtl.lineid , dtl.itemno ,dtl.qty, dtl.prc, dtl.locsum FROM orderdet dtl LEFT JOIN ordermst mst ON dtl.sysno = mst.sysno WHERE  dtl.sysno > 'P150523000'";
		System.out.println(sql);
		String sysno;
		List<SalesOrder> list = new ArrayList<SalesOrder>();
		ssql.connectSSql();
		ResultSet res = ssql.GetSSqlresult(sql);
		try {
			while (res.next()) {
				SalesOrder ERPsales = new SalesOrder();
				ERPsales.setSysno(res.getString("sysno"));
				ERPsales.setCompno(res.getString("compno"));
				ERPsales.setItemno(res.getString("itemno"));
				ERPsales.setQty(res.getFloat("qty"));
				ERPsales.setPrice(res.getFloat("prc"));
				ERPsales.setLineid(res.getInt("lineid"));
				list.add(ERPsales);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ssql.closeSSql();
		System.out.println("ERP sales order ArrayList was added successfully.");
		// print ArrayList 
		//int lineID = 0;
		//for (int i = 0; i < list.size(); i++) {
		//	lineID++;
		//	String[] str = list.get(i);
		//	System.out.println(lineID + ": "  + str[0] + " " + str[1]);
		//}
		
		/** Oracle operation **/
		WMSSalesOrder wmsSales = new WMSSalesOrder();
		List<SalesOrder> wmsList = wmsSales.itemList();
		System.out.println("WMS inventory ArrayList was added successfully.");
		//int lineID = 0;
		//for (int i = 0; i < wmsList.size(); i++) {
		//	lineID++;
		//	String[] str = wmsList.get(i);
		//	System.out.println(lineID + ": " + str[0] + " " + str[1]);
		//}
		
		/* ERPInventory compare to WSMInventory,while inventory is unequal,then add to the new list. */
		List<SalesOrder> ERPunequalList = new ArrayList<SalesOrder>();
		List<SalesOrder> WMSunequalList = new ArrayList<SalesOrder>();

		SalesOrder erpTemp, wmsTemp;
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
				 if (erpTemp.getSysno().equals(wmsTemp.getSysno()) 
						 && erpTemp.getLineid() == wmsTemp.getLineid() 
						 && erpTemp.getQty() != wmsTemp.getQty()
						 && erpTemp.getPrc() != wmsTemp.getPrc() ) {
					//ERPInventory erpTempList = new ERPInventory();
					//erpTempList.setItemno(erpTemp.getItemno());
					//erpTempList.setQuantity(erpTemp.getQuantity());
					ERPunequalList.add(erpTemp);
					ERPunequalList.add(wmsTemp);
					break;
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
			SalesOrder sales = ERPunequalList.get(i);
			System.out.println(lineID + ": " + sales.getSysno() + " " + sales.getItemno() + " " + sales.getQty() + " " + sales.getPrc());
			//System.out.println(lineID + "---------------- Printing Results ----------------");
		}
		/** WMS inventory */
		//System.out.println("---------------- ---------------- -------------------------------");
		//for (int i = 0; i < WMSunequalList.size(); i++) {
		//	lineID++;
		//	ERPInventory inv = WMSunequalList.get(i);
		//	System.out.println(lineID + ": " + inv.getItemno() + " " + inv.getQuantity());
		//	//System.out.println(lineID + "---------------- Printing Results ----------------");
		//}
		
		System.out.println("---------------- Printing Results Eeded -------------------------------");
		System.out.println("Elapsed TIme: " + diff);
	}
}
