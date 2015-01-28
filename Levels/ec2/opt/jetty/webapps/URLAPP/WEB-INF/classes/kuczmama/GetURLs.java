/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: GetURLs.java
 * Description:  Shows a list of all of the URLs in dynamodb
 *
 * Note : some code taken from Dr. Campbell
 */
package kuczmama;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class GetURLs
{
    protected static String getURLs(int start, int end)throws IOException
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
	//make sure that start or end aren't out of range
	if(start > end){
	    throw new IOException("Start > end exception");
	}
	start = (start < 0)?0:start;
	//end = (end > items.size())? items.size() : end;
	if(end > items.size()){
	    end = items.size();
	    start = end - items.size() % 5;
	}
	for(int i = start; i < end; i++) {
	    sb.append("<tr>");
	    sb.append("<td>" + items.get(i).getName() + "</td>");
	    sb.append("<td>" + items.get(i).getKey() + "</td>");
	    sb.append("<td><a href=\"" + items.get(i).getUrl() + "\">" + items.get(i).getUrl() + "</a></td>");
	    sb.append("<td><a href='delete?"+ 
		      "name="+ items.get(i).getName()  +
		      "&key=" + items.get(i).getKey()  +
		      "&url=" + items.get(i).getUrl()  +
		      "'>delete</a></td>");
	    sb.append("<td><a href='edit?"+ 
		      "name="+ items.get(i).getName()  +
		      "&key=" + items.get(i).getKey()  +
		      "&url=" + items.get(i).getUrl()  +
		      "'>edit</a></td>");
	 
	    sb.append("</tr>");
	}
  
	sb.append("</table>");
	sb.append("</body>");
	sb.append("</html>");
	return sb.toString();
    }
}
