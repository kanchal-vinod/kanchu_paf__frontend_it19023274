$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateproductForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidpidSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "productAPI",
		type : t,
		data : $("#formproduct").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onproductSaveComplete(response.responseText, status);
			console.log(response);
		}
	});
}); 

function onproductSaveComplete(response, status){
	if(status == "success")
	{
		console.log(response +  " "+status);
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
			console.log("dataaaaaa");
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	
	
	$("#hidpidSave").val("");
	$("#formproduct")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidpidSave").val($(this).closest("tr").find('#hidpidUpdate').val());     
	$("#prname").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#prdate").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#prdes").val($(this).closest("tr").find('td:eq(2)').text());     
	
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "productAPI",
		type : "DELETE",
		data : "pid=" + $(this).data("pid"),
		dataType : "text",
		complete : function(response, status)
		{
			onproductDeletedComplete(response.responseText, status);
		}
	});
});

function onproductDeletedComplete(response, status)
{
	if(status == "success")
	{
		console.log(response);
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateproductForm() {  
	// NAME  
	if ($("#prname").val().trim() == "")  {   
		return "Insert product name.";  
		
	} 
	
	 // Amount 
	if ($("#prdate").val().trim() == "")  {   
		return "Insert date.";  
	} 
	
	
	// NIC  
	if ($("#prdate").val().trim() == "")  {   
		return "Insert description."; 
		 
	}
	 
	 	

	 
	 return true; 
	 
}
