package be.otakushop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.otakushop.dao.BestelbonDAO;
import be.otakushop.entities.Bestelbon;
import be.otakushop.entities.Product;
import be.otakushop.exceptions.NietGenoegInStockException;
import be.otakushop.valueobjects.Bestelbonlijn;

@Service
@Transactional(readOnly = true)
class BestelbonServiceImpl implements BestelbonService {
	private final BestelbonDAO bestelbonDAO;
	
	@Autowired
	public BestelbonServiceImpl(BestelbonDAO bestelbonDAO) {
		this.bestelbonDAO = bestelbonDAO;
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
