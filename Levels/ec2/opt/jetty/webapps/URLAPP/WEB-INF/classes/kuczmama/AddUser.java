/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: AddUser.java
 * Description: Adds a new user
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
@RequestMapping("/add_user")
public class AddUser{

    @RequestMapping(method = RequestMethod.POST)
    public String resetPost(@RequestParam("user") String user,
			    @RequestParam("newPassword") String newPassword,
			    @RequestParam("confirmNewPassword") String confirmNewPassword,
			    HttpSession session, 
			    ModelMap model) throws IOException{
	//confirm that the passwords match
	if(!newPassword.equals(confirmNewPassword)){
	    model.addAttribute("message","Passwords to not match");
	    return "add_user";	    }
	try{                                            
	    
	    //connect to the url databases                                                                                      
	    DBConnector db = new DBConnector();                                                                
	    //		String stmt = 

	    ResultSet rs = db.executeQuery("SELECT name FROM users WHERE name='" + user+"'");
                                                  
	    //check that the username doesn't exist
	    if(!rs.next()){
		db.executeUpdate("INSERT INTO users VALUES('" + user + "','" + newPassword + "');");
	    } else {
		//if the username already exists
		model.addAttribute("message",user + " is already taken please try again");
		return "add_user";
	    }
	    model.addAttribute("message","New user successfully added");                                      
	} catch(SQLException e){                                                                                                
	    System.out.println("Big error stuff");                                                                                                    
	}
	return "login";
    }


	
    @RequestMapping(method = RequestMethod.GET)
    public String resetGet(HttpSession session, ModelMap model) {
	return "add_user"; 
    }
}
