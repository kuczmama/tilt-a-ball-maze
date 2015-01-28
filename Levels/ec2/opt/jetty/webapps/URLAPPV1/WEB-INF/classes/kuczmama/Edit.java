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
	 
    @RequestMapping(method = RequestMethod.GET)
    public String editGet(HttpSession session, ModelMap model,@RequestParam("name") String name,@RequestParam("key") String key,@RequestParam("url") String url) {
	//URLItems u = new URLItems();
	//u.deleteItem(name,key,url);
	//model.addAttribute("content","Successfully edited " + key + " " + url + " " + name);
	return "store";
    }
}
