package be.otakushop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.otakushop.dao.BestelbonDAO;
import be.otakushop.dao.ProductDAO;
import be.otakushop.entities.Bestelbon;
import be.otakushop.entities.Product;
import be.otakushop.exceptions.NietGenoegInStockException;
import be.otakushop.valueobjects.Bestelbonlijn;

@Service
@Transactional(readOnly = true)
public class BestelbonServiceImpl implements BestelbonService {
	private final BestelbonDAO bestelbonDAO;
	private final ProductDAO productDAO;
	
	@Autowired
	public BestelbonServiceImpl(BestelbonDAO bestelbonDAO, ProductDAO productDAO) {
		this.bestelbonDAO = bestelbonDAO;
		this.productDAO = productDAO;
	}
	
	@Transactional(readOnly = false)
	@Override
	public void create(Bestelbon bestelbon) {
		for(Bestelbonlijn bestelbonlijn:bestelbon.getBestelbonlijnen()) {
			Product product = bestelbonlijn.getProduct();
			
			if(bestelbonlijn.getAantal() > product.getStock()) {
				throw new NietGenoegInStockException();
			}
			
			product.verlaagStock(bestelbonlijn.getAantal());
		}
		
		bestelbonDAO.save(bestelbon);
		
	}
}
