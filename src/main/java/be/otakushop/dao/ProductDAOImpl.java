package be.otakushop.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import be.otakushop.entities.Product;
import be.otakushop.entities.Serie;
import be.otakushop.entities.Uitgever;

@Repository
class ProductDAOImpl implements ProductDAO {
	private Map<Long, Product> producten = new ConcurrentHashMap<>();
	
	ProductDAOImpl() {
		producten.put(1L, new Product(1L, "Nendoroid Nadeko Sengoku", new Serie(1L, "Bakemonogatori"), 100, new Date(2014, 6, 16), "", new Uitgever(1L, "Good Smile Company"), new BigDecimal(43.77), 2));
		producten.put(2L, new Product(2L, "Nendoroid Shimakaze", new Serie(2L, "Kantai Collection ~Kan Colle~"), 100, new Date(2014, 4, 22), "", new Uitgever(1L, "Good Smile Company"), new BigDecimal(49.77), 10));
		producten.put(3L, new Product(3L, "Nendoroid Misaka Mikoto", new Serie(3L, "To Aru Kagaku no Railgun S"), 100,new Date(2013, 10, 24), "", new Uitgever(1L, "Good Smile Company"), new BigDecimal(39.81), 0));
		producten.put(4L, new Product(4L, "Nendoroid Saber - Super Moveable", new Serie(4L, "Fate/Stay Night"), 100,new Date(2010, 10, 26), "", new Uitgever(1L, "Good Smile Company"), new BigDecimal(56.82), 1));
		producten.put(5L, new Product(5L, "Nendoroid Miku - Magical Snow Version", new Serie(5L, "Vocaloids"), 100,new Date(2014, 07, 23), "", new Uitgever(1L, "Good Smile Company"), new BigDecimal(54.54), 4));
	}
	
	@Override
	public Iterable<Product> findAll() {
		return producten.values();
	}
}
