package com.arun.app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.arun.util.*;

public class Warehousing  {
	public   String GetOraValue(String sql){
		String sqlora="select a1.related_bill1,a1.code,a1.wmsnum-b1.wmsnum wmsnum from( "+
"select a.related_bill1,e.code, sum(b.moved_quantity_bu) wmsnum "+
"from WMS_ASN a ,WMS_ASN_DETAIL b ,WMS_ITEM e "+
"where a.related_bill1 like 'G%'  and a.id=b.asn_id and a.quality_stauts!='NOQUALITY' and b.moved_quantity_bu!=0 and b.item_id=e.id "+sql+
"group by a.related_bill1,e.code) a1 , "+
"(select c.related_bill1,e.code,sum(d.unqualified_bu) wmsnum "+
"from WMS_QUALITY_BILL c ,WMS_QUALITY_BILL_DETAIL d ,WMS_ITEM e "+
"where c.related_bill1 like 'G%' and c.id=d.quality_bill_id and d.item_id=e.id "+
"group by c.related_bill1 ,e.code) b1 where a1.related_bill1=b1.related_bill1 and a1.code=b1.code "+
"union all "+
"select a.related_bill1,e.code, sum(b.moved_quantity_bu) wmsnum "+
"from WMS_ASN a ,WMS_ASN_DETAIL b ,WMS_ITEM e "+
"where a.related_bill1 like 'G%'  and a.id=b.asn_id and a.quality_stauts='NOQUALITY' and b.moved_quantity_bu!=0 and b.item_id=e.id "+sql+
"group by a.related_bill1,e.code";
		return sqlora;
	}
	
	public String GetSSqlValue(String spurno,String itemno,float wmsnum){
		String sqlssql="select sum(a.useqty) as erpnum from billbody as a,billhead as b where a.billno=b.billno and transdt>'2015-05-22' and b.contractno='"+spurno+"' and a.goodsname='"+itemno+"' having sum(a.useqty)!="+wmsnum;
		return sqlssql;
	}
	public List<String[]> Warehosing(String begintime,String endtime,String erp,String code){
		String condition=" ";
        String ssql;
        String orclsql;
        if(begintime!=null&&begintime!=""){
        	condition+=" and a.handover_date>=to_date('"+begintime+"' ,'yyyy-mm-dd')";
        }else if((erp!=null&&erp!="")||(code!=null&&code!="")){
        	
        }
        else{
        	condition+=" and a.handover_date>=(select trunc(sysdate, 'mm')   from   dual)";
        }
        if(endtime!=null&&endtime!=""){
        	condition+=" and a.handover_date<=to_date('"+endtime+"' ,'yyyy-mm-dd')";
        }else if((erp!=null&&erp!="")||(code!=null&&code!="")){
        
        }
        else{
        	condition+="  and a.handover_date<=(select sysdate from dual)";
        }
        if(erp!=null&&erp!=""){
        	condition+=" and a.related_bill1 like '"+erp.trim()+"'";
        }
        if(code!=null&&code!=""){
        	condition+=" and e.code like '"+code.trim()+"'";
        }
		SelectOracle sOrcl=new SelectOracle();
        SelectSqlserver sSSql=new SelectSqlserver();
        sOrcl.connectoracle();
        sSSql.connectSSql();
        orclsql=GetOraValue(condition);
        ResultSet ResOra=sOrcl.GetOraresult(orclsql);
        String spurno;
        String itemno;
        float WMSnum;
        float ERPnum=0; 
        int i=1;
        List<String[]>   list=new ArrayList<String[]>();
        try {
			while(ResOra.next()){
				 spurno=ResOra.getString("related_bill1");
	             itemno=ResOra.getString("code");
	             WMSnum=ResOra.getFloat("wmsnum");
	             ssql=GetSSqlValue(spurno,itemno,WMSnum);
	             ResultSet Resssql=sSSql.GetSSqlresult(ssql);
	             if(Resssql.next()){
	            	 ERPnum=Resssql.getFloat("erpnum");
	            	 if(ERPnum!=0){
	            	String[] str={Integer.toString(i),spurno,itemno,Float.toString(WMSnum),Float.toString(ERPnum)}; 
	            	list.add(str);
	            	i++;
	            	 }
	             }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sOrcl.closeOra();
		sSSql.closeSSql();
		return list;
	}
}
