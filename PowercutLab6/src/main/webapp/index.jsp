<%@page import="model.Power"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("locationName") != null) 
{ 
 Power powerObj = new Power(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidScheduleIDSave") == "") 
 { 
 stsMsg = powerObj.insertPower(request.getParameter("locationName"), 
 request.getParameter("relatedZone"), 
 request.getParameter("timePeriod"), 
 request.getParameter("scheduleDate")); 
 } 
else//Update----------------------
 { 
 stsMsg = powerObj.updatePower(request.getParameter("hidScheduleIDSave"), 
 request.getParameter("locationName"), 
 request.getParameter("relatedZone"), 
 request.getParameter("timePeriod"), 
 request.getParameter("scheduleDate")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidScheduleIDDelete") != null) 
{ 
 Power powerObj = new Power(); 
 String stsMsg = 
 powerObj.deletePower(request.getParameter("hidScheduleIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Cut Schedule Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/power.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Power Cut Schedule Management </h1>
<form id="formPower" name="formPower">
 Location name: 
 <input id="locationName" name="locationName" type="text" 
 class="form-control form-control-sm">
 <br> Related zone: 
 <input id="relatedZone" name="relatedZone" type="text" 
 class="form-control form-control-sm">
 <br> Time period: 
 <input id="timePeriod" name="timePeriod" type="text" 
 class="form-control form-control-sm">
 <br> Schedule Date: 
 <input id="scheduleDate" name="scheduleDate" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidScheduleIDSave" 
 name="hidScheduleIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divPowerGrid">
 <%
 Power powerObj = new Power(); 
 out.print(powerObj.readPower()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
