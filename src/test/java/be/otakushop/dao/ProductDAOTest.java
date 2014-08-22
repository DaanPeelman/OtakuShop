package be.otakushop.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Test
	public void create() {
		Serie serie = new Serie(1L, "Test");
		Uitgever uitgever = new Uitgever(1L, "Test");
		Product product = new Product("Test", serie, 1, new Date(), "Test", "Test", uitgever, new BigDecimal(10), 2);
		productDAO.save(product);
		Assert.assertNotEquals(0, product.getId());
	}
}
