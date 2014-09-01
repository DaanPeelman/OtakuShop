package be.otakushop.web;

import be.otakushop.valueobjects.Adres;

class AdresForm extends Adres {
	private static final long serialVersionUID = 1L;
	
	AdresForm() {
	}
	
	AdresForm(Adres adres) {
		setStraat(adres.getStraat());
		setNummer(adres.getNummer());
		setPostcode(adres.getPostcode());
		setGemeente(adres.getGemeente());
	}
	
	@Override
	public void setStraat(String straat) {
		super.setStraat(straat);
	}
	
	@Override
	public void setNummer(String nummer) {
		super.setNummer(nummer);
	}
	
	@Override
	public void setPostcode(Integer postcode) {
		super.setPostcode(postcode);
	}
	
	@Override
	public void setGemeente(String gemeente) {
		super.setGemeente(gemeente);
	}
}
