package be.otakushop.web;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.otakushop.entities.Product;
import be.otakushop.entities.Serie;
import be.otakushop.entities.Uitgever;
import be.otakushop.services.ProductService;
import be.otakushop.services.UitgeverService;
import be.otakushop.web.ProductController;

public class ProductControllerTest {
	private ProductController productController;
	private Iterable<Product> producten;
	private Product product;
	private ProductService productService;
	
	@Before
	public void setUp() {
		producten = Collections.emptyList();
		product = new Product("Nendoroid Nadeko Sengoku", new Serie("Bakemonogatori"), 100, new Date(), "", "", new Uitgever("Good Smile Company"), new BigDecimal(43.77), 2);
		
		productService = Mockito.mock(ProductService.class);
		Mockito.when(productService.findAll()).thenReturn(producten);
		Mockito.when(productService.read(1L)).thenReturn(product);
		
		UitgeverService uitgeverService = Mockito.mock(UitgeverService.class);
		Mandje mandje = Mockito.mock(Mandje.class);
		
		productController = new ProductController(productService, uitgeverService, mandje);
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
		Assert.assertEquals("producten/product", productController.read(product).getViewName());
	}
	
	@Test
	public void readMetBestaandeIDGeeftProductTerug() {
		Assert.assertSame(product, productController.read(product).getModelMap().get("product"));
	}
}
