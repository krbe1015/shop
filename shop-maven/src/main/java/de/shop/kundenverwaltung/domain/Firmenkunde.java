package de.shop.kundenverwaltung.domain;

import javax.xml.bind.annotation.XmlRootElement;

//von abstrakem kunden
@XmlRootElement
public class Firmenkunde extends Kunde {
	private static final long serialVersionUID = 6258156986876418100L;
	
	private String firmenname;
	
	public String getFirmenname() {
		return firmenname;
	}
	public void setFirmenname(String firmenname) {
		this.firmenname = firmenname;
	}
	@Override
	public String toString() {
		return "Firmenkunde [" + super.toString() + ", Firmenname=" + firmenname + "]";
	}
}
