/**
 * 
 */
package de.shop.artikelverwaltung.domain;

/**
 * @author Casanobi
 *
 */
public class Fahrrad extends AbstractArtikel {
	
	private int gaenge;
	private double zoll;
	private double rahmengr��e;
	private double gewicht;
	
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
	 * @return the zoll
	 */
	public double getZoll() {
		return zoll;
	}
	/**
	 * @param zoll the zoll to set
	 */
	public void setZoll(double zoll) {
		this.zoll = zoll;
	}
	/**
	 * @return the rahmengr��e
	 */
	public double getRahmengr��e() {
		return rahmengr��e;
	}
	/**
	 * @param rahmengr��e the rahmengr��e to set
	 */
	public void setRahmengr��e(double rahmengr��e) {
		this.rahmengr��e = rahmengr��e;
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
		temp = Double.doubleToLongBits(rahmengr��e);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(zoll);
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
		if (Double.doubleToLongBits(rahmengr��e) != Double
				.doubleToLongBits(other.rahmengr��e))
			return false;
		if (Double.doubleToLongBits(zoll) != Double
				.doubleToLongBits(other.zoll))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fahrrad [gaenge=" + gaenge + ", zoll=" + zoll
				+ ", rahmengr��e=" + rahmengr��e + ", gewicht=" + gewicht + "]";
	}
}
