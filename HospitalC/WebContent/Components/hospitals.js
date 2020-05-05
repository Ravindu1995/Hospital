$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	}  
	$("#alertError").hide(); }); 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateHospitalForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidHospitalIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( 
	{  
		url : "HospitalsAPI",  
		type : type,  
		data : $("#formHospital").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onHospitalSaveComplete(response.responseText, status);  
		} 
	}); 
}); 

function onHospitalSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divHospitalsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidHospitalIDSave").val("");  
	$("#formHospital")[0].reset(); 
	
	
} 
 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidHospitalIDSave").val($(this).closest("tr").find('#hidHospitalIDUpdate').val());     
	$("#hCode").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#hName").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#telephone").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 

//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "HospitalsAPI",   
		type : "DELETE",   
		data : "hID=" + $(this).data("hid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onHospitalDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onHospitalDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divHospitalsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateHospitalForm() 
{  
	// CODE  
	if ($("#hCode").val().trim() == "")  
	{   
		return "Insert Hospital Code.";  
	} 
 
	// NAME  
	if ($("#hName").val().trim() == "")  
	{   
		return "Insert Hospital Name.";  
	} 
	//PRICE-------------------------------  
	if ($("#address").val().trim() == "")  
	{   
		return "Insert Hospital address.";  
	} 

	// is numerical value  
	/*var tmpPrice = $("#itemPrice").val().trim();  
	if (!$.isNumeric(tmpPrice))  
	{   
		return "Insert a numerical value for Item Price.";  
	} 

	// convert to decimal price  
	$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2)); */

	// DESCRIPTION------------------------  
	if ($("#telephone").val().trim() == "")  
	{   
		return "Insert Hospital telephone.";  
	} 

	return true; 
}