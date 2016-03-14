package com.arun.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arun.util.SelectOracle;

public class WMSPurchaseOthers {
	//public static void main(String[] args) {
	public List<Purchase> itemList(){ 
		/** SqlServer operation **/
		SelectOracle orasql = new SelectOracle();

		String sql = "SELECT a.compno,a.itemno,a.spurno,a.purqty,a.lineid,a.purprc,b.total"
					+ " FROM" 
					+ "("
					+  "  SELECT wo.code compno,wi.code itemno,mst.related_bill1 spurno"
					+  "  ,dtl.expected_quantity_bu purqty,dtl.line_no lineid,dtl.unit_price purprc"
					+  "  FROM oms_order_detail dtl LEFT JOIN  oms_order mst"
					+  "         ON dtl.oms_order_id = mst.id "
					+  "         LEFT JOIN wms_organization wo ON mst.from_org_id=wo.id"
					+  "         LEFT JOIN wms_item wi ON dtl.item_id=wi.\"ID\" "
					+  "  WHERE mst.related_bill1 LIKE 'G%' "
					+")a  LEFT JOIN"
					+"("     
					+"  SELECT purmst.related_bill1 spurno,numb.num total"
					+"  FROM   OMS_ORDER purmst LEFT JOIN"
					+"( "     
					+"     SELECT dtl.oms_order_id,count(dtl.oms_order_id) num"
					+"     FROM oms_order_detail dtl LEFT JOIN  oms_order mst"
					+"             ON dtl.oms_order_id = mst.id"
					+"     WHERE mst.related_bill1 LIKE 'G%'"
					+"     GROUP BY dtl.oms_order_id"
					+"  )numb  ON numb.oms_order_id = purmst.id "
					+"  WHERE purmst.related_bill1 LIKE 'G%'"
					+" )b ON a.spurno = b.spurno"
					+" WHERE a.purqty !=0 "
					+" ORDER BY a.spurno"; 
	/*	String sql=" SELECT c.spurno,count(c.spurno) total FROM(SELECT mst.related_bill1 spurno,dtl.line_no ,count(dtl.line_no) tota FROM oms_order_detail dtl LEFT JOIN  oms_order mst"
				+"  ON dtl.oms_order_id = mst.id"
     + " WHERE mst.related_bill1 LIKE 'G%' AND mst.related_bill1 >'G1505230000' "
     + " GROUP BY dtl.line_no,mst.related_bill1)c GROUP BY c.spurno "; */
		System.out.println(sql);
		//int count = 0;
		List<Purchase> list = new ArrayList<Purchase>();
		orasql.connectoracle();
		ResultSet res = orasql.GetOraresult(sql);
		try {
			while (res.next()) {
				//count++;
				Purchase WMSsales = new Purchase();
				WMSsales.setSpurno(res.getString("spurno"));
				WMSsales.setLineTotal(res.getInt("total"));
				WMSsales.setCompno(res.getString("compno"));
				WMSsales.setItemno(res.getString("itemno"));
				WMSsales.setPurqty(res.getFloat("purqty"));
				WMSsales.setPurprc(res.getFloat("purprc"));
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
			Purchase pur = list.get(i);
			System.out.println(lineID + ": " + "sysno:" + pur.getSpurno() + " compno:" + pur.getCompno() +
					" itemno:" + pur.getItemno() + " qty:" + pur.getPurqty() + " prc:" + pur.getPurprc());
					
		}*/
			
		return list;
	}
}
