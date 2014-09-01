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
import be.otakushop.entities.Gebruiker;
import be.otakushop.valueobjects.Adres;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreateTestDataSourceBean.class, CreateTestDAOBeans.class })
@Transactional
public class GebruikerDAOTest {
	@Autowired
	private GebruikerDAO gebruikerDAO;
	
	@Before
	public void setUp() {
		gebruikerDAO.save(new Gebruiker("Gebr1", "Test", new Adres("Test", "Test", 2880, "Test"), "gebr1@gmail.com", "Test123"));
		gebruikerDAO.save(new Gebruiker("Gebr2", "Test", new Adres("Test", "Test", 2880, "Test"), "gebr2@gmail.com", "Test123"));
		gebruikerDAO.save(new Gebruiker("Gebr3", "Test", new Adres("Test", "Test", 2880, "Test"), "gebr3@gmail.com", "Test123"));
		gebruikerDAO.save(new Gebruiker("Gebr4", "Test", new Adres("Test", "Test", 2880, "Test"), "gebr4@gmail.com", "Test123"));
	}
	
	@Test
	public void findByEmailAdresMetOnbestaandEmailadresGeeftNullTerug() {
		Gebruiker gebruiker = gebruikerDAO.findByEmailadres("onbestaand@gmail.com");
		
		Assert.assertNull(gebruiker);
	}
	
	@Test
	public void findByEmailadresMetBestaandEmailadresGeeftGebruikerMetHetzelfdeEmailAdres() {
		String emailadres = "gebr2@gmail.com";
		
		Gebruiker gebruiker = gebruikerDAO.findByEmailadres(emailadres);
		
		Assert.assertTrue(emailadres.equals(gebruiker.getEmailadres()));
	}
}
