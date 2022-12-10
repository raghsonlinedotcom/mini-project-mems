<%@page import="com.assignments.java.mems.bo.UserBO"%>
<%@include file="../header.jsp" %>
<h1>Profile - View</h1>

<%
String errorMsg = (String) request.getAttribute("errorMsg");
String message = (String) request.getAttribute("message");
UserBO userBO = (UserBO) request.getAttribute("userBO");

if(null!=message) {
%>
	<div class="successMsg"><%=message %></div>	
<% 
}

if(null!=errorMsg) {
%>
	<div class="errorMsg"><%=errorMsg %></div>
<%
}
else {

%>
		<table>
			<thead>
				<tr>
					<th>Field</th>
					<th>Value</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Id</td>
					<td>${userBO.id}</td>
				</tr>
				<tr>
					<td>First Name</td>
					<td><%= userBO.getFirstName() %></td>
				</tr>	
				<tr>
					<td>Last Name</td>
					<td>${userBO.lastName}</td>
				</tr>
				<tr>
					<td>User Name</td>
					<td>${userBO.userName}</td>
				</tr>		
			</tbody>
		</table>
		<br/>
		<a href="Edit">Edit Profile</a>
<%
	}
%>
<%@include file="../footer.jsp" %>