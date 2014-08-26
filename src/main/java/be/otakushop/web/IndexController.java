package be.otakushop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.services.ProductService;

@Controller
@RequestMapping("/")
public class IndexController {
	private final ProductService productService;
	
	@Autowired
	public IndexController(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView("index");
		
		modelAndView.addObject("producten", productService.findNieuwsteProducten());
		modelAndView.addObject("productAankoopForm", new ProductAankoopForm());
		
		return modelAndView;
	}
}
