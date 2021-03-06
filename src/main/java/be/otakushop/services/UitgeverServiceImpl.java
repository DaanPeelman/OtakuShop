package be.otakushop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.otakushop.dao.UitgeverDAO;
import be.otakushop.entities.Uitgever;

@Service
@Transactional(readOnly = true)
class UitgeverServiceImpl implements UitgeverService {
	private final UitgeverDAO uitgeverDAO;
	
	@Autowired
	public UitgeverServiceImpl(UitgeverDAO uitgeverDAO) {
		this.uitgeverDAO = uitgeverDAO;
	}
	
	@Override
	public Iterable<Uitgever> findAll() {
		return uitgeverDAO.findAll();
	}
}