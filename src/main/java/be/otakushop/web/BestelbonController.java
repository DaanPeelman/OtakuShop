package be.otakushop.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.entities.Bestelbon;
import be.otakushop.entities.Gebruiker;
import be.otakushop.entities.Product;
import be.otakushop.exceptions.NietGenoegInStockException;
import be.otakushop.services.BestelbonService;
import be.otakushop.services.GebruikerService;
import be.otakushop.services.ProductService;
import be.otakushop.valueobjects.Bestelbonlijn;

@Controller
@RequestMapping("bestellingen")
class BestelbonController {
	private final BestelbonService bestelbonService;
	private final ProductService productService;
	private final GebruikerService gebruikerService;
	
	private Mandje mandje;
	
	@Autowired
	BestelbonController(BestelbonService bestelbonService, ProductService productService, GebruikerService gebruikerService, Mandje mandje) {
		this.bestelbonService = bestelbonService;
		this.productService = productService;
		this.gebruikerService = gebruikerService;
		this.mandje = mandje;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView viewBestellingen() {
		ModelAndView modelAndView = new ModelAndView("index");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ModelAndView create(HttpServletRequest request) {
		Bestelbon bestelbon = new Bestelbon();
		
		for(Long id:mandje.getProducten().keySet()) {
			Product product = productService.read(id);
			bestelbon.addBestelbonlijn(new Bestelbonlijn(product, mandje.getProducten().get(id), product.getPrijs()));
		}
		
		Gebruiker gebruiker = gebruikerService.findByEmailadres(request.getUserPrincipal().getName());

		
		bestelbon.setLeverAdres(mandje.getAdres());
		bestelbon.setGebruiker(gebruiker);
		bestelbon.setDatum(new Date());
		
		try {
			bestelbonService.create(bestelbon);
		} catch(NietGenoegInStockException e) {
			ModelAndView modelAndView = new ModelAndView("mandje/mandje");
			
			if(mandje.getProducten().size() > 0) {
				Bestelbon productenInMandje = new Bestelbon();
				for(long id:mandje.getProducten().keySet()) {
					Product product = productService.read(id);
					productenInMandje.addBestelbonlijn(new Bestelbonlijn(product, mandje.getProducten().get(id), product.getPrijs()));
				}
				
				modelAndView.addObject("productenInMandje", productenInMandje);
				
				MandjeForm mandjeForm;
				if(mandje.getAdres() == null) {
					mandjeForm = new MandjeForm();
				} else {
					mandjeForm = new MandjeForm(mandje.getAdres());
				}
				modelAndView.addObject("mandjeForm", mandjeForm);
			}
			
			modelAndView.addObject("nietGenoegInStock", true);
			
			return modelAndView;
		}
		
		mandje.clearMandje();
		
		ModelAndView modelAndView = new ModelAndView("bestellingen/bestellingsucces");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		modelAndView.addObject("bestelbon", bestelbon);
		
		return modelAndView;
	}
}
