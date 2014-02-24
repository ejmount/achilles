<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="uk.ac.dundee.computing.aec.stores.*" %>
<% 
String p = request.getContextPath(); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Achilles</title>
<style>
@CHARSET "ISO-8859-1";

body {
	font-family: Courier New, monospace;
	background: #000000;
	color: #00A000;	
}
input { 
   	background-color: #000000; 
    border: solid 1px #00FF00; 
    outline: none; 
    padding: 2px 2px;
    color: #00FF00; 
  } 
  </style>
</head>
<body>
<h1>achilles</h1>

<%
UserStore use =(UserStore) request.getSession().getAttribute("user");
if (use != null)
{
	%>You are logged in as: <%=use.isAdmin() ? "<u>" : "" %> <%=use.getUsername() %><%=use.isAdmin() ? "</u>" : "" %><br><%
}%>
<table style="min-width:80%;">
<tr><td><a href="<%=p %>/tweet">Everyone</a></td><% if (use != null) { %><td><!--  <a href="<%=p %>/following">Your Lists</a></td> --><td><a href="<%=p %>/tweet/<%=use.getUsername() %>">Your Messages</a></td><% } %><td><a href="<%=p %>/login">Login / Register</a></td></tr>
</table>
<hr />
