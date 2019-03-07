<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>List Customers</title>
	
	<!-- Stylesheet -->
	<link type="text/css"
		rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>

 <div id="wrapper">
	 <div id = "header">
	  <h2> CRM - Customer relationship management </h2>
	 </div>
 </div>
 
 <div id ="container">
 	<div id ="content">
 		
	<!-- add customer button -->
	<input type="button"
	value="Add Customer"
	onclick="window.location.href='showFormForAdd'; return false;"
	class="add-button">
 		
 	<!-- add html table -->
 	<table>
 		<tr>
 			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Action</th>
 		</tr>
 		
 		<!--  loop over customers -->
 		
 		<c:forEach var="tempCustomer" items="${customers}">
 		
 			<!-- construct an "update" link with customer id -->
 			<c:url var="updateLink" value="/customer/showFormForUpdate">
	 			 <c:param name="customerId" value="${tempCustomer.id}"></c:param>
 			</c:url>
 			<tr>
 				<td> ${tempCustomer.firstName}</td>
 				<td> ${tempCustomer.lastName}</td>
 				<td> ${tempCustomer.email}</td>
 				<td>
 					<!-- Display updateLink -->
 					<a href="${updateLink}">Update</a>
 				</td>
 			 </tr>
 		</c:forEach>
 	</table>
 	</div>
 </div>

</body>

</html>