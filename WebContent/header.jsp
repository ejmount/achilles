<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="uk.ac.dundee.computing.aec.stores.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Achilles</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
<h1>achilles</h1>
<% UserStore use =(UserStore) request.getSession().getAttribute("user");
if (use != null)
{
	%>You are logged in as: <%=use.getUsername() %><br><%
}%>
<hr />
