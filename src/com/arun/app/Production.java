package com.arun.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arun.util.SelectSqlserver;

public class Production {
	SelectSqlserver ssql=new SelectSqlserver();
	public List<String[]> item_num(String str1,String str2){
		
		String sql="select isnull(a1.deptname,b1.deptname) deptname,isnull(a1.itemno,b1.itemno) itemno,isnull(a1.itemname,b1.itemname) itemname,isnull(a1.descript,b1.descript) descript,isnull(a1.num,0)num,isnull(b1.\"计划需求\",0)plq,isnull(b1.\"生产消耗\",0)quc,isnull(a1.msname ,b1.msname) msname  from("+
" select top 100 percent  e.deptname,b.itemno,c.itemname,c.descript ,sum(b.drawqty) as num,d.msname "+
" from drawapplymst a,drawapplydet b,itemdata c,msunit d ,dept e "+
" where "+str1+
" and a.sysno=b.sysno and b.itemno=c.itemno and a.drawdept=e.deptno and b.msunit=d.msunit and b.drawqty!=0 "+
" group by  e.deptname,b.itemno,c.itemname,c.descript,d.msname "+
" order by deptname) a1 full outer join (select top 100 percent c.deptname,b.itemno,d.itemname,d.descript,e.msname,sum(a.qty_ord*b.qty/b.f_qty) '计划需求',sum(a.qty_comp*b.qty/b.f_qty) '生产消耗' "+
" from mps_mfg_ord a,bom b,dept c ,itemdata d,msunit e "+
" where "+str2+
" and a.is_mfg_wrk='W' and a.statusflg in('R','C')  and a.itemno=b.f_itemno and a.def_str3=c.deptno and d.itemno=b.itemno "+
" and e.msunit=d.msunit "+
" group by c.deptname,b.itemno,d.itemname,d.descript,e.msname "+
" order by c.deptname "+
" ) b1 on a1.deptname=b1.deptname  and a1.itemno=b1.itemno "+
"order by deptname,itemno "; 
		System.out.println(sql);
		String deptname;
		String itemno;
		String itemname;
		String descript;
		float num;
		float demand;
		float consumption;
		String msname;
		List<String[]>  list=new ArrayList<String[]>();
		ssql.connectSSql();
		ResultSet res=ssql.GetSSqlresult(sql);
		try {
			while(res.next()){
				deptname=res.getString("deptname");
				itemno=res.getString("itemno");
				itemname=res.getString("itemname");
				descript=res.getString("descript");
				num=res.getFloat("num");
				demand=res.getFloat("plq");
				consumption=res.getFloat("quc");
				msname=res.getString("msname");
				String[] str={deptname,itemno,itemname,descript,Float.toString(num),Float.toString(demand),Float.toString(consumption),msname};
				list.add(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ssql.closeSSql();
		return list;
	}
}
