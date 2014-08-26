package be.otakushop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.otakushop.entities.Serie;

public interface SerieDAO extends JpaRepository<Serie, Long> {
	Iterable<Serie> findByTitelContains(String titel);
}
