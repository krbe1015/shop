package de.shop.bestellverwaltung.domain;

// import java.io.Serializable;
import java.net.URI;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import de.shop.artikelverwaltung.domain.Artikel;
import javax.validation.constraints.Min;

@XmlRootElement
public class Bestellposition {
	// private static final long serialVersionUID = 3213359234119009876L;

		@XmlTransient
		private Artikel artikel;
		
		@Min(value = 1, message = "{bestellposition.anzahl.min}")
		private int anzahl;
		
		private URI artikelURI;
		
		public Artikel getArtikel() {
			return artikel;
		}
		public void setArtikel(Artikel artikel) {
			this.artikel = artikel;
		}
		public int getAnzahl() {
			return anzahl;
		}
		public void setAnzahl(int anzahl) {
			this.anzahl = anzahl;
		}
		public URI getArtikelURI() {
			return artikelURI;
		}
		public void setArtikelURI(URI artikelURI) {
			this.artikelURI = artikelURI;
		}
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + anzahl;
			result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
			result = prime * result
					+ ((artikelURI == null) ? 0 : artikelURI.hashCode());
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
			final Bestellposition other = (Bestellposition) obj;
			if (anzahl != other.anzahl)
				return false;
			if (artikel == null) {
				if (other.artikel != null)
					return false;
			} 
			else if (!artikel.equals(other.artikel))
				return false;
			if (artikelURI == null) {
				if (other.artikelURI != null)
					return false;
			} 
			else if (!artikelURI.equals(other.artikelURI))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return "Bestellposition [anzahl=" + anzahl + ", artikelURI=" + artikelURI + "]";
		}
}
