package be.otakushop.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.entities.Gebruiker;
import be.otakushop.exceptions.GebruikerMetDitEmailadresBestaatAlException;
import be.otakushop.services.GebruikerService;

@Controller
@RequestMapping("gebruiker")
public class GebruikerController {
	private final GebruikerService gebruikerService;
	
	@Autowired
	public GebruikerController(GebruikerService gebruikerService) {
		this.gebruikerService = gebruikerService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ModelAndView create(@Valid Gebruiker gebruiker, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("login");
		
		if(!bindingResult.hasErrors() && gebruiker.isValid()) {
			try {
				gebruikerService.create(gebruiker);
				
				return new ModelAndView("redirect:/");
			} catch(GebruikerMetDitEmailadresBestaatAlException e) {
				bindingResult.rejectValue("emailadres", "emailadresInGebruik");
			}
		}
		
		if(!gebruiker.isValid()) {
			bindingResult.rejectValue("wachtwoordBevestig", "wachtwoordenNietGelijk");
		}
		
		return modelAndView;
	}
	
//	@RequestMapping(value = "login", method = RequestMethod.GET)
//	ModelAndView aanmeldForm() {
//		ModelAndView modelAndView = new ModelAndView("gebruiker/login");
//		
//		modelAndView.addObject("gebruiker", new Gebruiker());
//		
//		return modelAndView;
//	}
	
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
