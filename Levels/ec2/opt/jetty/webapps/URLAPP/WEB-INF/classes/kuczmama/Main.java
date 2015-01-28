/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: Main.java
 * Description:  The main page of the site after loggin in
 */
package kuczmama;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class Main{
	 
    @RequestMapping(method = RequestMethod.GET)
    public String printHello(ModelMap model, HttpSession session) {
	String user = (String)session.getAttribute("name");
	if (user==null) {
	    model.addAttribute("message", "");
	    // model.addAttribute("content",getAds(2));
	    return "login";
	} 
	else{
	    //model.addAttribute("content",getAds(2));
	    return "main";
	}
    }

    /*
    protected static String getAds(int ii) throws IOException
    {
	StringBuilder sb = new StringBuilder();
	sb.append("<html>");
	sb.append("<body>");
	sb.append("<table>");
	URLItems u = new URLItems();
	ArrayList<AdItem> items = u.getAllAds();
	for(int i = 0; i < items.size(); i++){
	    sb.append("<tr>");
	    sb.append("<td> ID " + items.get(i).getId() + "</td>");
	    sb.append("<td> TEXT " + items.get(i).getText() + "</td>");
	    sb.append("<td> LINK " + items.get(i).getLink() + "</td>");
	    sb.append("<td> IMAGE " + items.get(i).getImage() + "</td>");
	    sb.append("</tr>");
	}
	sb.append("</table>");
	sb.append("</body>");
	sb.append("</html>");
	return sb.toString();
	}*/
}
