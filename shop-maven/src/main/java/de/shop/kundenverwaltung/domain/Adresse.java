package de.shop.kundenverwaltung.domain;

import java.io.Serializable;

//import javax.validation.constraints.Min;
//import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import javax.xml.bind.annotation.XmlTransient;

public class Adresse implements Serializable {
	private static final long serialVersionUID = -3029272617931844501L;
	private static final String REG_EX = "[A-Z\u00C4\u00D6\u00DC][a-z0-9+-_\u00E4\u00F6\u00FC\u00DF]+";
	
	private Long id;
	
	@NotNull(message = "{adresse.plz.notNull}")
	@Pattern(regexp = "\\d{5}", message = "{adresse.plz.pattern}")
	private String plz;
	
	@NotNull(message = "{adresse.ort.notNull}")
	@Size(min = 5, max = 100, message = "{adresse.ort.length}")
	@Pattern(regexp = REG_EX, message = "{adresse.ort.pattern}")
	private String ort;

	@XmlTransient
	private Kunde kunde;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public Kunde getKunde() {
		return kunde;
	}
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ort == null) ? 0 : ort.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
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
		final Adresse other = (Adresse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else if (!id.equals(other.id))
			return false;
		if (ort == null) {
			if (other.ort != null)
				return false;
		}
		else if (!ort.equals(other.ort))
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		}
		else if (!plz.equals(other.plz))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Adresse [id=" + id + ", plz=" + plz + ", ort=" + ort + "]";
	}
}
