<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp" %>
<h1>Error</h1>
<h2><%
String err = (String)request.getAttribute("error");
if (err != null) {
%>
<%=err.toString() %></h2>
<%} %>
</body>
</html>