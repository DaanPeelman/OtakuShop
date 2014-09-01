package be.otakushop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.otakushop.entities.Gebruiker;

public interface GebruikerDAO extends JpaRepository<Gebruiker, Long> {
	Gebruiker findByEmailadres(String emailadres);
}
