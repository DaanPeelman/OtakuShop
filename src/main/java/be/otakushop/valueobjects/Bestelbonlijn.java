package be.otakushop.valueobjects;

import java.math.BigDecimal;

import be.otakushop.entities.Bestelbon;
import be.otakushop.entities.Product;

public class Bestelbonlijn {
	private Product product;
	private Bestelbon bestelbon;
	private int aantal;
	private BigDecimal prijs;
	
	protected Bestelbonlijn() {
	}

	public Bestelbonlijn(Product product, Bestelbon bestelbon, int aantal, BigDecimal prijs) {
		this.product = product;
		this.bestelbon = bestelbon;
		this.aantal = aantal;
		this.prijs = prijs;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Bestelbon getBestelbon() {
		return bestelbon;
	}

	public void setBestelbon(Bestelbon bestelbon) {
		if(this.bestelbon != null && this.bestelbon.getBestelbonlijnen().contains(this)) {
			this.bestelbon.removeBestelbonlijn(this);
		}
		
		this.bestelbon = bestelbon;
		
		if(bestelbon != null && !bestelbon.getBestelbonlijnen().contains(this)) {
			bestelbon.addBestelbonlijn(this);
		}
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + aantal;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		Bestelbonlijn other = (Bestelbonlijn) obj;
		if (aantal != other.aantal)
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bestelbonlijn [product=" + product + ", bestelbon=" + bestelbon
				+ ", aantal=" + aantal + ", prijs=" + prijs + "]";
	}
}
