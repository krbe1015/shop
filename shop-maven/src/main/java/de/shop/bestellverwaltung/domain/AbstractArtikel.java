package de.shop.bestellverwaltung.domain;

public abstract class AbstractArtikel {
	
	private int id;
	private double preis;
	private String beschreibung;
	private boolean lieferbar;
	
	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the preis
	 */
	public double getPreis() {
		return preis;
	}
	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}
	/**
	 * @return the lieferbar
	 */
	public boolean isLieferbar() {
		return lieferbar;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param preis the preis to set
	 */
	public void setPreis(double preis) {
		this.preis = preis;
	}
	
	/**
	 * @param beschreibung the beschreibung to set
	 */
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	
	/**
	 * @param lieferbar the lieferbar to set
	 */
	public void setLieferbar(boolean lieferbar) {
		this.lieferbar = lieferbar;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((beschreibung == null) ? 0 : beschreibung.hashCode());
		result = prime * result + id;
		result = prime * result + (lieferbar ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(preis);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractArtikel other = (AbstractArtikel) obj;
		if (beschreibung == null) {
			if (other.beschreibung != null)
				return false;
		} else if (!beschreibung.equals(other.beschreibung))
			return false;
		if (id != other.id)
			return false;
		if (lieferbar != other.lieferbar)
			return false;
		if (Double.doubleToLongBits(preis) != Double
				.doubleToLongBits(other.preis))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AbstractArtikel [id=" + id + ", preis=" + preis
				+ ", beschreibung=" + beschreibung + ", lieferbar=" + lieferbar
				+ "]";
	}
	
}
