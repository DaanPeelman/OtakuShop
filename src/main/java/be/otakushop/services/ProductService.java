package be.otakushop.services;

import be.otakushop.entities.Product;
import be.otakushop.web.ZoekForm;

public interface ProductService {
	Iterable<Product> findAll();
	Product read(long id);
	Iterable<Product> findNieuwsteProducten();
	long findMaxPrijs();
	int findMinDatum();
	int findMaxDatum();
	Iterable<Product> findByZoektermen(ZoekForm zoekform);
}
