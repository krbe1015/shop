package de.shop.kundenverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 */
@XmlRootElement
public class Firmenkunde extends AbstractKunde {
	private static final long serialVersionUID = 6258156986876418100L;
	
	private Firma firma;
	
	/**
	 * 
	 * @return the firma
	 */
	public Firma getFirma() {
		return firma;
	}
	/**
	 * 
	 * @param firma the firma to set
	 */
	public void setFirma(Firma firma) {
		this.firma = firma;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((firma == null) ? 0 : firma.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Firmenkunde other = (Firmenkunde) obj;
		if (firma == null) {
			if (other.firma != null)
				return false;
		} else if (!firma.equals(other.firma))
			return false;
		return true;
	}
	
	
	
}
