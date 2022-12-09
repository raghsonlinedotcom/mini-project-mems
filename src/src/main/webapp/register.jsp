<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MEMS | Registration</title>
	</head>
	<body>
		<h1>Sign Up</h1>
		<%!
			boolean isError = false;
			String firstName = "Thejaswini";
		%>
		<%
			String errorMsg = (String) request.getAttribute("errorMsg");
			
			if(null!=errorMsg) {
				isError = true;
				firstName = (String) request.getAttribute("firstName");				
			}
		%>
		<form name="signUpForm" action="register" method="post">
			<table border="1">
				<thead>
					<tr>
						<th>Field</th>
						<th>Value</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<label for="firstName">First Name</label>
						</td>
						<td>
							<input id="firstName" type="text" name="firstName" size="20" 
								placeholder="First Name" 
								value="<%= firstName %>"
								 required/>
							<%
								if(isError) {
							%>
								<br/>
								<span style="background-color:yellow; color:red; weight: bold;">
										<%= errorMsg %>
								</span>
							<%
								}
							%>
						</td>
					</tr>
					<tr>
						<td>
							<label for="lastName">Last Name</label>
						</td>
						<td>
							<input id="lastName" type="text" name="lastName" size="20" 
							 	placeholder="Last Name" value="RN"
							 	maxlength="20" required/>
						</td>
					</tr>
					<tr>
						<td>
							<label for="userName">User Name</label>
						</td>
						<td>
							<input id="userName" type="text" name="userName" size="20" 
								placeholder="Unique userName" value="theju@3"
								maxlength="20" required/>
						</td>
					</tr>
					<tr>
						<td>
							<label for="password">Password</label>
						</td>
						<td>
							<input id="password" type="password" name="password" size="20" 
								placeholder="Password" value="theju@3"
								maxlength="20" required/>
						</td>
					</tr>	
					<tr>
						<td>
							<input type="submit" value="Register" name="Submit"/>
						</td>
						<td>
							<input type="reset" value="Reset" name="Reset"/>
						</td>						
					</tr>														
				</tbody>
			</table>
		</form>
	</body>
</html>