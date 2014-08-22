package be.otakushop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.otakushop.dao.ProductDAO;
import be.otakushop.entities.Product;

@Service
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
		return productDAO.read(id);
	}
}
