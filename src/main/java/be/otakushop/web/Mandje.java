package be.otakushop.web;

import java.util.Map;

import be.otakushop.valueobjects.Adres;

public interface Mandje {
	void addProduct(long id, int aantal);
	void deleteProduct(long id);
	void clearMandje();
	Map<Long, Integer> getProducten();
	void setProducten(Map<Long, Integer> producten);
	Adres getAdres();
	void setAdres(Adres adres);
}
