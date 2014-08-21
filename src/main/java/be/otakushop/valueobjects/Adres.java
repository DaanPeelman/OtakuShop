package be.otakushop.valueobjects;

public class Adres {
	private String straat;
	private String nummer;
	private int postcode;
	private String gemeente;
	
	protected Adres() {
	}
	
	public Adres(String straat, String nummer, int postcode, String gemeente) {
		setStraat(straat);
		setNummer(nummer);
		setPostcode(postcode);
		setGemeente(gemeente);
	}

	protected String getStraat() {
		return straat;
	}

	protected void setStraat(String straat) {
		this.straat = straat;
	}

	protected String getNummer() {
		return nummer;
	}

	protected void setNummer(String nummer) {
		this.nummer = nummer;
	}

	protected int getPostcode() {
		return postcode;
	}

	protected void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	protected String getGemeente() {
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
