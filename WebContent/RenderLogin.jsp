<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@ page import="uk.ac.dundee.computing.aec.stores.UserStore" %>
<%@include file="header.jsp" %>
<%
String msg = (String)request.getAttribute("message");
if (msg != null) {
%><div><%=msg %>
</div>
<%}
%>

<div style="float:left;">

	<h2>Login</h2>
	<form action="login" method="post">
	Username: <input name="username" class="textbox" /><br />
	Password: <input name="password" class="textbox" /><br />
	<br />
	<input type="submit" value="Submit" />
	</form>
</div>
<div style="float:left; position:relative; left:+30px;">
	<h2>Register</h2>
	<form action="register" method="post">
	Username: <input name="username" class="textbox" /><br />
	Password: <input name="password" class="textbox" /><br />
	Email: <input name="email" class="textbox" /><br />
	<br />
	<input type="submit" value="Register" />
	</form>
</div>
</body>
</html>