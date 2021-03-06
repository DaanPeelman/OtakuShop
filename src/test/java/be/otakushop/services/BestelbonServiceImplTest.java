package be.otakushop.services;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.otakushop.dao.BestelbonDAO;
import be.otakushop.entities.Bestelbon;
import be.otakushop.entities.Product;
import be.otakushop.entities.Serie;
import be.otakushop.entities.Uitgever;
import be.otakushop.exceptions.NietGenoegInStockException;
import be.otakushop.valueobjects.Bestelbonlijn;

public class BestelbonServiceImplTest {
	private BestelbonService bestelbonService;
	
	@Before
	public void setUp() {
		BestelbonDAO bestelbonDAO = Mockito.mock(BestelbonDAO.class);
		
		bestelbonService = new BestelbonServiceImpl(bestelbonDAO);
	}
	
	@Test(expected = NietGenoegInStockException.class)
	public void bestelbonMetProductDieGeenStockHeeftWerptException() {
		Product product = new Product("Product", new Serie("Serie"), 1, new Date(), "Test", "Test", new Uitgever("Uitgever"), BigDecimal.ONE, 0);
		Bestelbon bestelbon = new Bestelbon(null, null);
		bestelbon.addBestelbonlijn(new Bestelbonlijn(product, 1, BigDecimal.ONE));
		
		bestelbonService.create(bestelbon);
	}
	
	@Test(expected = NietGenoegInStockException.class)
	public void bestelbonMetHogerAantalDanDeStockVanProductWerptException() {
		Product product = new Product("Product", new Serie("Serie"), 1, new Date(), "Test", "Test", new Uitgever("Uitgever"), BigDecimal.ONE, 2);
		Bestelbon bestelbon = new Bestelbon(null, null);
		bestelbon.addBestelbonlijn(new Bestelbonlijn(product, 3, BigDecimal.ONE));
		
		bestelbonService.create(bestelbon);
	}
	
	@Test
	public void bestelbonMetProductDieGenoegStockHeeftWerptGeenException() {
		Product product = new Product("Product", new Serie("Serie"), 1, new Date(), "Test", "Test", new Uitgever("Uitgever"), BigDecimal.ONE, 5);
		Bestelbon bestelbon = new Bestelbon(null, null);
		bestelbon.addBestelbonlijn(new Bestelbonlijn(product, 1, BigDecimal.ONE));
		
		bestelbonService.create(bestelbon);
	}
	
	@Test
	public void productInBestelbonDieGeplaatstIsVermindertStock() {
		Product product = new  Product("Product", new Serie("Serie"), 1, new Date(), "Test", "Test", new Uitgever("Uitgever"), BigDecimal.ONE, 5);
		Bestelbon bestelbon = new Bestelbon(null, null);
		bestelbon.addBestelbonlijn(new Bestelbonlijn(product, 1, BigDecimal.ONE));
		
		bestelbonService.create(bestelbon);
		
		Assert.assertEquals(4, product.getStock());
	}
}
