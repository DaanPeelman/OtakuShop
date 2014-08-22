package be.otakushop.web;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.otakushop.entities.Product;
import be.otakushop.entities.Serie;
import be.otakushop.entities.Uitgever;
import be.otakushop.services.ProductService;
import be.otakushop.web.ProductController;

public class ProductControllerTest {
	private ProductController productController;
	private Iterable<Product> producten;
	private Product product;
	private ProductService productService;
	
	@Before
	public void setUp() {
		producten = Collections.emptyList();
		product = new Product(1L, "Nendoroid Nadeko Sengoku", new Serie(1L, "Bakemonogatori"), 100, new Date(2014, 6, 16),"", "", new Uitgever(1L, "Good Smile Company"), new BigDecimal(43.77), 2);
		
		productService = Mockito.mock(ProductService.class);
		Mockito.when(productService.findAll()).thenReturn(producten);
		Mockito.when(productService.read(1L)).thenReturn(product);
		
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
	
	@Test
	public void readActiveertJuisteView() {
		Assert.assertEquals("producten/product", productController.read(1L).getViewName());
	}
	
	@Test
	public void readMetBestaandeIDGeeftProductTerug() {
		Assert.assertSame(product, productController.read(1L).getModelMap().get("product"));
	}
	
	@Test
	public void readMetOnbestaandeIDGeeftNullTerug() {
		Assert.assertNull(productController.read(999L).getModelMap().get("product"));
	}
}
