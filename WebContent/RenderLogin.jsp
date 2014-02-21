<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>    
<%@ page import="uk.ac.dundee.computing.aec.stores.UserStore" %>
<%@include file="header.jsp" %>

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
<div >
	<form action="login" method="post">
	Username: <input name="username" class="textbox" /><br />
	Password: <input name="password" class="textbox" /><br />
	<br />
	<input type="submit" value="Submit" />
	</form>
</div>
<div style="position:relative; left:+100px;">
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