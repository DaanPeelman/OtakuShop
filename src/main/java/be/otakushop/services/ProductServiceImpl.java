package be.otakushop.services;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.otakushop.dao.ProductDAO;
import be.otakushop.entities.Product;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
	private final ProductDAO productDAO;
	
	@Autowired
	public ProductServiceImpl(ProductDAO productDAO) {
		this.productDAO = productDAO;
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
}
