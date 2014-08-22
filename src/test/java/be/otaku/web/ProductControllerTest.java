package be.otaku.web;

import java.util.Collections;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.otakushop.entities.Product;
import be.otakushop.services.ProductService;
import be.otakushop.web.ProductController;

public class ProductControllerTest {
	private ProductController productController;
	private Iterable<Product> producten;
	private ProductService productService;
	
	@Before
	public void setUp() {
		producten = Collections.emptyList();
		productService = Mockito.mock(ProductService.class);
		Mockito.when(productService.findAll()).thenReturn(producten);
		productController = new ProductController(productService);
	}
	
	@Test
	public void findAllActiveertJuisteView() {
		Assert.assertEquals("producten/producten", productController.findAll().getViewName());
	}
	
	@Test
	public void findAllMaaktRequestAttribuutProducten() {
		Assert.assertSame(producten, productController.findAll().getModelMap().get("producten"));
	}
}
