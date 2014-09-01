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
import be.otakushop.entities.Serie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreateTestDataSourceBean.class, CreateTestDAOBeans.class })
@Transactional
public class SerieDAOTest {
	@Autowired
	SerieDAO serieDAO;
	
	@Before
	public void setUp() {
		serieDAO.save(new Serie("De wondere wereld van Daan"));
		serieDAO.save(new Serie("Het gevaar"));
		serieDAO.save(new Serie("Het Wereldwonder"));
	}
	
	@Test
	public void findByTitelContainsGeeftSeriesMetDeGegevenWoordenInDeTitel() {
		Iterable<Serie> gevondenSeries = serieDAO.findByTitelContains("wereld");
		boolean goedeSerie = true;
		
		for(Serie serie:gevondenSeries) {
			if(!serie.getTitel().contains("wereld")) {
				goedeSerie = false;
			}
		}
		
		Assert.assertTrue(goedeSerie);
	}
}
