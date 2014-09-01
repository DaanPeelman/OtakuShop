package be.otakushop.services;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;

import be.otakushop.dao.ProductDAO;
import be.otakushop.dao.SerieDAO;
import be.otakushop.dao.UitgeverDAO;
import be.otakushop.entities.Product;
import be.otakushop.entities.Serie;
import be.otakushop.entities.Uitgever;

public class ProductServiceImplTest {
	@Autowired
	ProductService productService;
	
	@Before
	public void setUp() {
		ProductDAO productDAO = Mockito.mock(ProductDAO.class);
		SerieDAO serieDAO = Mockito.mock(SerieDAO.class);
		UitgeverDAO uitgeverDAO = Mockito.mock(UitgeverDAO.class);
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date datum = new Date();
		try {
			 datum = df.parse("2014/09/01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		List<Product> producten = new ArrayList<>();
		producten.add(new Product("Product4", new Serie("Serie"), 1, datum, "Test", "Test", new Uitgever("Uitgever"), new BigDecimal(28.99), 1));
		
		Pageable pageablePrijs = new PageRequest(0, 1, Direction.DESC, "prijs");
		Mockito.when(productDAO.findAll(pageablePrijs)).thenReturn(new PageImpl<>(producten));
		
		Pageable pageableMinDatum = new PageRequest(0, 1, Direction.ASC, "uitgifte");
		Mockito.when(productDAO.findAll(pageableMinDatum)).thenReturn(new PageImpl<>(producten));
		
		Pageable pageableMaxDatum = new PageRequest(0, 1, Direction.DESC, "uitgifte");
		Mockito.when(productDAO.findAll(pageableMaxDatum)).thenReturn(new PageImpl<>(producten));
		
		productService = new ProductServiceImpl(productDAO, serieDAO, uitgeverDAO);
	}
	
	@Test
	public void findMaxPrijsGeeftHoogstePrijsAfgerond() {
		Assert.assertSame(30L, productService.findMaxPrijs());
	}
	
	@Test
	public void findMinDatumGeeftMinimumDatum() {
		Assert.assertEquals(2014, productService.findMinDatum());
	}
	
	@Test
	public void findMaxDatumGeeftMaximumDatum() {
		Assert.assertEquals(2014, productService.findMaxDatum());
	}
}
