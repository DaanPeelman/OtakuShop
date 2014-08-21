package be.otakushop.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Uitgever {
	private long id;
	private String naam;
	private Set<Product> producten;
	
	protected Uitgever() {
		this.producten = new HashSet<>();
	}

	public Uitgever(String naam) {
		setNaam(naam);
		this.producten = new HashSet<>();
	}

	public Uitgever(long id, String naam) {
		setId(id);
		setNaam(naam);
		this.producten = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	public Set<Product> getProducten() {
		return Collections.unmodifiableSet(producten);
	}
	
	public void addProduct(Product product) {
		producten.add(product);
		
		if(product.getUitgever() != this) {
			product.setUitgever(this);
		}
	}
	
	public void removeProduct(Product product) {
		producten.remove(product);
		
		if(product.getUitgever() == this) {
			product.setUitgever(null);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Uitgever other = (Uitgever) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Uitgever [id=" + id + ", naam=" + naam + "]";
	}
}
