package be.otakushop.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.otakushop.valueobjects.Adres;
import be.otakushop.valueobjects.Bestelbonlijn;

@Entity
@Table(name = "bestelbonnen")
public class Bestelbon implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gebruikerId")
	private Gebruiker gebruiker;
	@Embedded
	private Adres leverAdres;
	private Date datum;
	@ElementCollection
	@CollectionTable(name = "bestelbonlijnen", joinColumns = @JoinColumn(name = "bestelbonId"))
	private Set<Bestelbonlijn> bestelbonlijnen;
	
	public Bestelbon() {
		this.bestelbonlijnen = new HashSet<>();
	}

	public Bestelbon(Gebruiker gebruiker, Adres leverAdres) {
		setGebruiker(gebruiker);
		setLeverAdres(leverAdres);
		setDatum(new Date());
		this.bestelbonlijnen = new HashSet<>();
	}

	public Bestelbon(long id, Gebruiker gebruiker, Adres leverAdres) {
		setId(id);
		setGebruiker(gebruiker);
		setLeverAdres(leverAdres);
		setDatum(new Date());
		this.bestelbonlijnen = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Gebruiker getGebruiker() {
		return gebruiker;
	}

	public void setGebruiker(Gebruiker gebruiker) {
		if(this.gebruiker.getBestellingen().contains(this)) {
			this.gebruiker.removeBestelling(this);
		}
		
		this.gebruiker = gebruiker;
		
		if(gebruiker != null) {
			gebruiker.addBestelling(this);
		}
	}

	public Adres getLeverAdres() {
		return leverAdres;
	}

	public void setLeverAdres(Adres leverAdres) {
		this.leverAdres = leverAdres;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Set<Bestelbonlijn> getBestelbonlijnen() {
		return Collections.unmodifiableSet(bestelbonlijnen);
	}

	public void setBestelbonlijnen(Set<Bestelbonlijn> bestelbonlijnen) {
		this.bestelbonlijnen = bestelbonlijnen;
	}
	
	public void addBestelbonlijn(Bestelbonlijn bestelbonlijn) {
		this.bestelbonlijnen.add(bestelbonlijn);
	}
	
	public void removeBestelbonlijn(Bestelbonlijn bestelbonlijn) {
		this.bestelbonlijnen.remove(bestelbonlijn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bestelbonlijnen == null) ? 0 : bestelbonlijnen.hashCode());
		result = prime * result + ((datum == null) ? 0 : datum.hashCode());
		result = prime * result
				+ ((gebruiker == null) ? 0 : gebruiker.hashCode());
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
		Bestelbon other = (Bestelbon) obj;
		if (bestelbonlijnen == null) {
			if (other.bestelbonlijnen != null)
				return false;
		} else if (!bestelbonlijnen.equals(other.bestelbonlijnen))
			return false;
		if (datum == null) {
			if (other.datum != null)
				return false;
		} else if (!datum.equals(other.datum))
			return false;
		if (gebruiker == null) {
			if (other.gebruiker != null)
				return false;
		} else if (!gebruiker.equals(other.gebruiker))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bestelbon [id=" + id + ", gebruiker=" + gebruiker
				+ ", leverAdres=" + leverAdres + ", datum=" + datum
				+ ", bestelbonlijnen=" + bestelbonlijnen + "]";
	}
}
