package com.arun.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arun.util.SelectOracle;

public class WMSInventroy {
	//public static void main(String[] args) {
	public List<ERPInventory> itemList(){ 
		/** SqlServer operation **/
		SelectOracle orasql = new SelectOracle();

		String sql = "select itemno, 实际库存 ,合格 ,不合格,allqty,mqty,fqty,ufqty  from WMSInv"
				+" where 实际库存!=0";
		System.out.println(sql);
		//int count = 0;
		List<ERPInventory> list = new ArrayList<ERPInventory>();
		orasql.connectoracle();
		ResultSet res = orasql.GetOraresult(sql);
		try {
			while (res.next()) {
				//count++;
				ERPInventory inv = new ERPInventory();
				inv.setItemno(res.getString("itemno"));
				inv.setQuantity(res.getFloat("实际库存"));
				inv.setQuantified(res.getFloat("合格"));
				inv.setUnquantified(res.getFloat("不合格"));
				inv.setAllqty(res.getFloat("allqty"));
				inv.setMountqty(res.getFloat("mqty"));
				inv.setFeedbackqty(res.getFloat("fqty"));
				inv.setUfeedbackqty(res.getFloat("ufqty"));
				//String[] str = { sysno, Float.toString(quantity) };
				// System.out.println("lineno" + count + ": " + sysno + " " +
				// quantity);
				// System.out.println("lineno " + count + ": " + str[0] + " " +
				// str[1]);
				list.add(inv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		orasql.closeOra();
		
		// print ArrayList
		//int lineID = 0;
		//for (int i = 0; i < 2000; i++) {
		//	lineID++;
		//	ERPInventory inv = list.get(i);
		//	System.out.println(lineID + ": " + inv.getItemno() + " " + inv.getQuantity());
		//}

		return list;
	}
}
