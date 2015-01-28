<%
	String username = (String)session.getAttribute( "username" );
%>
<% if(username == null){ %>
   Please login <a href="page1.jsp">here</a>
<%} else {%>
<% 
   Integer count = (Integer)session.getAttribute( "count" );
   if(count == null){
	session.setAttribute("count", 1);
   } else {
        session.setAttribute("count",((Integer)count)+1);
   }%>
       Welcome, <%= username %><br />
       You have logged in  <%= count %> times.
<%} %>
