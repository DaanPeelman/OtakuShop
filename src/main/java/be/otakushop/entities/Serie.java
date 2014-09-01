package be.otakushop.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "series")
public class Serie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	private String titel;
	@OneToMany(mappedBy = "serie")
	private Set<Product> producten;
	
	protected Serie() {
		this.producten = new HashSet<>();
	}

	public Serie(String titel) {
		setTitel(titel);
		this.producten = new HashSet<>();
	}
	
	public Serie(long id, String titel) {
		setId(id);
		setTitel(titel);
		this.producten = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Set<Product> getProducten() {
		return Collections.unmodifiableSet(producten);
	}

	public void addProduct(Product product) {
		this.producten.add(product);
		
		if(product.getSerie() != this) {
			product.setSerie(this);
		}
	}
	
	public void removeProduct(Product product) {
		producten.remove(product);
		
		if(product.getSerie() == this) {
			product.setSerie(null);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
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
		Serie other = (Serie) obj;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Serie [id=" + id + ", titel=" + titel + "]";
	}
}
