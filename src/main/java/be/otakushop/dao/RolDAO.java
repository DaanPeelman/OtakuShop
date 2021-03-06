package be.otakushop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.otakushop.entities.Rol;

public interface RolDAO extends JpaRepository<Rol, Long> {
	Rol findByNaamIs(String naam);
}
