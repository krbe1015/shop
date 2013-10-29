/**
 * 
 */
package de.shop.bestellverwaltung.domain;

/**
 * @author Casanobi
 *
 */
public class Fahrrad extends AbstractArtikel {

	private double rahmengroesse;
	private int zoll;
	private int gaenge;
	private double gewicht;
	
	/**
	 * @return the rahmengroesse
	 */
	public double getRahmengroesse() {
		return rahmengroesse;
	}
	/**
	 * @param rahmengroesse the rahmengroesse to set
	 */
	public void setRahmengroesse(double rahmengroesse) {
		this.rahmengroesse = rahmengroesse;
	}
	/**
	 * @return the zoll
	 */
	public int getZoll() {
		return zoll;
	}
	/**
	 * @param zoll the zoll to set
	 */
	public void setZoll(int zoll) {
		this.zoll = zoll;
	}
	/**
	 * @return the gaenge
	 */
	public int getGaenge() {
		return gaenge;
	}
	/**
	 * @param gaenge the gaenge to set
	 */
	public void setGaenge(int gaenge) {
		this.gaenge = gaenge;
	}
	/**
	 * @return the gewicht
	 */
	public double getGewicht() {
		return gewicht;
	}
	/**
	 * @param gewicht the gewicht to set
	 */
	public void setGewicht(double gewicht) {
		this.gewicht = gewicht;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + gaenge;
		long temp;
		temp = Double.doubleToLongBits(gewicht);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(rahmengroesse);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + zoll;
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
		Fahrrad other = (Fahrrad) obj;
		if (gaenge != other.gaenge)
			return false;
		if (Double.doubleToLongBits(gewicht) != Double
				.doubleToLongBits(other.gewicht))
			return false;
		if (Double.doubleToLongBits(rahmengroesse) != Double
				.doubleToLongBits(other.rahmengroesse))
			return false;
		if (zoll != other.zoll)
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fahrrad [rahmengroesse=" + rahmengroesse + ", zoll=" + zoll
				+ ", gaenge=" + gaenge + ", gewicht=" + gewicht + "]";
	}
	
	
}
