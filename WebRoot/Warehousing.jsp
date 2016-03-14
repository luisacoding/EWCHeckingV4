<%@ page language="java" import="java.util.*" import="com.app.*" import="com.util.*" import="java.sql.ResultSet" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Warehousing</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link  rel="stylesheet" type="text/css"  href="CSS/style.css"/>
	<link  rel="stylesheet" type="text/css"  href="CSS/nquire.css"/>
	<script type="text/javascript" src="js/calender.js"></script>
	<script type="text/javascript" src="js/Warehousing.js"></script>
  </head>
<script>
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
	show('wait');
	loadXMLDoc();
}
</script>
  <body>
  <div id="wait"  class="white_content" >
 <img  src="images/1.gif" height=50 width=50 style="background-color:#a4bed4" />
</div>
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
      <td style="height:40px">ERP单号:</td>
      <td><input type="text" name="erp" id="erp" style="width:110px; height:20px" /></td>
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
     <table  class="thead" style="width:700px" border="0" cellpadding="3" cellspacing="1">
     <tbody>
     <tr>
     <th style="width:40px" align="center">序号</th>
     <th style="width:200px" align="center">ERP单号</th>
     <th style="width:200px" align="center">物料编码</th>
     <th style="width:130px" align="center">WMS数</th>
     <th style="width:130px" align="center">ERP数</th>
     <th style="width:33px" align="center"><input type="button" value="查询" onclick="show('light')"></th>
     </tr>
     </tbody>
     </table><div id="mydiv">
     </div>
     </div>
  </body>
</html>
