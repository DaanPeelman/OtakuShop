package be.otakushop.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.entities.Bestelbon;
import be.otakushop.valueobjects.Bestelbonlijn;

@Controller
@RequestMapping("bestellingen")
class BestellingController {
	private Mandje mandje;
	
	@Autowired
	public BestellingController(Mandje mandje) {
		this.mandje = mandje;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView viewBestellingen() {
		
		return new ModelAndView("index");
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ModelAndView create() {
		System.out.println("-----MANDJE UITKUISEN EN BESTELLING PLAATSEN-----");
		return new ModelAndView("redirect:/producten");
	}
}
