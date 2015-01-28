/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: Edit.java
 * Description:  Allows a user to edit an item
 */


package kuczmama;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpSession;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.sql.*;
 
@Controller
@RequestMapping("/edit")
public class Edit{
    private String name;
    private String key;
    private String url;
    
    @RequestMapping(method = RequestMethod.POST)
    public String editPost(@RequestParam("newurl") String newUrl, HttpSession session, ModelMap model,HttpServletRequest req) throws IOException{
	URLItems u = new URLItems();
	//String url = req.getParameter("url");
	u.updateItem(name,key,url,newUrl);
	//model.addAttribute("url",url);
	model.addAttribute("content","Successfully edited " + Sanitize.forHTML(url) + " to the new url: " + Sanitize.forHTML(newUrl));
	return "main";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String editGet(HttpSession session, ModelMap model,@RequestParam("name") String name,@RequestParam("key") String key,@RequestParam("url") String url) {
	String user = (String) session.getAttribute("name");
	if (user == null || !user.equals("admin")) {
	    model.addAttribute("message","only admins can edit urls");
	    return "main";
	}

	this.name = name;
	this.key = key;
	this.url = url;
	model.addAttribute("url",Sanitize.forHTML(url));
	return "edit";
    }
}
