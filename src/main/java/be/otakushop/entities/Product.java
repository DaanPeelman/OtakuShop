package be.otakushop.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "producten")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	private String titel;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "serieId")
	private Serie serie;
	private int hoogte;
	@Temporal(TemporalType.DATE)
	private Date uitgifte;
	private String omschrijvingNl;
	private String omschrijvingEn;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uitgeverId")
	private Uitgever uitgever;
	private BigDecimal prijs;
	private int stock;
	
	protected Product() {
	}

	public Product(String titel, Serie serie, int hoogte, Date uitgifte, String omschrijvingNl, String omschrijvingEn, Uitgever uitgever, BigDecimal prijs, int stock) {
		setTitel(titel);
		setSerie(serie);
		setHoogte(hoogte);
		setUitgifte(uitgifte);
		setOmschrijvingNl(omschrijvingNl);
		setOmschrijvingEn(omschrijvingEn);
		setUitgever(uitgever);
		setPrijs(prijs);
		setStock(stock);
	}

	public Product(long id, String titel, Serie serie, int hoogte, Date uitgifte, String omschrijvingNl, String omschrijvingEn, Uitgever uitgever, BigDecimal prijs, int stock) {
		setId(id);
		setTitel(titel);
		setSerie(serie);
		setHoogte(hoogte);
		setUitgifte(uitgifte);
		setOmschrijvingNl(omschrijvingNl);
		setOmschrijvingEn(omschrijvingEn);
		setUitgever(uitgever);
		setPrijs(prijs);
		setStock(stock);
	}
	
	public void verlaagStock(int aantal) {
		stock -= aantal;
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
	
	public Date getUitgifte() {
		return uitgifte;
	}
	
	public void setUitgifte(Date uitgifte) {
		this.uitgifte = uitgifte;
	}

	public String getOmschrijvingNl() {
		return omschrijvingNl;
	}

	public void setOmschrijvingNl(String omschrijvingNl) {
		this.omschrijvingNl = omschrijvingNl;
	}
	
	public String getOmschrijvingEn() {
		return omschrijvingEn;
	}
	
	public void setOmschrijvingEn(String omschrijvingEn) {
		this.omschrijvingEn = omschrijvingEn;
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
				+ ((uitgifte == null) ? 0 : uitgifte.hashCode());
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
		if (uitgifte == null) {
			if (other.uitgifte != null)
				return false;
		} else if (!uitgifte.equals(other.uitgifte))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", titel=" + titel + ", serie=" + serie
				+ ", hoogte=" + hoogte + ", uitgifteDatum=" + uitgifte
				+ ", omschrijvingNl=" + omschrijvingNl + ", omschrijvingEn="
				+ omschrijvingEn + ", uitgever=" + uitgever + ", prijs="
				+ prijs + ", stock=" + stock + "]";
	}
}
