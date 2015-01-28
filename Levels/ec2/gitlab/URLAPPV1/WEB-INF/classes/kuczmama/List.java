/**
 * Mark Kuczmarski
 * CSE 383
 * Code taken from Dr. Campbell
 * List.java
 */
package kuczmama;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpSession;

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

		StringBuffer sb = new StringBuffer();
		sb.append("<div>Existing list of URLs</div>");
		sb.append("<table><tr><td>URL</td></tr>");
		sb.append("<tr><td><a href='http://google.com'>Google</a></td><td><a href='edit?name=google&url=google'>e</a></td></tr>");
		sb.append("</table>");
		model.addAttribute("content",sb.toString());


	   return "main";
      }
}
