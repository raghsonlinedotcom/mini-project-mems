<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MEMS | Index Page</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css"/>
	</head>
	<body>
		<%@include file="menu.jsp" %>
		<b>Context Root : </b> <%=request.getContextPath()%>
		<br/><br/>
		<b>Present Server Date and Time : </b>
		<span style="background-color: teal; color: white;">
		<%= new java.util.Date()%>
		</span>
		<hr/>	