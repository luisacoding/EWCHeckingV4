package com.arun.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arun.util.SelectOracle;

public class WMSSalesOrder {
	//public static void main(String[] args) {
	public List<SalesOrder> itemList(){ 
		/** SqlServer operation **/
		SelectOracle orasql = new SelectOracle();

		String sql = "SELECT wo.code compno,wi.code itemno,mst.related_bill1 sysno" +
				",dtl.expected_quantity_bu qty,dtl.line_no lineid,dtl.unit_price prc" +
				" FROM oms_order_detail dtl LEFT JOIN  oms_order mst" +
				" ON dtl.oms_order_id = mst.id" +
				" LEFT JOIN wms_organization wo ON mst.to_org_id=wo.id" +
				" LEFT JOIN wms_item wi ON dtl.item_id=wi.\"ID\"" +
						" WHERE mst.related_bill1 LIKE 'P%'";
		System.out.println(sql);
		//int count = 0;
		List<SalesOrder> list = new ArrayList<SalesOrder>();
		orasql.connectoracle();
		ResultSet res = orasql.GetOraresult(sql);
		try {
			while (res.next()) {
				//count++;
				SalesOrder WMSsales = new SalesOrder();
				WMSsales.setSysno(res.getString("sysno"));
				WMSsales.setCompno(res.getString("compno"));
				WMSsales.setItemno(res.getString("itemno"));
				WMSsales.setQty(res.getFloat("qty"));
				WMSsales.setPrice(res.getFloat("prc"));
				WMSsales.setLineid(res.getInt("lineid"));
				list.add(WMSsales);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		orasql.closeOra();
		
		// print ArrayList
		/*int lineID = 0;
		for (int i = 0; i < list.size(); i++) {
			lineID++;
			SalesOrder salse = list.get(i);
			System.out.println(lineID + ": " + "sysno:" + salse.getSysno() + " compno:" + salse.getCompno() +
					" itemno:" + salse.getItemno() + " qty:" + salse.getQty() + " prc:" + salse.getPrc());
		}
			*/
		return list;
	}
}
