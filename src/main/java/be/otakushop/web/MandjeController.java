package be.otakushop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import be.otakushop.entities.Bestelbon;
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
		ModelAndView modelAndView = new ModelAndView("mandje");
		
		if(mandje.getProducten().size() > 0) {
			Bestelbon bestelbon = new Bestelbon();
			for(long id:mandje.getProducten().keySet()) {
				Product product = productService.read(id);
				bestelbon.addBestelbonlijn(new Bestelbonlijn(product, mandje.getProducten().get(id), product.getPrijs()));
			}
			
			modelAndView.addObject("mandje", bestelbon);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addProduct(@Validated ProductAankoopForm productAankoopForm) {		
		mandje.addProduct(productAankoopForm.getProductId(), productAankoopForm.getAantal());
		
		return new ModelAndView("redirect:/mandje");
	}
}
