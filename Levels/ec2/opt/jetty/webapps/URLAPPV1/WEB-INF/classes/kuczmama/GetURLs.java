package kuczmama;
/*
campbest
lab 10-2

servlet to return data from AWS
*/

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class GetURLs
{
    protected static String getURLs()throws IOException
    {
	StringBuilder sb = new StringBuilder();
	sb.append("<html>");
	sb.append("<body>");
	sb.append("<table>");
	sb.append("<tr>");
	sb.append("<td>Name</td>");
	sb.append("<td>Key</td>");
	sb.append("<td>URL</td>");
	sb.append("<td>Delete</td>");
	sb.append("<td>Edit</td>");
	sb.append("</tr>");
	URLItems u = new URLItems();
	ArrayList<URLItem> items = u.getAllItems();
	for(URLItem i:items) {
	    sb.append("<tr>");
	    sb.append("<td>" + i.getName() + "</td>");
	    sb.append("<td>" + i.getKey() + "</td>");
	    sb.append("<td><a href=\"" + i.getUrl() + "\">" + i.getUrl() + "</a></td>");
	    sb.append("<td><a href='delete?"+ 
		      "name="+ i.getName()  +
		      "&key=" + i.getKey()  +
		      "&url=" + i.getUrl()  +
		      "'>delete</a></td>");
	    sb.append("<td><a href='edit?"+ 
		      "name="+ i.getName()  +
		      "&key=" + i.getKey()  +
		      "&url=" + i.getUrl()  +
		      "'>edit</a></td>");
	 
	    sb.append("</tr>");
	}
  
	sb.append("</table>");
	sb.append("</body>");
	sb.append("</html>");
	return sb.toString();
    }
}
