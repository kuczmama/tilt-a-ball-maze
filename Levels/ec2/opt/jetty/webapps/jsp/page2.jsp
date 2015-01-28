<%
	String username = request.getParameter( "username" );
	String password = request.getParameter( "password" );
	session.setAttribute("username", username);
	session.setAttribute("password", password);	
%>
<% if(username == null){ %>
      Please login <a href="page1.jsp">here</a>
<%} else {%>
Welcome, <%= username %><br />
<% } %>
<a href="page3.jsp">Continue to page 3</a>
