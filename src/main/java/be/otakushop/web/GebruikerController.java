package be.otakushop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("gebruiker")
public class GebruikerController {
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	ModelAndView aanmeldForm() {
		return new ModelAndView("gebruiker/login");
	}
}
