package be.otakushop.web;

import org.hibernate.validator.constraints.SafeHtml;

public class ZoekForm {
	@SafeHtml
	private String titel;
	@SafeHtml
	private String serie;
	@SafeHtml
	private String uitgever;
	private int startPrijs;
	private int eindPrijs;
	private int startJaar;
	private int eindJaar;
	
	public ZoekForm() {
	}
	
	public ZoekForm(String titel, String serie, String uitgever, int startPrijs, int eindPrijs, int startJaar, int eindJaar) {
		this.titel = titel;
		this.serie = serie;
		this.uitgever = uitgever;
		this.startPrijs = startPrijs;
		this.eindPrijs = eindPrijs;
		this.startJaar = startJaar;
		this.eindJaar = eindJaar;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public String getUitgever() {
		return uitgever;
	}

	public void setUitgever(String uitgever) {
		this.uitgever = uitgever;
	}

	public int getStartPrijs() {
		return startPrijs;
	}

	public void setStartPrijs(int startPrijs) {
		this.startPrijs = startPrijs;
	}

	public int getEindPrijs() {
		return eindPrijs;
	}

	public void setEindPrijs(int eindPrijs) {
		this.eindPrijs = eindPrijs;
	}

	public int getStartJaar() {
		return startJaar;
	}

	public void setStartJaar(int startJaar) {
		this.startJaar = startJaar;
	}

	public int getEindJaar() {
		return eindJaar;
	}

	public void setEindJaar(int eindJaar) {
		this.eindJaar = eindJaar;
	}
}
