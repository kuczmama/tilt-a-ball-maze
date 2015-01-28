/**
 * Mark Kuczmarski
 * URLAPP
 * CSE 383
 * File: Ads.java
 * Description:  Shows revolving banner ads to the user
 */
package kuczmama;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.io.IOException;

@Controller
@RequestMapping("/ads")
public class Ads{
	 
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String printHello(ModelMap model
					   , HttpSession session
					   , @RequestParam(value="index") int index) {
	try{
	return getAds(index);
	} catch (IOException e){
	    return e.toString();
	}
    }

    
    public static String getAds(int i) throws IOException
    {
	StringBuilder sb = new StringBuilder();
	//sb.append("<html>");
	//sb.append("<body>");
	//sb.append("<table>");
	URLItems u = new URLItems();
	ArrayList<AdItem> items = u.getAllAds();
	if(items.get(i).getText() != null){
	    sb.append(items.get(i).getText());
	}
	sb.append("<br>");
	if(items.get(i).getLink() != null){
	    sb.append("<a href='" + items.get(i).getLink() + "'>");
	    if(items.get(i).getImage() != null){
		sb.append("<img src='" + items.get(i).getImage() + "/>");
	    } else {
		sb.append(items.get(i).getLink());
	    }
	    sb.append("</a>");
	}
	//handle if all three are available
	//if(items.get(i).getText() != null){
	//  if(items.get(i).getLink() != null){
	//	sb.append("<h2>" + items.get(i).getText() + "</h2");
	//  }
	//}
	//for(int i = 0; i < items.size(); i++){
	//sb.append("<tr>");
	// sb.append("<td>" + items.get(i).getId() + "</td>");
	//  sb.append("<td>" + items.get(i).getText() + "</td>");
	//  sb.append("<td>" + items.get(i).getLink() + "</td>");
	//  sb.append("<td>" + items.get(i).getImage() + "</td>");
	//  sb.append("</tr>");
	//}
	//sb.append("</table>");
	//sb.append("</body>");
	//sb.append("</html>");
	return sb.toString();
    }
}
