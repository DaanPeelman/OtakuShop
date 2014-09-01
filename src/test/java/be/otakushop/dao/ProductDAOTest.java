package be.otakushop.dao;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import be.otakushop.datasource.CreateTestDataSourceBean;
import be.otakushop.entities.Product;
import be.otakushop.entities.Serie;
import be.otakushop.entities.Uitgever;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreateTestDataSourceBean.class, CreateTestDAOBeans.class })
@Transactional
public class ProductDAOTest {
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private SerieDAO serieDAO;
	@Autowired
	private UitgeverDAO uitgeverDAO;
	
	@Before
	public void setUp() {
		Serie serie = new Serie("Serie");
		Uitgever uitgever = new Uitgever("Uitgever");
		
		serieDAO.save(serie);
		uitgeverDAO.save(uitgever);
		
		productDAO.save(new Product("Product1", serie, 1, new Date(), "Test", "Test", uitgever, new BigDecimal(0), 4));		
		productDAO.save(new Product("Product2", serie, 1, new Date(), "Test", "Test", uitgever, new BigDecimal(5), 0));
		productDAO.save(new Product("Product3", serie, 1, new Date(), "Test", "Test", uitgever, new BigDecimal(8), 7));
		productDAO.save(new Product("Product4", serie, 1, new Date(), "Test", "Test", uitgever, new BigDecimal(11), 0));
	}
	
	@Test
	public void findByStockGreaterThan0GeeftAlleProductenMetEenStockGroterDan0Terug() {
		Pageable pageable = new PageRequest(0, 4, Direction.DESC, "id");
		Iterable<Product> gevondenProducten = productDAO.findByStockGreaterThan(pageable, 0);
		
		boolean legeStock = false;
		
		for(Product product:gevondenProducten) {
			if(product.getStock() == 0) {
				legeStock = true;
			}
		}
		
		Assert.assertFalse(legeStock);
	}
	
	@Test
	public void findByTitelContainsAndPrijsBetweenAndUitgifteBetweenMetParametersGeeftJuisteProductenTerug() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date startDatum = new Date();
		try {
			 startDatum = df.parse("2014/08/31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Iterable<Product> gevondenProducten = productDAO.findByTitelContainsAndPrijsBetweenAndUitgifteBetweenOrderByTitelAsc("Product", new BigDecimal(1), new BigDecimal(10), startDatum, new Date());
		boolean goedProduct = true;
		
		for(Product product:gevondenProducten) {
			if(!product.getTitel().contains("Product") || (product.getPrijs().compareTo(new BigDecimal(1)) == -1) || (product.getPrijs().compareTo(new BigDecimal(10)) == 1)) {
				goedProduct = false;
			}
		}
		
		Assert.assertTrue(goedProduct);
	}
	
	@Test
	public void findByTitelContainsAndPrijsBetweenAndUitgifteBetweenMetEenParameterDieNietOvereenkomtMetEenProductGeeftLegeCollectionTerug() {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date startDatum = new Date();
		try {
			 startDatum = df.parse("2014/08/31");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Iterable<Product> gevondenProducten = productDAO.findByTitelContainsAndPrijsBetweenAndUitgifteBetweenOrderByTitelAsc("Product", new BigDecimal(100), new BigDecimal(200), startDatum, new Date());
		
		Assert.assertFalse(gevondenProducten.iterator().hasNext());
	}
}
