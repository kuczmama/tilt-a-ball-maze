/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: List.java
 * Description:  Shows a list of all of the URLs in dynamodb
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
import org.springframework.web.bind.annotation.ResponseBody;  
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpSession;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.sql.*;

@Controller
@RequestMapping("/list")
public class List{
	 
    @RequestMapping(method = RequestMethod.POST)
    public String post(@RequestParam(value="start")int start,
		       @RequestParam(value="end")int end,
		       ModelMap model) {
	model.addAttribute("message", "");
	try{
	    model.addAttribute("content",GetURLs.getURLs(start,end));
	} catch(Exception e){
	    e.printStackTrace();
	}
	return "list";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listGet(HttpSession session, ModelMap model) {
	String name = (String) session.getAttribute("name");
	//check that the name is not equal to null
	if (name == null || "".equals(name)) {
	    model.addAttribute("message","");
	    return "login";
	}
	try{
	    model.addAttribute("content",GetURLs.getURLs(0,5));
	} catch(Exception e){
	    e.printStackTrace();
	}
	return "list";
    }
}
