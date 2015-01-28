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
@RequestMapping("/reset")
public class Reset{
    //    private String name;
    // private String oldPassword;
    // private String newPassword;
    // private String confirmNewPassword;
 
    @RequestMapping(method = RequestMethod.POST)
    public String resetPost(@RequestParam("name") String name,
			    @RequestParam("oldPassword") String oldPassword,
			    @RequestParam("newPassword") String newPassword,
			    @RequestParam("confirmNewPassword") String confirmNewPassword,
			    HttpSession session, 
			    ModelMap model) throws IOException{
	//confirm that the passwords match
	if(!newPassword.equals(confirmNewPassword)){
		model.addAttribute("message","Passwords to not match");
		return "reset";	    }
	    try{                                                                                                                                                                          
		//connect to the url databases                                                                                      
		DBConnector db = new DBConnector();                                                                
		//		String stmt = 

		ResultSet rs = db.executeQuery("select name,password from users");                                                  
		while(rs != null && rs.next()){
		    //check that the user exists
		    if(rs.getString("name").equals(name) && rs.getString("password").equals(oldPassword)){
			db.executeUpdate("UPDATE users set password = '" + newPassword + "' WHERE name ='" + name + "'");
		    }
      		}                                                                                                                                                        
		model.addAttribute("message","password successfully changed");                                                                         
	    } catch(SQLException e){                                                                                                
		System.out.println("Big error stuff");                                                                                                    
            }
	    return "login";
	    }


	
    @RequestMapping(method = RequestMethod.GET)
    public String resetGet(HttpSession session, ModelMap model) {
	    return "reset"; 
    }
}
