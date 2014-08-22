package be.otakushop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		return new ModelAndView("producten/producten", "producten", productService.findAll());
	}
}
