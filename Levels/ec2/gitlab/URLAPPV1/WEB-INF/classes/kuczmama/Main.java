/**
 * Mark Kuczmarski
 * CSE 383
 * Code taken from Dr. Campbell
 * Main.java
 */
package kuczmama;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class Main{
	 
    @RequestMapping(method = RequestMethod.GET)
	public String printHello(ModelMap model, HttpSession session) {
		String user = (String)session.getAttribute("name");
		if (user==null) {
		     model.addAttribute("message", "");
		   return "login";
		} 
		else
			return "main";
      }

}
