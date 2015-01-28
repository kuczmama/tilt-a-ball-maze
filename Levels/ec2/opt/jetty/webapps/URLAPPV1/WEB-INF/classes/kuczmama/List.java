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
@RequestMapping("/list")
public class List{
	 
    @RequestMapping(method = RequestMethod.POST)
    public String post(ModelMap model) {
	model.addAttribute("message", "");

	return "main";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listGet(HttpSession session, ModelMap model) {
	String name = (String) session.getAttribute("name");
	if (name == null || "".equals(name)) {
	    model.addAttribute("message","");
	    return "login";
	}
 	try{
	    model.addAttribute("content",GetURLs.getURLs());
	} catch(Exception e){
	    e.printStackTrace();
	}
	return "main";
    }
}
