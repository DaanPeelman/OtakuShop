package be.otakushop.services;

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
}
