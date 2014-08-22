package be.otakushop.services;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.otakushop.dao.ProductDAO;
import be.otakushop.dao.SerieDAO;
import be.otakushop.dao.UitgeverDAO;
import be.otakushop.entities.Product;
import be.otakushop.entities.Serie;
import be.otakushop.entities.Uitgever;
import be.otakushop.web.ZoekForm;

@Service
@Transactional(readOnly = true)
class ProductServiceImpl implements ProductService {
	private final ProductDAO productDAO;
	private final SerieDAO serieDAO;
	private final UitgeverDAO uitgeverDAO;
	
	@Autowired
	public ProductServiceImpl(ProductDAO productDAO, SerieDAO serieDAO, UitgeverDAO uitgeverDAO) {
		this.productDAO = productDAO;
		this.serieDAO = serieDAO;
		this.uitgeverDAO = uitgeverDAO;
	}
	
	@Override
	public Iterable<Product> findAll() {
		return productDAO.findAll();
	}
	
	@Override
	public Product read(long id) {
		return productDAO.findOne(id);
	}
	
	@Override
	public Iterable<Product> findNieuwsteProducten() {
		Pageable pageable = new PageRequest(0, 4, Direction.DESC, "id");
		Page<Product> pageProducten = productDAO.findByStockGreaterThan(pageable, 0);

		List<Product> producten = pageProducten.getContent();

		return producten;
	}
	
	
	@Override
	public long findMaxPrijs() {
		Pageable pageable = new PageRequest(0, 1, Direction.DESC, "prijs");
		Page<Product> pageProducten = productDAO.findAll(pageable);
		
		BigDecimal bdMaxPrijs = pageProducten.getContent().get(0).getPrijs();		
		double prijs = bdMaxPrijs.longValue();
		
		prijs /= 10;
		prijs = (Math.ceil(prijs)) * 10;
		
		return (long)prijs;
	}
	
	@Override
	public int findMinDatum() {
		Pageable pageable = new PageRequest(0, 1, Direction.ASC, "uitgifte");
		Page<Product> pageProducten = productDAO.findAll(pageable);
		
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(pageProducten.getContent().get(0).getUitgifte());
	    int minJaar = cal.get(Calendar.YEAR);

		return minJaar;
	}
	
	@Override
	public int findMaxDatum() {
		Pageable pageable = new PageRequest(0, 1, Direction.DESC, "uitgifte");
		Page<Product> pageProducten = productDAO.findAll(pageable);
		
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(pageProducten.getContent().get(0).getUitgifte());
	    int maxJaar = cal.get(Calendar.YEAR);

		return maxJaar;
	}
	
	@Override
	public Iterable<Product> findByZoektermen(ZoekForm zoekform) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date startDatum = new Date();
		Date eindDatum = new Date();
		try {
			 startDatum = df.parse(String.format("%d/12/31", zoekform.getStartJaar() - 1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			eindDatum = df.parse(String.format("%d/01/01", zoekform.getEindJaar() + 1));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ArrayList<Product> producten =  (ArrayList<Product>)productDAO.findByTitelContainsAndPrijsBetweenAndUitgifteBetween(zoekform.getTitel(), new BigDecimal(zoekform.getStartPrijs()), new BigDecimal(zoekform.getEindPrijs()), startDatum, eindDatum);
		Map<Long, Product> mapProducten = new ConcurrentHashMap<>();
		
		for(Product product:producten) {
			mapProducten.put(product.getId(), product);
		}
		
		
		if(zoekform.getSerie() != null && !zoekform.getSerie().equals("")) {
			Iterable<Serie> series = serieDAO.findByTitelContains(zoekform.getSerie());
			for(Product product:mapProducten.values()) {
				boolean gevonden = false;
				
				for(Serie serie:series) {
					if(product.getSerie() == serie) {
						gevonden = true;
					}
				}
				
				if(gevonden == false) {
					mapProducten.remove(product.getId());
				}
			}
		}
		
		if(zoekform.getUitgever() != null && !zoekform.getUitgever().equals("-")) {
			Iterable<Uitgever> uitgevers = uitgeverDAO.findByNaamContains(zoekform.getUitgever());
			for(Product product:mapProducten.values()) {
				boolean gevonden = false;
				
				for(Uitgever uitgever:uitgevers) {
					if(product.getUitgever() == uitgever) {
						gevonden = true;
					}
				}
				
				if(gevonden == false) {
					mapProducten.remove(product.getId());
				}
			}
		}
		
		return mapProducten.values();
	}
}
