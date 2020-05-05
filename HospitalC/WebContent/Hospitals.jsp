<%@ page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospitals Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/hospitals.js"></script> 



</head>
<body >
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>Hospitals MANAGEMENT</h1>
				<form id="formHospital" name="formHospital" method="post" action="hospitals.jsp">  
					Hospital Code:  
					<input id="hCode" name="hCode" type="text" class="form-control form-control-sm">  
					<br> Hospital Name:  
					<input id="hName" name="hName" type="text" class="form-control form-control-sm">  
					<br> Hospital Address:  
					<input id="address" name="address" type="text" class="form-control form-control-sm">  
					<br> Hospital Telephone:  
					<input id="telephone" name="telephone" type="text" class="form-control form-control-sm">  
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidHospitalIDSave" name=hidHospitalIDSave value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divHospitalsGrid">
					<%
						Hospital hospitalObj = new Hospital();
						out.print(hospitalObj.readHospitals());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>