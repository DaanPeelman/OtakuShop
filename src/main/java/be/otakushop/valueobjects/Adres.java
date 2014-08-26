package be.otakushop.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import be.otakushop.constraints.Postcode;

@Embeddable
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@SafeHtml
	@NotBlank
	private String straat;
	@SafeHtml
	@NotBlank
	private String nummer;
	@NotNull
	@Postcode
	private Integer postcode;
	@SafeHtml
	@NotBlank
	private String gemeente;
	
	protected Adres() {
	}
	
	public Adres(String straat, String nummer, Integer postcode, String gemeente) {
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

	public Integer getPostcode() {
		return postcode;
	}

	protected void setPostcode(Integer postcode) {
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
