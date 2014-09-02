package be.otakushop.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FoutController {
	@ExceptionHandler(Exception.class)
	public String foutPagina() {
		return "fout";
	}
}
