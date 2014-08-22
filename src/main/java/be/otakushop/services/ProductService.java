package be.otakushop.services;

import java.math.BigDecimal;
import java.util.Date;

import be.otakushop.entities.Product;

public interface ProductService {
	Iterable<Product> findAll();
	Product read(long id);
	Iterable<Product> findNieuwsteProducten();
	long findMaxPrijs();
	int findMinDatum();
	int findMaxDatum();
}
