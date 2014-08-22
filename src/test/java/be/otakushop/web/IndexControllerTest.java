package be.otakushop.web;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import be.otakushop.services.ProductService;
import be.otakushop.web.IndexController;

public class IndexControllerTest {
	private IndexController indexController;
	
	@Before
	public void setUp() {
		ProductService productService = Mockito.mock(ProductService.class);
		indexController = new IndexController(productService);
	}
	
	@Test
	public void indexActiveertJuisteView() {
		Assert.assertEquals("index", indexController.index().getViewName());
	}
}
