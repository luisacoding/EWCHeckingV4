package com.arun.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arun.util.SelectOracle;

public class WMSUnFinishedPurchase {
	//public static void main(String[] args) {
	public List<WMSPurchaseDate> itemList(){ 
		/** SqlServer operation **/
		SelectOracle orasql = new SelectOracle();

		String sql=" SELECT   mst.related_bill1 ,mst.related_bill1||wi.code spurnoitemno ,to_char(dtl.handover_date,'yyyy-mm-dd') handover_date"
		+"  FROM oms_order_detail dtl LEFT JOIN  oms_order mst ON dtl.oms_order_id = mst.id"
     + " LEFT JOIN wms_organization wo ON mst.from_org_id=wo.id LEFT JOIN wms_item wi ON dtl.item_id=wi.\"ID\" "
     + " LEFT JOIN wms_bill_type btype ON mst.bill_type_id = btype.id WHERE btype.code = 'S01' AND dtl.expected_quantity_bu != 0 "
     + "  ORDER BY mst.related_bill1" ;
		System.out.println(sql);
		//int count = 0;
		List<WMSPurchaseDate> list = new ArrayList<WMSPurchaseDate>();
		orasql.connectoracle();
		ResultSet res = orasql.GetOraresult(sql);
		try {
			while (res.next()) {
				//count++;
				WMSPurchaseDate WMSPurDate = new WMSPurchaseDate();
				WMSPurDate.setSpurnoitemno(res.getString("spurnoitemno"));
				WMSPurDate.setHandoverdate(res.getString("handover_date"));
			/*	WMSsales.setCompno(res.getString("compno"));
				WMSsales.setItemno(res.getString("itemno"));
				WMSsales.setPurqty(res.getFloat("purqty"));
				WMSsales.setPurprc(res.getFloat("purprc"));
				WMSsales.setLineid(res.getInt("lineid"));  */
				list.add(WMSPurDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		orasql.closeOra();
		
		// print ArrayList
	/*	int lineID = 0;
		for (int i = 0; i < list.size(); i++) {
			lineID++;
			WMSPurchaseDate pur = list.get(i);
			System.out.println(lineID + ": " + "sysno:" + pur.getSpurnoitemno() 
					+ " compno:" + pur.getHandoverdate());
					
		}
		*/	
		return list;
	}
}
