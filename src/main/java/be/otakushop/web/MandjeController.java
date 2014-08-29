package be.otakushop.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.entities.Bestelbon;
import be.otakushop.entities.Gebruiker;
import be.otakushop.entities.Product;
import be.otakushop.services.ProductService;
import be.otakushop.valueobjects.Bestelbonlijn;

@Controller
@RequestMapping("mandje")
class MandjeController {
	private final ProductService productService;
	private Mandje mandje;
	
	@Autowired
	MandjeController(ProductService productService, Mandje mandje) {
		this.productService = productService;
		this.mandje = mandje;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewMandje() {
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
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addProduct(@Valid ProductAankoopForm productAankoopForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("producten/product");
		
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
	public ModelAndView wijzigMandje(@Valid MandjeForm mandjeForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("mandje/mandje");
		
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
	public ModelAndView viewMandjeOverzicht() {
		ModelAndView modelAndView = new ModelAndView("mandje/overzicht");
		
		Bestelbon bestelbon = new Bestelbon();
		
		for(long productId: mandje.getProducten().keySet()) {
			Product product = productService.read(productId);
			bestelbon.addBestelbonlijn(new Bestelbonlijn(product, mandje.getProducten().get(productId), product.getPrijs()));
		}
		
		bestelbon.setLeverAdres(mandje.getAdres());
		
		modelAndView.addObject("bestelbon", bestelbon);
		
		return modelAndView;
	}
	
	@InitBinder("mandjeForm")
	public void initBinderGebruiker(DataBinder dataBinder) {
		MandjeForm mandjeForm = (MandjeForm)dataBinder.getTarget();
		
		if(mandjeForm.getAdres() == null) {
			mandjeForm.setAdres(new AdresForm());
		} else {
			mandjeForm.setAdres(new AdresForm(mandjeForm.getAdres()));
		}
	}
}
