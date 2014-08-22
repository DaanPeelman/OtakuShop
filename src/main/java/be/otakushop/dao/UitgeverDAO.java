package be.otakushop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.otakushop.entities.Uitgever;

public interface UitgeverDAO extends JpaRepository<Uitgever, Long> {
	Iterable<Uitgever> findByNaamContains(String naam);
}
