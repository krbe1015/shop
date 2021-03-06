package de.shop.bestellverwaltung.domain;

// import java.io.Serializable;
import java.net.URI;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javax.validation.Valid;
import org.hibernate.validator.constraints.NotEmpty;

import de.shop.kundenverwaltung.domain.Kunde;

@XmlRootElement
public class Bestellung {
	// private static final long serialVersionUID = 1618359234119003714L;
	
	private Long id;
	private BigDecimal gesamtpreis;
	private boolean ausgeliefert;
	
	@Valid
	@NotEmpty(message = "{bestellverwaltung.bestellung.bestellposition.notEmpty}")
	private List<Bestellposition> bestellposition;
	
	@XmlTransient
	private Kunde kunde;
	
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

	// FIXME remove setScale!
	public void setGesamtpreis(BigDecimal gesamtpreis) {
		this.gesamtpreis = gesamtpreis.setScale(1);
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

	@XmlTransient
	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public URI getKundeUri() {
		return kundeUri;
	}

	public void setKundeUri(URI kundeUri) {
		this.kundeUri = kundeUri;
	}
	
//	public BigDecimal gesamtpreisBerechnung() {
//        BigDecimal gpb = 
//        		new BigDecimal("0.1000000000000000055511151231257827021181583404541015625");
//        
//        // FIXME error with datatype
//        for (Bestellposition bp : bestellposition) {
//        	BigDecimal b = new BigDecimal(bp.getAnzahl());
//        	// TODO getPreis from Artikel, but look in Bestellposition
//        	gpb = (bp.getArtikel().getPreis());
//        	// gpb = (x.getPreis());
//        	gpb = gpb.multiply(b);
//        }
//        return gpb;
//	}

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
