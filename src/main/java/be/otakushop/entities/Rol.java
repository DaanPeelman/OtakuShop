package be.otakushop.entities;

import java.util.HashSet;
import java.util.Set;

public class Rol {
	private long id;
	private long naam;
	private Set<Gebruiker> gebruikers;
	
	protected Rol() {
		this.gebruikers = new HashSet<>();
	}

	public Rol(long naam) {
		setNaam(naam);
		this.gebruikers = new HashSet<>();
	}

	public Rol(long id, long naam) {
		setId(id);
		setNaam(naam);
		this.gebruikers = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getNaam() {
		return naam;
	}

	public void setNaam(long naam) {
		this.naam = naam;
	}

	public Set<Gebruiker> getGebruikers() {
		return gebruikers;
	}

	public void setGebruikers(Set<Gebruiker> gebruikers) {
		this.gebruikers = gebruikers;
	}
	
	public void addGebruiker(Gebruiker gebruiker) {
		this.gebruikers.add(gebruiker);
		
		if(!gebruiker.getRollen().contains(this)) {
			gebruiker.addRol(this);
		}
	}
	
	public void removeGebruiker(Gebruiker gebruiker) {
		this.gebruikers.remove(gebruiker);
		
		if(gebruiker.getRollen().contains(this)) {
			gebruiker.removeRol(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (naam ^ (naam >>> 32));
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
		Rol other = (Rol) obj;
		if (naam != other.naam)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", naam=" + naam + ", gebruikers="
				+ gebruikers + "]";
	}
}
