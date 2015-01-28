/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: Delete.java
 * Description:  Allows a user to delete an item
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
@RequestMapping("/delete")
public class Delete{
	private String key, url, name;
	@RequestMapping(method = RequestMethod.POST)
		public String deletePost(HttpSession session, ModelMap model,HttpServletRequest req) {
			String submit = req.getParameter("submit");
			String url = req.getParameter("url");
			if(submit != null && submit.equals("yes")){
				String name = req.getParameter("name");
				String key = req.getParameter("key");
				URLItems u = new URLItems();
				u.deleteItem(name,key,url);
				model.addAttribute("message","successfully deleted " + url);
			}
			else
				model.addAttribute("message","did not delete " + url);
			return "main";
		}

	@RequestMapping(method = RequestMethod.GET)
		public String editGet(HttpSession session, ModelMap model,@RequestParam("name") String name,@RequestParam("key") String key,@RequestParam("url") String url) {
			this.name = name;
			this.key = key;
			this.url = url;
			model.addAttribute("name",name);
			model.addAttribute("key",key);
			model.addAttribute("url",url);
			model.addAttribute("message","Are you sure you want to delete " + url);
			return "delete";
		}
}
