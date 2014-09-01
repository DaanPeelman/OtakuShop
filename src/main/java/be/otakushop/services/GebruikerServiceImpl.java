package be.otakushop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.otakushop.dao.GebruikerDAO;
import be.otakushop.dao.RolDAO;
import be.otakushop.entities.Gebruiker;
import be.otakushop.entities.Rol;
import be.otakushop.exceptions.GebruikerMetDitEmailadresBestaatAlException;

@Service
@Transactional(readOnly = true)
class GebruikerServiceImpl implements GebruikerService {
	private final GebruikerDAO gebruikerDAO;
	private final RolDAO rolDAO;
	
	@Autowired
	public GebruikerServiceImpl(GebruikerDAO gebruikerDAO, RolDAO rolDAO) {
		this.gebruikerDAO = gebruikerDAO;
		this.rolDAO = rolDAO;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void create(Gebruiker gebruiker) {
		Rol klantenrol = rolDAO.findByNaamIs("klant");
		
		if(findByEmailadres(gebruiker.getEmailadres()) != null) {
			throw new GebruikerMetDitEmailadresBestaatAlException();
		}
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		gebruiker.setWachtwoord(encoder.encode(gebruiker.getWachtwoord()));
		gebruiker.addRol(klantenrol);
		gebruiker.setActief(true);
		
		gebruikerDAO.save(gebruiker);
	}
	
	@Override
	public Gebruiker findByEmailadres(String emailadres) {
		return gebruikerDAO.findByEmailadres(emailadres);
	}
}
