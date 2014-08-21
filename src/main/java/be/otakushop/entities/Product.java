package be.otakushop.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
	private long id;
	private String titel;
	private Serie serie;
	private int hoogte;
	private Date uitgifteDatum;
	private String omschrijving;
	private Uitgever uitgever;
	private BigDecimal prijs;
	private int stock;
	
	protected Product() {
	}

	public Product(String titel, Serie serie, int hoogte, String omschrijving, Date uitgifteDatum, Uitgever uitgever, BigDecimal prijs, int stock) {
		setTitel(titel);
		setSerie(serie);
		setHoogte(hoogte);
		setOmschrijving(omschrijving);
		setUitgever(uitgever);
		setPrijs(prijs);
		setStock(stock);
	}

	public Product(long id, String titel, Serie serie, int hoogte, Date uitgifteDatum, String omschrijving, Uitgever uitgever, BigDecimal prijs, int stock) {
		setId(id);
		setTitel(titel);
		setSerie(serie);
		setHoogte(hoogte);
		setOmschrijving(omschrijving);
		setUitgever(uitgever);
		setPrijs(prijs);
		setStock(stock);
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

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		if(this.serie != null && this.serie.getProducten().contains(this)) {
			this.serie.removeProduct(this);
		}
		
		this.serie = serie;
		
		if(serie != null && !serie.getProducten().contains(this)) {
			serie.addProduct(this);
		}
	}

	public int getHoogte() {
		return hoogte;
	}

	public void setHoogte(int hoogte) {
		this.hoogte = hoogte;
	}
	
	public Date getUitgifteDatum() {
		return uitgifteDatum;
	}
	
	public void setUitgifteDatum(Date uitgifteDatum) {
		this.uitgifteDatum = uitgifteDatum;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public Uitgever getUitgever() {
		return uitgever;
	}

	public void setUitgever(Uitgever uitgever) {
		if(this.uitgever != null && this.uitgever.getProducten().contains(this)) {
			this.uitgever.removeProduct(this);
		}
		
		this.uitgever = uitgever;
		
		if(uitgever != null && !uitgever.getProducten().contains(this)) {
			uitgever.addProduct(this);
		}
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		result = prime * result
				+ ((uitgever == null) ? 0 : uitgever.hashCode());
		result = prime * result
				+ ((uitgifteDatum == null) ? 0 : uitgifteDatum.hashCode());
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
		Product other = (Product) obj;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		if (uitgever == null) {
			if (other.uitgever != null)
				return false;
		} else if (!uitgever.equals(other.uitgever))
			return false;
		if (uitgifteDatum == null) {
			if (other.uitgifteDatum != null)
				return false;
		} else if (!uitgifteDatum.equals(other.uitgifteDatum))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", titel=" + titel + ", serie=" + serie
				+ ", hoogte=" + hoogte + ", uitgifteDatum=" + uitgifteDatum
				+ ", omschrijving=" + omschrijving + ", uitgever=" + uitgever
				+ ", prijs=" + prijs + ", stock=" + stock + "]";
	}
}
