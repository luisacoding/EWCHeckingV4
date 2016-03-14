package com.arun.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arun.util.SelectOracle;

public class WMSSalesLineTotal {
	//public static void main(String[] args) {
	public List<SalesOrder> itemList(){ 
		/** SqlServer operation **/
		SelectOracle orasql = new SelectOracle();

		String sql=" SELECT c.spurno,count(c.spurno) total FROM(SELECT mst.related_bill1 spurno,dtl.line_no ,count(dtl.line_no) tota FROM oms_order_detail dtl LEFT JOIN  oms_order mst"
				+"  ON dtl.oms_order_id = mst.id"
     + " WHERE mst.related_bill1 LIKE 'P%' AND mst.related_bill1 >'P1505230000' "
     + " GROUP BY dtl.line_no,mst.related_bill1)c GROUP BY c.spurno ";
		System.out.println(sql);
		//int count = 0;
		List<SalesOrder> list = new ArrayList<SalesOrder>();
		orasql.connectoracle();
		ResultSet res = orasql.GetOraresult(sql);
		try {
			while (res.next()) {
				//count++;
				SalesOrder WMSsales = new SalesOrder();
			/*	WMSsales.setSysno(res.getString("sysno"));
				WMSsales.setCompno(res.getString("compno"));
				WMSsales.setItemno(res.getString("itemno"));
				WMSsales.setQty(res.getFloat("qty"));
				WMSsales.setPrice(res.getFloat("prc")); 
			*/
				WMSsales.setLineTotal(res.getInt("total"));
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
