package de.shop.artikelverwaltung.domain;

//import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;
//import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Artikel {
	
	private String bezeichnung;	
	private BigDecimal preis;
	private Long id;
	// private URI artikelUri;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public BigDecimal getPreis() {
		return preis;
	}

	public void setPreis(BigDecimal preis) {
		this.preis = preis;
	}
	
//	public URI getArtikelUri() {
//		return artikelUri;
//	}
//	public void setArtikelUri(URI artikelUri) {
//		this.artikelUri = artikelUri;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
//		result = prime * result
//				+ ((artikelUri == null) ? 0 : artikelUri.hashCode());
		result = prime * result
				+ ((bezeichnung == null) ? 0 : bezeichnung.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((preis == null) ? 0 : preis.hashCode());
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
		final Artikel other = (Artikel) obj;
//		if (artikelUri == null) {
//			if (other.artikelUri != null)
//				return false;
//		} 
//		else if (!artikelUri.equals(other.artikelUri))
//			return false;
		if (bezeichnung == null) {
			if (other.bezeichnung != null)
				return false;
		} 
		else if (!bezeichnung.equals(other.bezeichnung))
			return false;

		if (preis == null) {
			if (other.preis != null)
				return false;
		} 
		else if (!preis.equals(other.preis))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Artikel [bezeichnung=" + bezeichnung + ", preis=" + preis
				+ ", id=" + id + "]";
	}
	
	
}
	
