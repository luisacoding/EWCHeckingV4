<%@ page language="java" import="java.util.*" import="com.arun.servlet.*" pageEncoding="UTF-8"  contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link  rel="stylesheet" type="text/css"  href="CSS/style.css"/>
	<link  rel="stylesheet" type="text/css"  href="CSS/nquire.css"/>
	<script type="text/javascript" src="js/calender.js"></script>
	<script type="text/javascript" src="js/Item.js"></script>
  </head>
  <script >
function show(tag){
 var light=document.getElementById(tag);
 light.style.display='block';
 }
function hide(tag){
 var light=document.getElementById(tag);
 light.style.display='none';
}
function calender(){
	hide('light');
	changdate();
	loadXMLDoc();
}
function changdate(){
	var begintime=document.getElementById("begintime").value;
	var endtime=document.getElementById("endtime").value;
	var myDate = new Date();
	if(begintime==""){
	    myDate.setDate(1);
		begintime = myDate.toLocaleDateString();
	}
	if(endtime==""){
		endtime=new Date().toLocaleDateString();
	}
	if(begintime!=""||endtime!=""){
	 document.getElementById("date").innerHTML=begintime+" 至 "+endtime;
	}
}
</script>
  <body onload="loadXMLDoc()">
  <div id="light" class="white_content"  style='position:absolute;z-index:2'>
      <table width="260">
      <tbody>
      <tr>
      <td style="width: 80px; height: 40px" align="left">开始时间:</td>
      <td align="left"><input type="text" id="begintime" name="begintime" style="width:80px; height:20px" align="left" onFocus="HS_setDate(this)" /></td>
      </tr>
      <tr>
      <td style="height:40px">结束时间:</td><td><input type="text" name="endtime" id="endtime" style="width:80px; height:20px" onFocus="HS_setDate(this)" /></td></tr>
      <tr>
      <td style="height:40px">部门:</td>
      <td><input type="text" name="dept" id="dept" style="width:110px; height:20px" /></td>
      </tr>
      <tr><td style="height:40px">物料编码:</td><td><input type="text" name="code" id="code" style="width:110px; height:20px" /></td>
      </tr> 
      <tr>
      <td style="height:80px" colspan="2" align="center"><input type="submit" value="确定" onclick="calender()" />    <input type="button" value="取消" onclick="hide('light')" /></td>
      </tr>
      </tbody>
      </table> 
      </div>
  <div id="scrollTable">
  <%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM");
java.util.Date currentTime = new java.util.Date();//得到当前系统时间
String str_date1 = formatter.format(currentTime); //将日期时间格局化
%>
  <table>
  	<tr>
  	<td style="width:20px"></td>
  	<td style="font-size: 18px">月份:</td>
  	<td style="font-size: 18px" id="date"><%=str_date1%> </td>  	
  	</tr>
  	<tr><td colspan="3" style="height:4px"></td></tr>
  	</table>
     <table  class="thead" style="width:1130px" border="0" cellpadding="3" cellspacing="1">
     <tbody>
     <tr>
     <th style="width:30px" align="center"><input type='checkbox' name='VoteOption1' value=0></th>
     <th style="width:40px" align="center">序号</th>
     <th style="width:100px" align="center">部门</th>
     <th style="width:150px" align="center">物料编码</th>
     <th style="width:150px" align="center">物料名称</th>
     <th style="width:150px" align="center">规格</th>
     <th style="width:100px" align="center">上月结存</th>
     <th style="width:100px" align="center">计划需求</th>
     <th style="width:100px" align="center">已领料</th>
     <th style="width:100px" align="center">已用料</th>
     <th style="width:50px" align="center">单位</th>
     <th style="width:60px" align="center"><input type="button" value="高级查询" onclick="show('light')"></th>
     </tr>
     </tbody>
     </table><div id="mydiv">
     </div>
     </div>
  </body>
</html>
