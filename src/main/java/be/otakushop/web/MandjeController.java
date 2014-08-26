package be.otakushop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("mandje")
class MandjeController {
	private Mandje mandje;
	
	@Autowired
	MandjeController(Mandje mandje) {
		this.mandje = mandje;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView viewMandje() {
		ModelAndView modelAndView = new ModelAndView("mandje");
		
		return modelAndView;
	}
}
