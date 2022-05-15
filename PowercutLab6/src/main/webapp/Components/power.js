
$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validatePowerForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidScheduleIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "PowerAPI", 
 type : type, 
 data : $("#formPower").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onPowerSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidScheduleIDSave").val($(this).data("scheduleid")); 
 $("#locationName").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#relatedZone").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#timePeriod").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#scheduleDate").val($(this).closest("tr").find('td:eq(3)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "PowerAPI", 
 type : "DELETE", 
 data : "scheduleID=" + $(this).data("scheduleid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onPowerDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================
function validatePowerForm() 
{ 
// CODE
if ($("#locationName").val().trim() == "") 
 { 
 return "Insert Location Name."; 
 } 
// NAME
if ($("#relatedZone").val().trim() == "") 
 { 
 return "Insert Related Zone."; 
 } 
// PRICE-------------------------------
if ($("#timePeriod").val().trim() == "") 
 { 
 return "Insert Time Period."; 
 } 
// is numerical value
//var tmpPrice = $("#itemPrice").val().trim(); 
//if (!$.isNumeric(tmpPrice)) 
 //{ 
 //return "Insert a numerical value for Item Price."; 
 //} 
// convert to decimal price
 //$("#itemPrice").val(parseFloat(tmpPrice).toFixed(2)); 
// DESCRIPTION------------------------
if ($("#scheduleDate").val().trim() == "") 
 { 
 return "Insert Schedule Date."; 
 } 
return true; 
}

function onPowerSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divPowerGrid").html(resultSet.data); 
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
 $("#hidScheduleIDSave").val(""); 
 $("#formPower")[0].reset(); 
}


function onPowerDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divPowerGrid").html(resultSet.data); 
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




