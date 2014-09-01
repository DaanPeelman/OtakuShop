package be.otakushop.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	private Serie serie;
	private Uitgever uitgever;
	
	@Before
	public void setUp() {
		serie = new Serie("Test");
		uitgever = new Uitgever("Test");
		
		serieDAO.save(serie);
		uitgeverDAO.save(uitgever);
		
		productDAO.save(new Product("Product1", serie, 1, new Date(), "Test", "Test", uitgever, new BigDecimal(10), 4));
		productDAO.save(new Product("Product2", serie, 1, new Date(), "Test", "Test", uitgever, new BigDecimal(10), 0));
		productDAO.save(new Product("Product3", serie, 1, new Date(), "Test", "Test", uitgever, new BigDecimal(10), 7));
	}
	
	@Test
	public void create() {
		Product product = new Product("Test", serie, 1, new Date(), "Test", "Test", uitgever, new BigDecimal(10), 2);
		productDAO.save(product);
		
		Assert.assertNotEquals(0, product.getId());
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
}
