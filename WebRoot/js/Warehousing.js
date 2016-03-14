
function loadXMLDoc()
{
var xmlhttp;
var begintime=document.getElementById("begintime").value;
var endtime=document.getElementById("endtime").value;
var erp=document.getElementById("erp").value;
var code=document.getElementById("code").value;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
url= 'WarehousingServlet?begintime='+begintime+"&endtime="+endtime+"&erp="+erp+"&code="+code ;
xmlhttp.open("POST",url,true);
xmlhttp.send();
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
	document.getElementById("mydiv").innerHTML=xmlhttp.responseText;
	hide('wait');
    }
  }
}