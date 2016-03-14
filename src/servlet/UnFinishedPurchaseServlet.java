package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.arun.app.UnFinishedPurchase;
import com.arun.app.WMSPurchaseDate;
import com.arun.app.WMSUnFinishedPurchase;
import com.arun.util.SelectSqlserver;

	public class UnFinishedPurchaseServlet extends HttpServlet {
		public void doGet(HttpServletRequest request,
				HttpServletResponse response)
				throws IOException {
			response.setContentType("text/html;charset=utf-8");  
		//public static void main(String[] args) {	
			PrintWriter out = response.getWriter();
			java.util.Date today = new java.util.Date();
	        
	        /** SqlServer operation **/
			SelectSqlserver ssql = new SelectSqlserver();
			
			String sql = "select  采购订单+物料编码  AS spurnoitemno," +
					" 采购订单,供应商编码,供应商名称,物料编码,REPLACE(物料名称,CHAR(92),'/') 物料名称,REPLACE(规格,CHAR(92),'/') AS 规格,订单数量,已送待检数," +
					" 还需送货数,退换货数,仓库账面结余数,未领料数,实际可用库存,采购员,CONVERT(varchar(100), 需求日期, 23) AS 需求日期,REPLACE(REPLACE(REPLACE([备注],CHAR(13),''),CHAR(10),''),CHAR(92),'\\') AS 备注,单价,仓库期初数" +
					" from [NG0006].[dbo].[lxz_cgddgz3] "
					+ " WHERE 还需送货数 != 0  ORDER BY  采购订单";
			System.out.println(sql);
			
			List<UnFinishedPurchase> list = new ArrayList<UnFinishedPurchase>();
			ssql.connectSSql();
			ResultSet res = ssql.GetSSqlresult(sql);
			try {
				while (res.next()) {
					UnFinishedPurchase ERPpur = new UnFinishedPurchase();
					ERPpur.setSpurnoitemno(res.getString("spurnoitemno"));
					ERPpur.setSpurno(res.getString("采购订单"));
					ERPpur.setCompno(res.getString("供应商编码"));
					ERPpur.setCompname(res.getString("供应商名称"));
					ERPpur.setItemno(res.getString("物料编码"));
					ERPpur.setItemname(res.getString("物料名称"));
					ERPpur.setDescript(res.getString("规格"));
					ERPpur.setPurqty(res.getFloat("订单数量"));
					ERPpur.setRecqty_daij(res.getFloat("已送待检数"));
					ERPpur.setQianjiaoshu(res.getFloat("还需送货数"));
					ERPpur.setRtnqty_yij(res.getFloat("退换货数"));
					ERPpur.setJiecun(res.getFloat("仓库账面结余数"));
					ERPpur.setUnpicked(res.getFloat("未领料数"));
					ERPpur.setRealusage(res.getFloat("实际可用库存"));
					ERPpur.setLastname(res.getString("采购员"));
					ERPpur.setDuedt(res.getString("需求日期"));
					//ERPpur.setHandoverDate(res.getString("采购交期回复"));
					ERPpur.setRemarks(res.getString("备注"));
					
					ERPpur.setPurprc(res.getFloat("单价"));
					ERPpur.setBgnqty(res.getInt("仓库期初数"));
					
					list.add(ERPpur);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ssql.closeSSql();
			System.out.println("ERP Purchase order ArrayList was added successfully.");
			
			/** Oracle operation **/
			WMSUnFinishedPurchase wmsPurDate= new WMSUnFinishedPurchase();
			List<WMSPurchaseDate> wmsList = wmsPurDate.itemList();
			System.out.println("WMS PurchaseOrder ArrayList was added successfully.");
			
			/* ERPInventory compare to WSMInventory,while inventory is unequal,then add to the new list. */
		//	List<UnFinishedPurchase> ERPunequalList = new ArrayList<UnFinishedPurchase>();
		//	List<UnFinishedPurchase> WMSunequalList = new ArrayList<UnFinishedPurchase>();
			
			UnFinishedPurchase erpTemp;
			WMSPurchaseDate	wmsTemp;
			int outCount = 0, inCount = 0;
			/** 差异数 */
			float difference = 0.0f;
			
			System.out.println("Processing Patterns starting.......");
			java.util.Date date1 = new java.util.Date();
			System.out.println("Started Time: " + date1);
			for (int i = 0; i < list.size(); i++) {
				outCount++;
				erpTemp = list.get(i);
				//System.out.println("FOR OUTER starting.......");
				for (int j = 0; j < wmsList.size(); j++) {
					inCount++;
					wmsTemp = wmsList.get(j);
					
					// itemno equal but quantity unequal, then insert into unequalList.
					 if ( erpTemp.getSpurnoitemno().equals(wmsTemp.getSpurnoitemno()) 
						 ) {
						 
						 
								list.get(i).setHandoverDate(wmsList.get(j).getHandoverdate());
								
								break;
						 
					}
				}
				
			}
			java.util.Date date2 = new java.util.Date();
			System.out.println("End Time: " + date2);
			long diff = date2.getTime() - date1.getTime();
			System.out.println("Elapsed TIme: " + diff);
			
			/** convert to json format */
			System.out.println("Processing json format.......");
			java.util.Date datejson1 = new java.util.Date();
			System.out.println("json formatting Started Time: " +datejson1);
			
	        StringBuilder json = new StringBuilder();
	        String s="{" + "\"total\"" + ":1500" + ","+"\"rows\"" +":" +"[";
	        json.append(s);
	        int lineid = 0, lineno = 0;
	        for(int i=0; i < list.size(); i++){
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
	        	UnFinishedPurchase jsonList = list.get(i);
		        String s1="{" + "\"lineno\"" + ":" +"\"" + lineid +  "\"" 
		        			+ "," + "\"spurno\"" + ":" +"\"" + jsonList.getSpurno()+  "\""
		        			+ "," + "\"compno\"" + ":" +"\"" + jsonList.getCompno()+  "\""
		        			+ "," + "\"compname\"" + ":" +"\"" + jsonList.getCompname()+  "\""
		        			+ "," + "\"itemno\"" + ":" +"\"" + jsonList.getItemno()+  "\""
		        			+ "," + "\"itemname\"" + ":" +"\"" + jsonList.getItemname()+  "\""
		        			+ "," + "\"descript\"" + ":" +"\"" + jsonList.getDescript()+  "\""
		        			+ "," + "\"purqty\"" + ":" +"\"" + jsonList.getPurqty()+  "\""
		        			+ "," + "\"recqty_daij\"" + ":" +"\"" + jsonList.getRecqty_daij()+  "\""
		        			+ "," + "\"qianjiaoshu\"" + ":" +"\"" + jsonList.getQianjiaoshu()+  "\""
		        			+ "," + "\"rtnqty_yij\"" + ":" +"\"" + jsonList.getRtnqty_yij()+  "\""
		        			+ "," + "\"jiecun\"" + ":" +"\"" + jsonList.getJiecun()+  "\""
		        			+ "," + "\"unpicked\"" + ":" +"\"" + jsonList.getUnpicked()+  "\""
		        			+ "," + "\"realusage\"" + ":" +"\"" + jsonList.getRealusage()+  "\""
		        			+ "," + "\"lastname\"" + ":" +"\"" + jsonList.getLastname()+  "\""
		        			+ "," + "\"duedt\"" + ":" +"\"" + jsonList.getDuedt()+  "\""
		        			+ "," + "\"remarks\"" + ":" +"\"" + jsonList.getRemarks()+"\"" 
		        			+ "," + "\"handoverDate\"" + ":" +"\"" + jsonList.getHandoverDate()+  "\""
		        			      			
		        		+ "}";
		        json.append(s1);
		        if(i < list.size()-1) {
			        String comma=",";
			        json.append(comma);
		        }
	        }
	        String s3="]}";
	        json.append(s3);
	        java.util.Date jsondate2 = new java.util.Date();
			System.out.println("End Time: " + jsondate2);
			long jsondiff = jsondate2.getTime() - datejson1.getTime();
			
			
			System.out.println("PrintWriting starting........");
			out.println(json);
	        System.out.println(json);
	        System.out.println("PrintWriting ended........");
	        System.out.println("Elapsed Time: " + jsondiff);	
		}
	}
	
	
	

