package be.otakushop.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.otakushop.dao.GebruikerDAO;
import be.otakushop.dao.RolDAO;
import be.otakushop.entities.Gebruiker;
import be.otakushop.entities.Rol;
import be.otakushop.exceptions.GebruikerMetDitEmailadresBestaatAlException;
import be.otakushop.valueobjects.Adres;

public class GebruikerServiceImplTest {
	private GebruikerService gebruikerService;
	
	@Before
	public void setUp() {
		GebruikerDAO gebruikerDAO = Mockito.mock(GebruikerDAO.class);
		RolDAO rolDAO = Mockito.mock(RolDAO.class);
		
		Gebruiker gebruiker = new Gebruiker("Daan", "Peelman", new Adres("Omgangstraat", "137", 2880, "Mariekerke"), "daanpeelman@gmail.com", "Test123");
		Mockito.when(gebruikerDAO.findByEmailadres("daanpeelman@gmail.com")).thenReturn(gebruiker);
		Rol rol = new Rol("klant");
		Mockito.when(rolDAO.findByNaamIs("klant")).thenReturn(rol);
		
		gebruikerService = new GebruikerServiceImpl(gebruikerDAO, rolDAO);
	}
	
	@Test
	public void createMetNieuwEmailadresWerptGeenException() {
		Gebruiker nieuweGebruiker = new Gebruiker("Test", "Test", new Adres("Test", "1", 9600, "Test"), "test@gmail.com", "Test123");
		
		gebruikerService.create(nieuweGebruiker);
	}
	
	@Test(expected = GebruikerMetDitEmailadresBestaatAlException.class)
	public void createMetEenBestaandEmailadresWerptException() {
		Gebruiker nieuweGebruiker = new Gebruiker("Test", "Test", new Adres("Test", "1", 9600, "Test"), "daanpeelman@gmail.com", "Test123");
		
		gebruikerService.create(nieuweGebruiker);
	}
	
	@Test
	public void wachtwoordenVanGebruikerWordenGehashed() {
		Gebruiker nieuweGebruiker = new Gebruiker("Test", "Test", new Adres("Test", "1", 9600, "Test"), "test@gmail.com", "Test123");
		String oudWachtwood = nieuweGebruiker.getWachtwoord();
		
		gebruikerService.create(nieuweGebruiker);
		
		Assert.assertNotEquals(oudWachtwood, nieuweGebruiker.getWachtwoord());
	}
	
	@Test
	public void nieuweGebruikerIsEenKlant() {
		Gebruiker nieuweGebruiker = new Gebruiker("Test", "Test", new Adres("Test", "1", 9600, "Test"), "test@gmail.com", "Test123");
		
		gebruikerService.create(nieuweGebruiker);
		boolean isKlant = false;
		
		for(Rol rol:nieuweGebruiker.getRollen()) {
			if(rol.getNaam().equals("klant")) {
				isKlant = true;
			}
		}
		
		Assert.assertTrue(isKlant);
	}
	
	@Test
	public void nieuweGebruikerIsActief() {
		Gebruiker nieuweGebruiker = new Gebruiker("Test", "Test", new Adres("Test", "1", 9600, "Test"), "test@gmail.com", "Test123");
		
		gebruikerService.create(nieuweGebruiker);
		
		Assert.assertTrue(nieuweGebruiker.isActief());
	}
}
