package be.otakushop.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.entities.Bestelbon;
import be.otakushop.entities.Gebruiker;
import be.otakushop.entities.Product;
import be.otakushop.services.GebruikerService;
import be.otakushop.services.ProductService;
import be.otakushop.valueobjects.Bestelbonlijn;

@Controller
@RequestMapping("mandje")
class MandjeController {
	private final ProductService productService;
	private final GebruikerService gebruikerService;
	private Mandje mandje;
	
	@Autowired
	MandjeController(ProductService productService, GebruikerService gebruikerService, Mandje mandje) {
		this.productService = productService;
		this.gebruikerService = gebruikerService;
		this.mandje = mandje;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView viewMandje(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("mandje/mandje");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		if(request.getUserPrincipal() != null) {
			Gebruiker gebruiker = gebruikerService.findByEmailadres(request.getUserPrincipal().getName());
			
			modelAndView.addObject("adresGebruiker", gebruiker.getAdres());
		}
		
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
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ModelAndView addProduct(@Valid ProductAankoopForm productAankoopForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("producten/product");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		if(!bindingResult.hasErrors()) {
			if(productAankoopForm.getAantal() > productService.read(productAankoopForm.getProductId()).getStock()) {
				bindingResult.rejectValue("aantal", "teWeinigStock");
				
				modelAndView.addObject("product", productService.read(productAankoopForm.getProductId()));
				
				return modelAndView;
			}
			mandje.addProduct(productAankoopForm.getProductId(), productAankoopForm.getAantal());
			
			return new ModelAndView("redirect:/mandje");
		}
		
		modelAndView.addObject("product", productService.read(productAankoopForm.getProductId()));
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	ModelAndView wijzigMandje(@Valid MandjeForm mandjeForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("mandje/mandje");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		if(!bindingResult.hasErrors()) {
			boolean inStockFout = false;
			
			for(MandjeFormLijn lijn: mandjeForm.getLijnen()) {
				Product product = productService.read(lijn.getId());
				
				if(lijn.getAantal() > product.getStock()) {
					inStockFout = true;
				}
				
				mandje.addProduct(lijn.getId(), lijn.getAantal());
			}
			
			if(!inStockFout) {
				mandje.setAdres(mandjeForm.getAdres());
				
				return new ModelAndView("redirect:/mandje/overzicht");
			}
			
			modelAndView.addObject("nietGenoegInStock", inStockFout);
		}
		
		System.out.println(bindingResult.getAllErrors());
		
		Bestelbon productenInMandje = new Bestelbon();
		for(long id:mandje.getProducten().keySet()) {
			Product product = productService.read(id);
			productenInMandje.addBestelbonlijn(new Bestelbonlijn(product, mandje.getProducten().get(id), product.getPrijs()));
		}
		
		modelAndView.addObject("productenInMandje", productenInMandje);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "overzicht", method = RequestMethod.GET)
	 ModelAndView viewMandjeOverzicht() {
		ModelAndView modelAndView = new ModelAndView("mandje/overzicht");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		Bestelbon bestelbon = new Bestelbon();
		
		for(long productId: mandje.getProducten().keySet()) {
			Product product = productService.read(productId);
			bestelbon.addBestelbonlijn(new Bestelbonlijn(product, mandje.getProducten().get(productId), product.getPrijs()));
		}
		
		bestelbon.setLeverAdres(mandje.getAdres());
		
		modelAndView.addObject("bestelbon", bestelbon);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "{product}/verwijder", method = RequestMethod.GET)
	ModelAndView verwijderProduct(@PathVariable Product product) {
		mandje.deleteProduct(product.getId());		
		
		return new ModelAndView("redirect:/mandje");
	}
	
	@InitBinder("mandjeForm")
	void initBinderGebruiker(DataBinder dataBinder) {
		MandjeForm mandjeForm = (MandjeForm)dataBinder.getTarget();
		
		if(mandjeForm.getAdres() == null) {
			mandjeForm.setAdres(new AdresForm());
		} else {
			mandjeForm.setAdres(new AdresForm(mandjeForm.getAdres()));
		}
	}
}
