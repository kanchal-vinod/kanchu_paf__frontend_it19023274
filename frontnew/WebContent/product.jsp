<%@page import="com.productf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/Fund.js"></script>

<meta charset="ISO-8859-1">
<title>product details</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">product mangement</h1>        
				
				<form id="formCustomer" name="formCustomer" method="post" action="productAPI">  
					Product Name:  
					<input id="prname" name="prname" type="text" class="form-control form-control-sm">  
					
					<br> 
					Product published date:  
					<input id="prdate" name="prdate" type="text" class="form-control form-control-sm">  
					
					<br>
					 product description:  
					 <input id="prdes" name="prdes" type="text" class="form-control form-control-sm">  
					 
					 <br> 
				
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="submit" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidpidSave" name="hidpidSave" value="product.jsp"> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>
				 <br>
                   <div id="divItemsGrid">   
					<%    
						productf productObj = new productf();
						out.print(productObj.readproduct());   
					%>  
					
					<br>
					<br>
					 <a href="Login.jsp" class="btn btn-success">Logout</a>
				</div> 
                   
                </div>
            </div>
				  
 			</div>
 		 
 		    
 		
 
	
</body>
</html>