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
@RequestMapping("/store")
public class Store{
    
    @RequestMapping(method = RequestMethod.POST)
    public String loginPost(@RequestParam("name") String name,@RequestParam("url") String url, HttpSession session, ModelMap model) throws IOException{
	try{
	Put p = new Put(name, url);
	p.store();
	
	model.addAttribute("message","url successfully stored");
	} catch(Exception e){
	    e.printStackTrace();
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
