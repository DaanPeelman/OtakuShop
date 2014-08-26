package be.otakushop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import be.otakushop.dao.GebruikerDAO;
import be.otakushop.dao.RolDAO;
import be.otakushop.entities.Gebruiker;
import be.otakushop.entities.Rol;
import be.otakushop.exceptions.GebruikerMetDitEmailadresBestaatAlException;

@Service
public class GebruikerServiceImpl implements GebruikerService {
	private final GebruikerDAO gebruikerDAO;
	private final RolDAO rolDAO;
	
	@Autowired
	public GebruikerServiceImpl(GebruikerDAO gebruikerDAO, RolDAO rolDAO) {
		this.gebruikerDAO = gebruikerDAO;
		this.rolDAO = rolDAO;
	}
	
	@Override
	public void create(Gebruiker gebruiker) {
		Rol klantenrol = rolDAO.findByNaamIs("klant");
		Gebruiker gebruikerInDatabase = gebruikerDAO.findByEmailadres(gebruiker.getEmailadres());
		
		if(gebruikerInDatabase != null) {
			throw new GebruikerMetDitEmailadresBestaatAlException();
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		gebruiker.setWachtwoord(encoder.encode(gebruiker.getWachtwoord()));
		gebruiker.addRol(klantenrol);
		gebruiker.setActief(true);
		
		gebruikerDAO.save(gebruiker);
	}
}
