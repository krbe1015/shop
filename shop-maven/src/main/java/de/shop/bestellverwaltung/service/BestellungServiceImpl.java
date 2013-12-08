package de.shop.bestellverwaltung.service;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.Kunde;
import de.shop.util.MockService;
import de.shop.util.interceptor.Log;

@Dependent
@Log
public class BestellungServiceImpl implements BestellungService, Serializable {
	private static final long serialVersionUID = -769454062519816252L;
	
	// TODO mach was nachdem die bestellung aufgegeben wurde, z.b. mail versenden
	// @Inject
	// @NeueBestellung
	// private transient Event<Bestellung> event;
	
	@Override
	public Kunde findKundeByBestellungId(Long id) {
		return MockService.findKundeByBestellungId(id);
	}
	
	@Override
	@NotNull(message = "{bestellung.notFound.id}")
	public Bestellung findBestellungById(Long id) {
		// TODO Datenbanzugriffsschicht statt MockService
		return MockService.findBestellungById(id);
	}

	@Override
	@Size(min = 1, message = "{bestellung.notFound.kunde}")
	public List<Bestellung> findBestellungenByKunde(Kunde kunde) {
		// TODO Datenbanzugriffsschicht statt MockService
		return MockService.findBestellungenByKunde(kunde);
	}

	@Override
	public Bestellung createBestellung(Bestellung bestellung) {
		// TODO Datenbanzugriffsschicht statt MockService
		bestellung = MockService.createBestellung(bestellung);
		// TODO email versenden
		// event.fire(bestellung);	
		return bestellung;
	}
}
