package be.otakushop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.entities.Product;
import be.otakushop.services.ProductService;

@Controller
@RequestMapping("/producten")
public class ProductController {
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findAll() {
		ModelAndView modelAndView = new ModelAndView("producten/producten");
		
		modelAndView.addObject("producten", productService.findAll());
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
