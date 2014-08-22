package be.otakushop.dao;

import be.otakushop.entities.Product;

public interface ProductDAO {
	Iterable<Product> findAll();
	Product read(long id);
}
