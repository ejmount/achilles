<%@ page import="uk.ac.dundee.computing.aec.stores.*" %>
<%@ page import="java.util.*" %>
<%@include file="header.jsp" %>
<h1>Tweet</h1>
<%
List<TweetStore> lTweet = (List<TweetStore>)request.getAttribute("Tweets");
if (lTweet==null){
 %>
	<p>No Tweets found</p>
	<% 
}else{
%>

<table border="1">
<% 
Iterator<TweetStore> iterator;


iterator = lTweet.iterator();     
while (iterator.hasNext()){
	TweetStore ts = (TweetStore)iterator.next();
	
	%>
	<tr>
	<td colspan=2><%=ts.getDate().toString() %></td>
	</tr>
	<tr>
	<td><a href="/ac32007examples/Tweet/<%=ts.getUser() %>" ><%=ts.getUser() %></a></td>
	<td><%=ts.getTweet() %><br/></td>
	</tr>
	<tr style="min-height:10px;"></tr>
	<%
}
}
%>
</table>

<% if (request.getSession().getAttribute("user") != null ) { %>
<form action="Tweet" method="post">
<table>
<tr><td>Text:</td><td><input name="tweet" maxlength="280" />
<tr><td><BUTTON name="submit" type="submit">Submit</BUTTON></td></tr>
</table>
</form>
<% } %>

</body>
</html>