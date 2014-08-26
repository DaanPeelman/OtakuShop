package be.otakushop.web;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import be.otakushop.valueobjects.Adres;

@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
@Component
class MandjeImpl implements Mandje {
	private Map<Long, Integer> producten;
	@Valid
	private Adres adres;
	
	MandjeImpl() {
		producten = new ConcurrentHashMap<>();
	}
	
	@Override
	public void addProduct(long id, int aantal) {
		producten.put(id, aantal);
	}
	
	@Override
	public void deleteProduct(long id) {
		producten.remove(id);
	}
	
	@Override
	public void clearMandje() {
		producten.clear();
	}

	public Map<Long, Integer> getProducten() {
		return producten;
	}

	public void setProducten(Map<Long, Integer> producten) {
		this.producten = producten;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}
}
