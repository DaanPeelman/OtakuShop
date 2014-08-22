package be.otakushop.dao;


import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import be.otakushop.entities.Product;

public interface ProductDAO extends JpaRepository<Product, Long> {
	Page<Product> findByStockGreaterThan(Pageable pageable, int stock);	
	/*
	Date findMinUitgifte();
	Date findMaxUitgifte();*/
}
