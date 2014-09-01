package be.otakushop.dao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import be.otakushop.datasource.CreateTestDataSourceBean;
import be.otakushop.entities.Rol;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreateTestDataSourceBean.class, CreateTestDAOBeans.class })
@Transactional
public class RolDAOTest {
	@Autowired
	private RolDAO rolDAO;
	
	@Before
	public void setUp() {
		rolDAO.save(new Rol("klantje"));
		rolDAO.save(new Rol("klant"));
		rolDAO.save(new Rol("administrator"));
	}
	
	@Test
	public void findByNaamIsKlantGeeftRolMetAlsNaamKlantTerug() {
		Rol rol = rolDAO.findByNaamIs("klant");
		
		Assert.assertTrue(rol.getNaam().equals("klant"));
	}
	
	@Test
	public void findByNaamIsMetNaamVanRolDieNietBestaatGeeftNullTerug() {
		Rol rol = rolDAO.findByNaamIs("verkoper");
		
		Assert.assertNull(rol);
	}
}
