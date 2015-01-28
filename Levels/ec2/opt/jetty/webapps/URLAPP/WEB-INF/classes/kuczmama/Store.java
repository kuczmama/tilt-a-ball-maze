/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: Store.java
 * Description:   Allows a user to store a new url
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
import java.net.*;

@Controller
@RequestMapping("/store")
public class Store{

    @RequestMapping(method = RequestMethod.POST)
    public String loginPost(@RequestParam("name") String name,@RequestParam("url") String url, HttpSession session, ModelMap model) throws IOException{

       	try{
	    if(url.matches("https?://.*")){
		Put p = new Put(name, url);
		p.store();	
		model.addAttribute("message","url successfully stored");
	    } else {
		model.addAttribute("message", "please enter a valid url");
		return "store";
	    }
	    return "main";
	} catch(Exception e){
	    System.err.println("error storing url");
	}

	return "main";
    }


	 
    @RequestMapping(method = RequestMethod.GET)
    public String listGet(HttpSession session, ModelMap model) {
	String name = (String) session.getAttribute("name");
	if (name == null || !name.equals("admin")) {
	    model.addAttribute("message","only admins can access the store!");
	    return "main";
	}

	return "store";
    }
}
