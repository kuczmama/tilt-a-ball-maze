package campbest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class Login{
	 
    @RequestMapping(method = RequestMethod.GET)
	public String loginGet(ModelMap model) {
	     model.addAttribute("message", "");

	   return "login";
      }

   @RequestMapping(method = RequestMethod.POST)
	public String loginPost(@RequestParam("name") String name,@RequestParam("password") String pwd, HttpSession session, ModelMap model) {
		if (name == null || !name.equals("campbest") || pwd==null || !pwd.equals("cse")) {
			model.addAttribute("name",name);
			model.addAttribute("message","Bad login - try again");
			return "login";
		}

	     model.addAttribute("message", "Login Successful - welcome " + name);
	     session.setAttribute("name",name);


	   return "main";
      }
}
