package be.otakushop.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.entities.Product;
import be.otakushop.services.ProductService;
import be.otakushop.services.UitgeverService;

@Controller
@RequestMapping("/producten")
public class ProductController {
	private final ProductService productService;
	private final UitgeverService uitgeverService;
	
	@Autowired
	public ProductController(ProductService productService, UitgeverService uitgeverService) {
		this.productService = productService;
		this.uitgeverService = uitgeverService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView modelAndView = new ModelAndView("producten/producten");
		
		modelAndView.addObject("producten", productService.findAll());
		modelAndView.addObject("uitgevers", uitgeverService.findAll());
		modelAndView.addObject("maxPrijs", productService.findMaxPrijs());
		modelAndView.addObject("minDatum", productService.findMinDatum());
		modelAndView.addObject("maxDatum", productService.findMaxDatum());
		modelAndView.addObject("zoekForm", new ZoekForm());
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET, params = {"titel", "serie", "uitgever", "startPrijs", "eindPrijs", "startJaar", "eindJaar"})
	public ModelAndView find(@Valid ZoekForm zoekForm, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("producten/producten");
		
		if(!bindingResult.hasErrors()) {
			modelAndView.addObject("producten", productService.findByZoektermen(zoekForm));
			modelAndView.addObject("uitgevers", uitgeverService.findAll());
			
			modelAndView.addObject("selectStartPrijs", zoekForm.getStartPrijs());
			modelAndView.addObject("selectEindPrijs", zoekForm.getEindPrijs());
			modelAndView.addObject("selectStartJaar", zoekForm.getStartJaar());
			modelAndView.addObject("selectEindJaar", zoekForm.getEindJaar());
		}
		
		modelAndView.addObject("maxPrijs", productService.findMaxPrijs());
		modelAndView.addObject("minDatum", productService.findMinDatum());
		modelAndView.addObject("maxDatum", productService.findMaxDatum());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "{product}", method = RequestMethod.GET)
	public ModelAndView read(@PathVariable Product product) {
		return new ModelAndView("producten/product", "product", product);
	}
}
