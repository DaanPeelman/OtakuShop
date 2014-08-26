package be.otakushop.web;

import java.util.Map;

import be.otakushop.valueobjects.Adres;

public interface Mandje {
	void addProduct(long id, int aantal);
	void deleteProduct(long id);
	void clearMandje();
	Map<Long, Integer> getProducten();
	Adres getAdres();
}
