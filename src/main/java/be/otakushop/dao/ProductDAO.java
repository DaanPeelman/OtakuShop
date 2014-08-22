package be.otakushop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.otakushop.entities.Product;

public interface ProductDAO extends JpaRepository<Product, Long> {
}
