/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: Logout.java
 * Description:  Handles the user logging out logic
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
@Controller
@RequestMapping("/logout")
public class Logout{
    @RequestMapping(method = RequestMethod.GET)
    public String loginGet(ModelMap model) {
	model.addAttribute("message", "logout successful");

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
        if(name == null || password == null) return false;
	Scanner sc = new Scanner(new File("/opt/jetty/webapps/URLAPPV1/WEB-INF/classes/kuczmama/logins.txt"));
	//check to see if the username and password are in logins.txt
	while(sc.hasNext()){
	    String line = sc.nextLine();
	    String[] split = line.split(" ");
	    if(split.length == 2 && split[0].equals(name) && split[1].equals(password)){
		return true;
	    }
	}
	return false;
     
    }
}
