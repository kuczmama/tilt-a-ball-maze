/*
 * Mark Kuczmarski
 * lab 10-2
 * Servlet to store data on AWS
 *
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class StoreUrls extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
	String name = req.getParameter("uid");
	String url = req.getParameter("url");
	String pwd = req.getParameter("pwd");
	
	PrintWriter out = resp.getWriter();
	out.println("<html>");
        out.println("<body>");
	if(pwd.equals("cse383")){
	    try{
		Put p = new Put(name, url);
		p.store();
	    } catch(Exception e){
		System.err.println("error putting data");
	    }
	    out.println("Success");
	} else {
	    out.println("Error incorrect password");
	}
        out.println("</body>");
        out.println("</html>");

    }
}
