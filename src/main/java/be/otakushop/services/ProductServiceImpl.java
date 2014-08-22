package be.otakushop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
}
