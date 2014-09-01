package be.otakushop.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import be.otakushop.datasource.CreateTestDataSourceBean;
import be.otakushop.entities.Uitgever;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreateTestDataSourceBean.class, CreateTestDAOBeans.class })
@Transactional
public class UitgeverDAOTest {
	@Autowired
	UitgeverDAO uitgeverDAO;
	
	@Before
	public void setUp() {
		uitgeverDAO.save(new Uitgever("Daan"));
		uitgeverDAO.save(new Uitgever("Jeroen"));
		uitgeverDAO.save(new Uitgever("Dann"));
	}
	
	@Test
	public void findByNaamGeeftUitgeverMetDezeNaam() {
		Uitgever uitgever = uitgeverDAO.findByNaam("Daan");
		
		Assert.assertTrue("Daan".equals(uitgever.getNaam()));
	}
}
