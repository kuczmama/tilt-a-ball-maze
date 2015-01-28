/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: Login.java
 * Description:  Handles login requests for the user
 */
package kuczmama;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@Controller
@RequestMapping("/login")
public class Login{
    @RequestMapping(method = RequestMethod.GET)
    public String loginGet(ModelMap model) {
	model.addAttribute("message", "");

	return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String loginPost(@RequestParam("name") String name,@RequestParam("password") String pwd, HttpSession session, ModelMap model) throws IOException{
	if (!isValidLogin(name,pwd,model)) {
	    model.addAttribute("name",name);
	    model.addAttribute("message","Bad login - try again");
	    LogWriter.writeToLog("Bad Login - try again");
	    return "login";
	}

	model.addAttribute("message", "Login Successful - welcome " + name);
	LogWriter.writeToLog("Login Successful - welcome " + name);
	session.setAttribute("name",name);

	return "main";
    }
  
    /**
     * Returns true if it is a valid username and password combination
     */
    private boolean isValidLogin(String name, String password,ModelMap model) throws IOException{
	try{                                                                                                                                                                          
	    //connect to the url databases                                                                                      
	    DBConnector db = new DBConnector();                                                             
	    ResultSet rs = db.executeQuery("select name,password from users where name='" 
					   + name + "' and password='"
					   +password+"'");                                                  
	    return !rs.next() == false;

	} catch(SQLException e){
	    System.out.println("Database Error");
	}
	return false;
    }
}
