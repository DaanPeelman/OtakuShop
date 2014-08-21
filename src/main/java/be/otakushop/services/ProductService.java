package be.otakushop.services;

import be.otakushop.entities.Product;

public interface ProductService {
	Iterable<Product> findAll();
}
