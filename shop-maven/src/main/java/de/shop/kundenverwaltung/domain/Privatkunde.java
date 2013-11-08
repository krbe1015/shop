package de.shop.kundenverwaltung.domain;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

// von abstrakem kunden
@XmlRootElement
public class Privatkunde extends AbstractKunde {
	private static final long serialVersionUID = -3177911520687689458L;
	
	private Set<Eigenschaften> eigenschaften;

	public Set<Eigenschaften> getEigenschaften() {
		return eigenschaften;
	}
	public void setEigenschaften(Set<Eigenschaften> eigenschaften) {
		this.eigenschaften = eigenschaften;
	}
	@Override
	public String toString() {
		return "Privatkunde [" + super.toString() + ", Eigenschaften=" + eigenschaften + "]";
	}
}
