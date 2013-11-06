package de.shop.util;

import java.lang.invoke.MethodHandles;
// import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.kundenverwaltung.domain.Adresse;
import de.shop.kundenverwaltung.domain.Firmenkunde;
import de.shop.kundenverwaltung.domain.Eigenschaften;
import de.shop.kundenverwaltung.domain.Privatkunde;

import org.jboss.logging.Logger;

public final class MockService {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final int MAX_ID = 99;
	private static final int MAX_KUNDEN = 8;
	private static final int MAX_BESTELLUNGEN = 4;

	public static AbstractKunde findKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		final AbstractKunde kunde = id % 2 == 1 ? new Privatkunde() : new Firmenkunde();
		kunde.setId(id);
		kunde.setNachname("Nachname" + id);
		kunde.setEmail("" + id + "@hska.de");
		
		final Adresse adresse = new Adresse();
		adresse.setId(id + 1);        // andere ID fuer die Adresse
		adresse.setPlz("12345");
		adresse.setOrt("Testort");
		adresse.setKunde(kunde);
		kunde.setAdresse(adresse);
		
		if (kunde.getClass().equals(Privatkunde.class)) {
			final Privatkunde privatkunde = (Privatkunde) kunde;
			final Set<Eigenschaften> eigenschaften = new HashSet<>();
			eigenschaften.add(Eigenschaften.LESEN);
			eigenschaften.add(Eigenschaften.REISEN);
			privatkunde.setEigenschaften(eigenschaften);
		}
		
		return kunde;
	}

	public static List<AbstractKunde> findAllKunden() {
		final int anzahl = MAX_KUNDEN;
		final List<AbstractKunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(Long.valueOf(i));
			kunden.add(kunde);			
		}
		return kunden;
	}

	public static List<AbstractKunde> findKundenByNachname(String nachname) {
		final int anzahl = nachname.length();
		final List<AbstractKunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final AbstractKunde kunde = findKundeById(Long.valueOf(i));
			kunde.setNachname(nachname);
			kunden.add(kunde);			
		}
		return kunden;
	}
	
	public static List<Bestellung> findBestellungenByKunde(AbstractKunde kunde) {
		// Beziehungsgeflecht zwischen Kunde und Bestellungen aufbauen
		final int anzahl = kunde.getId().intValue() % MAX_BESTELLUNGEN + 1;  // 1, 2, 3 oder 4 Bestellungen
		final List<Bestellung> bestellungen = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Bestellung bestellung = findBestellungById(Long.valueOf(i));
			bestellung.setKunde(kunde);
			bestellungen.add(bestellung);			
		}
		kunde.setBestellungen(bestellungen);
		
		return bestellungen;
	}

	public static Bestellung findBestellungById(Long id) {
		if (id > MAX_ID) {
			return null;
		}

		final AbstractKunde kunde = findKundeById(id + 1);  // andere ID fuer den Kunden

		final Bestellung bestellung = new Bestellung();
		bestellung.setId(id);
		bestellung.setAusgeliefert(false);
		bestellung.setKunde(kunde);
		
		return bestellung;
	}
	
	public static Bestellung createBestellung(Bestellung bestellung) {
		final int id = 9000;
		bestellung = findBestellungById(Long.valueOf(id));
		LOGGER.debugf("Erstelle Bestellung: " + bestellung);
		return bestellung;
	}
	
	public static void updateBestellung(Bestellung bestellung) {
		
		LOGGER.debugf("Aktualisierte Bestellung: " + bestellung);
	}
	
	public static Artikel findArtikelById(Long id) {
		if (id > MAX_ID) {
			return null;
		}

		final Artikel artikel = new Artikel();
		artikel.setId(id);
		artikel.setBezeichnung("Bezeichnung:" + id);
		artikel.setPreis(new Double(11.1 + id));
		return artikel;
	}
	
	public static Artikel createArtikel(Artikel artikel) {
		final String bezeichnung = artikel.getBezeichnung();
		artikel.setId(Long.valueOf(bezeichnung.length()));
		// final Double preis = artikel.getPreis();
		artikel.setPreis(artikel.getPreis());
		
		LOGGER.debugf("Ersteller Artikel: " + artikel);
		return artikel;
	}
	
	public static void updateArtikel(Artikel artikel) {
		
		LOGGER.debugf("Aktualisierter Artikel: " + artikel);
	}

	public static AbstractKunde createKunde(AbstractKunde kunde) {
		// Neue IDs fuer Kunde und zugehoerige Adresse
		// Ein neuer Kunde hat auch keine Bestellungen
		final String nachname = kunde.getNachname();
		kunde.setId(Long.valueOf(nachname.length()));
		final Adresse adresse = kunde.getAdresse();
		adresse.setId((Long.valueOf(nachname.length())) + 1);
		adresse.setKunde(kunde);
		kunde.setBestellungen(null);
		
		LOGGER.debugf("Neuer Kunde: " + kunde);
		return kunde;
	}

	public static void updateKunde(AbstractKunde kunde) {
		
		LOGGER.debugf("Aktualisierter Kunde: " + kunde);
	}

	public static void deleteKunde(Long kundeId) {
		LOGGER.debugf("Kunde mit ID=" + kundeId + " geloescht");
	}

	private MockService() { /**/ }
}