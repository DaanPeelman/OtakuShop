package be.otakushop.services;

import be.otakushop.entities.Gebruiker;

public interface GebruikerService {
	void create(Gebruiker gebruiker);
	Gebruiker findByEmailadres(String emailadres);
}
