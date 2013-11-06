package de.shop.bestellverwaltung.domain;

import java.io.Serializable;
import java.net.URI;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import de.shop.kundenverwaltung.domain.AbstractKunde;

@XmlRootElement
public class Bestellung implements Serializable {
	private static final long serialVersionUID = 1618359234119003714L;
	
	private Long id;
	private BigDecimal gesamtpreis;
	private boolean ausgeliefert;
	private List<Bestellposition> bestellposition;
	
	@XmlTransient
	private AbstractKunde kunde;
	
	private URI kundeUri;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getGesamtpreis() {
		return gesamtpreis;
	}

	public void setGesamtpreis(BigDecimal gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}

	public boolean isAusgeliefert() {
		return ausgeliefert;
	}

	public void setAusgeliefert(boolean ausgeliefert) {
		this.ausgeliefert = ausgeliefert;
	}

	public List<Bestellposition> getBestellposition() {
		return bestellposition;
	}

	public void setBestellposition(List<Bestellposition> bestellposition) {
		this.bestellposition = bestellposition;
	}

	public AbstractKunde getKunde() {
		return kunde;
	}

	public void setKunde(AbstractKunde kunde) {
		this.kunde = kunde;
	}

	public URI getKundeUri() {
		return kundeUri;
	}

	public void setKundeUri(URI kundeUri) {
		this.kundeUri = kundeUri;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public BigDecimal gesamtpreisBerechnung() {
        BigDecimal gpb = 
        		new BigDecimal("0.1000000000000000055511151231257827021181583404541015625");
        
        // FIXME error with datatype
        for (Bestellposition bp : bestellposition) {
        	// BigDecimal b = new BigDecimal(bp.getAnzahl());
        	gpb = gpb.add((bp.getArtikel().getPreis()).
        			multiply(new BigDecimal(bp.getAnzahl())));
        }
        return gpb;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ausgeliefert ? 1231 : 1237);
		result = prime * result
				+ ((bestellposition == null) ? 0 : bestellposition.hashCode());
		result = prime * result
				+ ((gesamtpreis == null) ? 0 : gesamtpreis.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result
				+ ((kundeUri == null) ? 0 : kundeUri.hashCode());
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
		final Bestellung other = (Bestellung) obj;
		if (ausgeliefert != other.ausgeliefert)
			return false;
		if (bestellposition == null) {
			if (other.bestellposition != null)
				return false;
		} 
		else if (!bestellposition.equals(other.bestellposition))
			return false;
		if (gesamtpreis == null) {
			if (other.gesamtpreis != null)
				return false;
		} 
		else if (!gesamtpreis.equals(other.gesamtpreis))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} 
		else if (!id.equals(other.id))
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} 
		else if (!kunde.equals(other.kunde))
			return false;
		if (kundeUri == null) {
			if (other.kundeUri != null)
				return false;
		} 
		else if (!kundeUri.equals(other.kundeUri))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bestellung [id=" + id + ", gesamtpreis=" + gesamtpreis
				+ ", ausgeliefert=" + ausgeliefert + ", bestellposition="
				+ bestellposition + ", kunde=" + kunde + ", kundeUri="
				+ kundeUri + "]";
	}
}
