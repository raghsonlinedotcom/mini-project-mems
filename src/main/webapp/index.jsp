<%@page import="com.assignments.java.mems.dao.UserDAOImpl"%>
<%@page import="com.assignments.java.mems.bo.UserBO"%>
<%@include file="header.jsp" %>
<h1>Welcome to MEMS - Mini Email Messaging System</h1>

<%-- Testing purpose until Login Module is completed --%>
<%
	UserBO userBO = new UserDAOImpl().getUserById(1);
	session.setAttribute("userBO", userBO);
%>

<%
	String errorMsg = (String) request.getAttribute("errorMsg");
	if(null!=errorMsg) {
%>
		<div class="errorMsg">
			<%= errorMsg %>
		</div>
<% 
	}
%>

<%@include file="footer.jsp" %>