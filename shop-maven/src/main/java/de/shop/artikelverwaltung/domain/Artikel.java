package de.shop.artikelverwaltung.domain;

import java.io.Serializable;
import java.math.BigDecimal;
// import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;
// import javax.xml.bind.annotation.XmlTransient;
// import static de.shop.util.Constants.KEINE_ID;

//import java.lang.invoke.MethodHandles;
//import javax.persistence.Basic;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Index;
//import javax.persistence.NamedQueries;
//import javax.persistence.NamedQuery;
//import javax.persistence.PostPersist;
//import javax.persistence.Table;
//
//import org.codehaus.jackson.annotate.JsonTypeInfo;
//import org.jboss.logging.Logger;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

// import de.shop.util.persistence.AbstractAuditable;

@XmlRootElement
// FIXME json richtig annotieren
// @JsonTypeInfo = JsonTypeInfo.Id.Name, include = JsonTypeInfo.As.Property, property = "type")
public class Artikel implements Serializable {
	
	private static final long serialVersionUID = 1472129607838538329L;
	
	@NotNull(message = "{artikel.bezeichung.notnull}")
	@Size(min = 10, max = 111, message = "{artikel.bezeichnung.length}")
	@Pattern(regexp = "[A-Z\u00C4\u00D6\u00DC][a-z\u00E4\u00F6\u00FC\u00DF_-/0-9]+", 
				message = "{artikel.bezeichnung.pattern}")
	private String bezeichnung;
	
	@NotNull(message = "{artikel.preis.notNull}")
	@Digits(integer = 10, fraction = 2, message = "{artikel.preis.digits}")
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
	
// TODO erstelle einen leeren artikel mit NULL
// TODO erstelle einen vollstaendigen artikel -> super()

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
		
		if (id == null) {
			if (other.id != null)
					return false;
		}
		else if (!id.equals(other.id))
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
