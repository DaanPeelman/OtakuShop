package be.otakushop.entities;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import be.otakushop.valueobjects.Adres;

public class Gebruiker {
	private long id;
	private String voornaam;
	private String familienaam;
	private Adres adres;
	private String emailadres;
	private String wachtwoord;
	private Set<Rol> rollen;
	private boolean actief;
	
	protected Gebruiker() {
		this.rollen = new HashSet<>();
	}

	public Gebruiker(String voornaam, String familienaam, Adres adres, String emailadres, String wachtwoord) {
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setAdres(adres);
		setEmailadres(emailadres);
		setWachtwoord(wachtwoord);
		this.rollen = new HashSet<>();
		setActief(true);
	}

	public Gebruiker(long id, String voornaam, String familienaam, Adres adres, String emailadres, String wachtwoord) {
		setId(id);
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setAdres(adres);
		setEmailadres(emailadres);
		setWachtwoord(wachtwoord);
		this.rollen = new HashSet<>();
		setActief(true);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public String getEmailadres() {
		return emailadres;
	}

	public void setEmailadres(String emailadres) {
		this.emailadres = emailadres;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	
	public Set<Rol> getRollen() {
		return Collections.unmodifiableSet(rollen);
	}
	
	public void setRollen(Set<Rol> rollen) {
		this.rollen = rollen;
	}
	
	public void addRol(Rol rol) {
		this.rollen.add(rol);
		
		if(!rol.getGebruikers().contains(this)) {
			rol.addGebruiker(this);
		}
	}
	
	public void removeRol(Rol rol) {
		this.rollen.remove(rol);
		
		if(rol.getGebruikers().contains(this)) {
			rol.removeGebruiker(this);
		}
	}

	public boolean isActief() {
		return actief;
	}

	public void setActief(boolean actief) {
		this.actief = actief;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emailadres == null) ? 0 : emailadres.hashCode());
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
		Gebruiker other = (Gebruiker) obj;
		if (emailadres == null) {
			if (other.emailadres != null)
				return false;
		} else if (!emailadres.equals(other.emailadres))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Gebruiker [id=" + id + ", voornaam=" + voornaam
				+ ", familienaam=" + familienaam + ", adres=" + adres
				+ ", emailadres=" + emailadres + ", wachtwoord=" + wachtwoord
				+ ", actief=" + actief + "]";
	}
}
