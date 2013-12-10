package de.shop.bestellverwaltung.service;

import java.util.List;
import javax.validation.constraints.NotNull;
// import java.util.Locale;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.Kunde;

public interface BestellungService {
	/*
	 * Java 8 hat zwar Default-Methoden in Interfaces, wie z.B.
	 *    default public Bestellung findBestellungById(Long id, FetchType fetch) {...}
	 * ABER:
	 *    Es duerfen keine Attribute definiert werden - auch nicht injizierte.
	 */

	@NotNull(message = "bestellung.notFound.id")
	Bestellung findBestellungById(Long id);
	
	@NotNull(message = "bestellung.notFound.kunde")
	List<Bestellung> findBestellungenByKunde(Kunde kunde);
	
	Bestellung createBestellung(Bestellung bestellung);
	
	List<Bestellung> findAllBestellungen();
	
	// FIXME findKundeByBestellungId
	// Kunde findKundeByBestellungId(Long id);
}
