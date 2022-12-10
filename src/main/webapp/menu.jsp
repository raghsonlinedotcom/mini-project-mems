<%
	String user = (String) session.getAttribute("user");

	if(null!=user) {
%>
		<div class="menu">
			<a href="/MEMS">Home</a> &nbsp; | &nbsp;
			<a href="Admin">Admin</a>&nbsp; | &nbsp;
			<a href="profile.jsp">Profile</a>&nbsp; | &nbsp;
			<a href="Inbox">Inbox</a>&nbsp; | &nbsp;
			<a href="Compose">Compose</a>&nbsp; | &nbsp;
			<a href="Logout">Logout</a>
		</div>
<%
	} else {
%>
		<div class="menu">
			<a href="register.jsp">Register</a>
		</div>
<%		
	}
%>
