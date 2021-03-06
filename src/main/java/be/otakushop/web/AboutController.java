package be.otakushop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("about")
class AboutController {
	private Mandje mandje;
	
	@Autowired
	AboutController(Mandje mandje) {
		this.mandje = mandje;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	ModelAndView viewAboutUs() {
		ModelAndView modelAndView = new ModelAndView("about");
		
		modelAndView.addObject("aantalInMandje", mandje.getProducten().size());
		
		return modelAndView;
	}
}
