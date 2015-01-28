/*
campbest
lab 10-2

servlet to return data from AWS
*/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class GetURLs extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
		{
			PrintWriter out = resp.getWriter();

			out.println("<html>");
			out.println("<body>");
			out.println("college");
			out.println("<table>");
			URLItems u = new URLItems();
			ArrayList<URLItem> items = u.getAllItems();
			for(URLItem i:items) {
				out.println("<tr>");
				out.println("<td>" + i.getName() + "</td>");
				out.println("<td>" + i.getKey() + "</td>");
				out.println("<td><a href=\"" + i.getUrl() + "\">" + i.getUrl() + "</a></td>");
				out.println("<td>" + i.getName() + "</td>");
				out.println("</tr>");
			}

			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
		{
			String field = req.getParameter("field");
			PrintWriter out = resp.getWriter();

			out.println("<html>");
			out.println("<body>");
			out.println("POST NOT SUPPORTED");
			out.println("</body>");
			out.println("</html>");
		}
}
