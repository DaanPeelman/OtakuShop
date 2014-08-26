package be.otakushop.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rollen")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	private String naam;
	@ManyToMany
	@JoinTable(name = "gebruikersrollen", joinColumns = @JoinColumn(name = "rolId"), inverseJoinColumns = @JoinColumn(name = "gebruikerId"))
	private Set<Gebruiker> gebruikers;
	
	protected Rol() {
		this.gebruikers = new HashSet<>();
	}

	public Rol(String naam) {
		setNaam(naam);
		this.gebruikers = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
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
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
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
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", naam=" + naam + ", gebruikers="
				+ gebruikers + "]";
	}
}
