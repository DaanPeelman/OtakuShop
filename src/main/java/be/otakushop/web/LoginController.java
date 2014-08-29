package be.otakushop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.entities.Gebruiker;

@Controller
@RequestMapping("login")
public class LoginController {
	private Mandje mandje;
	
	@Autowired
	public LoginController(Mandje mandje) {
		this.mandje = mandje;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView aanmeldForm() {
		ModelAndView modelAndView = new ModelAndView("login");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		modelAndView.addObject("gebruiker", new Gebruiker());
		
		return modelAndView;
	}
	
	@InitBinder("gebruiker")
	public void initBinderGebruiker(DataBinder dataBinder) {
		Gebruiker gebruiker = (Gebruiker)dataBinder.getTarget();
		
		if(gebruiker.getAdres() == null) {
			gebruiker.setAdres(new AdresForm());
		} else {
			gebruiker.setAdres(new AdresForm(gebruiker.getAdres()));
		}
	}
}
