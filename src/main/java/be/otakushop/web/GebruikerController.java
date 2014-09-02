package be.otakushop.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.entities.Gebruiker;
import be.otakushop.exceptions.GebruikerMetDitEmailadresBestaatAlException;
import be.otakushop.services.GebruikerService;

@Controller
@RequestMapping("gebruiker")
class GebruikerController {
	private final GebruikerService gebruikerService;
	private Mandje mandje;
	
	@Autowired
	GebruikerController(GebruikerService gebruikerService, Mandje mandje) {
		this.gebruikerService = gebruikerService;
		this.mandje = mandje;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView showGegevens(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("gebruiker/gebruiker");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		modelAndView.addObject("gebruiker", gebruikerService.findByEmailadres(request.getUserPrincipal().getName()));
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ModelAndView create(@Valid Gebruiker gebruiker, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("login");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		if(!bindingResult.hasErrors() && gebruiker.isValid()) {
			try {
				ModelAndView succesModelAndView = new ModelAndView("gebruiker/registratieSucces");
				succesModelAndView.addObject("aantalInMandje", mandje.getProducten().size());
				
				gebruikerService.create(gebruiker);
				
				return succesModelAndView;
			} catch(GebruikerMetDitEmailadresBestaatAlException e) {
				bindingResult.rejectValue("emailadres", "emailadresInGebruik");
			}
		}
		
		if(!gebruiker.isValid()) {
			bindingResult.rejectValue("wachtwoordBevestig", "wachtwoordenNietGelijk");
		}
		
		return modelAndView;
	}
	
	@InitBinder("gebruiker")
	void initBinderGebruiker(DataBinder dataBinder) {
		Gebruiker gebruiker = (Gebruiker)dataBinder.getTarget();
		
		if(gebruiker.getAdres() == null) {
			gebruiker.setAdres(new AdresForm());
		} else {
			gebruiker.setAdres(new AdresForm(gebruiker.getAdres()));
		}
	}
}
