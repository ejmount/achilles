<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    
    <%@ page import="uk.ac.dundee.computing.aec.stores.UserStore" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
String msg = (String)request.getAttribute("message");
if (msg != null) {
%><%=msg %>
<%}
UserStore use =(UserStore) request.getSession().getAttribute("user");
if (use != null)
{
	%>You are logged in as: <%=use.getUsername() %><br><%
}
%>
<form action="login" method="post">
Username: <input name="username" /><br />
Password: <input name="password" /><br />
<input type="submit" value="Submit" />
</form>
</body>
</html>