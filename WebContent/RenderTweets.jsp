<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
    <%@ page import="uk.ac.dundee.computing.aec.stores.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Tweets</title>
</head>
<body>

<h1>Tweet</h1>
<%
System.out.println("In render");
List<TweetStore> lTweet = (List<TweetStore>)request.getAttribute("Tweets");
if (lTweet==null){
 %>
	<p>No Tweet found</p>
	<% 
}else{
%>

<table>
<% 
Iterator<TweetStore> iterator;


iterator = lTweet.iterator();     
while (iterator.hasNext()){
	TweetStore ts = (TweetStore)iterator.next();
	
	%>
	<tr><td><a href="/ac32007examples/Tweet/<%=ts.getUser() %>" ><%=ts.getUser() %></a></td>
	<td rowspan="2"><%=ts.getTweet() %></a><br/></td>
	</tr>
	<tr><td><%=ts.getDate().toLocaleString() %></td></tr>
	
	<%
}
}
%>

</body>
</html>