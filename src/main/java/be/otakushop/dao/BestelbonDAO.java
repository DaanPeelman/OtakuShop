package be.otakushop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.otakushop.entities.Bestelbon;

public interface BestelbonDAO extends JpaRepository<Bestelbon, Long> {
}
