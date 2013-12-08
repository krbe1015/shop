package de.shop.bestellverwaltung.service;

import java.util.List;

import java.util.Locale;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;

public interface BestellungService {
	/*
	 * Java 8 hat zwar Default-Methoden in Interfaces, wie z.B.
	 *    default public Bestellung findBestellungById(Long id, FetchType fetch) {...}
	 * ABER:
	 *    Es duerfen keine Attribute definiert werden - auch nicht injizierte.
	 */

	Bestellung findBestellungById(Long id);
	List<Bestellung> findBestellungenByKunde(AbstractKunde kunde);
	Bestellung createBestellung(Bestellung bestellung, AbstractKunde kunde, Locale locale);
	AbstractKunde findKundeByBestellungId(Long id);
}
