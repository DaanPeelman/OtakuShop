package be.otakushop.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.SafeHtml;

@Embeddable
public class Adres implements Serializable {
	private static final long SerialVersionUID = 1L;
	
	@SafeHtml
	private String straat;
	@SafeHtml
	private String nummer;
	@SafeHtml
	private int postcode;
	@SafeHtml
	private String gemeente;
	
	protected Adres() {
	}
	
	public Adres(String straat, String nummer, int postcode, String gemeente) {
		setStraat(straat);
		setNummer(nummer);
		setPostcode(postcode);
		setGemeente(gemeente);
	}

	public String getStraat() {
		return straat;
	}

	protected void setStraat(String straat) {
		this.straat = straat;
	}

	public String getNummer() {
		return nummer;
	}

	protected void setNummer(String nummer) {
		this.nummer = nummer;
	}

	public int getPostcode() {
		return postcode;
	}

	protected void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	protected void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}

	@Override
	public String toString() {
		return "Adres [straat=" + straat + ", nummer=" + nummer + ", postcode="
				+ postcode + ", gemeente=" + gemeente + "]";
	}
}
