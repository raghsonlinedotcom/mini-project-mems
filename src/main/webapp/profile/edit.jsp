<%@page import="com.assignments.java.mems.bo.UserBO"%>
<%@include file="../header.jsp" %>
<h1>Profile - Edit</h1>

<%
UserBO userBO = (UserBO) request.getAttribute("userBO");

String errorMsg = (String) request.getAttribute("errorMsg");

if(null!=errorMsg) {
%>
	<div class="errorMsg"><%= errorMsg %></div>
<%			
}

if(null==userBO) {
%>
	<div class="errorMsg">No User available</div>
<%
}
else 
{

%>
		<form id="editUserForm" name="editUserForm" 
				action="Update" method="post">
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
						<td>
							<input type="text" id="id" name="id"
							readonly value="${userBO.id}"/>
						</td>
					</tr>
					<tr>
						<td>First Name</td>
						<td>
							<input type="text" id="firstName" name="firstName"
							required  size="20"
							placeholder="First Name"
							value="<%= userBO.getFirstName() %>"/>
						</td>
					</tr>	
					<tr>
						<td>Last Name</td>
						<td>
							<input type="text" id="lastName" name="lastName"
							required size="20"
							placeholder="First Name"
							value="${userBO.lastName}"/>
						</td>
					</tr>
					<tr>
						<td>User Name</td>
						<td>						
							<input type="text" id="userName" name="userName"
							required  size="20"
							placeholder="First Name"
							value="${userBO.userName}"/>						
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" name="Update" value="Update Profile"/>
						</td>
						<td>
							<input type="reset" name="Reset" value="Reset Values"/>
						</td>
					</tr>		
				</tbody>
			</table>
		</form>
<%
	}
%>
<%@include file="../footer.jsp" %>