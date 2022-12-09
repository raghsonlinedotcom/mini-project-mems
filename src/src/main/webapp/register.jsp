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
								required placeholder="First Name" value="Thejaswini"/>
						</td>
					</tr>
					<tr>
						<td>
							<label for="lastName">Last Name</label>
						</td>
						<td>
							<input id="lastName" type="text" name="lastName" size="20" 
								required placeholder="Last Name" value="RN"/>
						</td>
					</tr>
					<tr>
						<td>
							<label for="userName">User Name</label>
						</td>
						<td>
							<input id="userName" type="text" name="userName" size="20" 
								required placeholder="Unique userName" value="theju@3"/>
						</td>
					</tr>
					<tr>
						<td>
							<label for="password">Password</label>
						</td>
						<td>
							<input id="password" type="password" name="password" size="20" 
								required placeholder="Password" value="theju@3"/>
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