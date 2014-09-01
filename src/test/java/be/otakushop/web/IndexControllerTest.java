package be.otakushop.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.otakushop.services.ProductService;

public class IndexControllerTest {
	private IndexController indexController;
	
	@Before
	public void setUp() {
		ProductService productService = Mockito.mock(ProductService.class);
		Mandje mandje = Mockito.mock(Mandje.class);
		indexController = new IndexController(productService, mandje);
	}
	
	@Test
	public void indexActiveertJuisteView() {
		Assert.assertEquals("index", indexController.index().getViewName());
	}
}
