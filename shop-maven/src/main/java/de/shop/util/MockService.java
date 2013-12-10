package de.shop.util;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.GregorianCalendar;
import java.util.Date;

import org.jboss.logging.Logger;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.bestellverwaltung.domain.Bestellung;
// import de.shop.bestellverwaltung.domain.Bestellposition;
import de.shop.kundenverwaltung.domain.Kunde;
import de.shop.kundenverwaltung.domain.Adresse;
import de.shop.kundenverwaltung.domain.Eigenschaften;
import de.shop.kundenverwaltung.domain.Firmenkunde;
import de.shop.kundenverwaltung.domain.Privatkunde;

public final class MockService {
	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	private static final int MAX_ID = 99;
	private static final int MAX_KUNDEN = 8;
	private static final BigDecimal DUMMY_PREIS =
			new BigDecimal("0.1000000000000000055511151231257827021181583404541015625");
	private static final int MAX_BESTELLUNGEN = 4;
//	private static final int MAX_ARTIKEL = 101;
	
	public static Kunde findKundeById(Long id) {
		if (id > MAX_ID) {
			return null;
		}
		
		// modulo fuer variiation
		final Kunde kunde = id % 2 == 1 ? new Privatkunde() : new Firmenkunde();
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

	// finde alle kunden
	public static List<Kunde> findAllKunden() {
		final int anzahl = MAX_KUNDEN;
		final List<Kunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Kunde kunde = findKundeById(Long.valueOf(i));
			kunden.add(kunde);			
		}
		return kunden;
	}

	// finde kunden durch nachname
	public static List<Kunde> findKundenByNachname(String nachname) {
		final int anzahl = nachname.length();
		final List<Kunde> kunden = new ArrayList<>(anzahl);
		for (int i = 1; i <= anzahl; i++) {
			final Kunde kunde = findKundeById(Long.valueOf(i));
			kunde.setNachname(nachname);
			kunden.add(kunde);			
		}
		return kunden;
	}
	
//	public static Kunde findKundeByBestellungId(Long id) {
//		final Bestellung bestellung = findBestellungById(id);
//		return bestellung.getKunde();
//	}
	
	public static Kunde findKundeByEmail(String email) {
		if (email.isEmpty()) {
			return null;
		}
		
		final Kunde kunde = email.length() % 2 == 1 ? new Privatkunde() : new Firmenkunde();
		kunde.setId(Long.valueOf(email.length()));
		kunde.setNachname("Mueller");
		kunde.setEmail(email);
		// FIXME datum, import kalender
		final GregorianCalendar seitGC = new GregorianCalendar(2013, 0, 8);
		final Date seit = seitGC.getTime();
		kunde.setSeit(seit);
	
		// TODO kunde hat eigentlich noch adresse
		// TODO Eigenschaften des Privatkunden setzen
	
		return kunde;
	}
	
	// finde bestellung durch kunde
	public static List<Bestellung> findBestellungenByKunde(Kunde kunde) {
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
	
	// finde bestellung durch ID
	public static Bestellung findBestellungById(Long id) {
		if (id > MAX_ID) {
			return null;
		}

		final Kunde kunde = findKundeById(id + 1);  // andere ID fuer den Kunden
		final Bestellung bestellung = new Bestellung();
		bestellung.setId(id);
		bestellung.setAusgeliefert(false);
		bestellung.setKunde(kunde);
		// FIXME gesamtPreis berechnen!
//		bestellung.setGesamtpreis(DUMMY_PREIS);
		
//		final List<Bestellposition> bestellposition = new ArrayList<>();
//        for (int i = 1; i < 7; i++) {
//        		final Bestellposition bp = new Bestellposition();
//                bp.setAnzahl(DUMMY_ANZAHL);
//                bp.setArtikel(findArtikelById(Long.valueOf(i)));
//                
//                bestellposition.add(bp);
//        }
//        bestellung.setBestellposition(bestellposition);
//        bestellung.setGesamtfpreis(bestellung.gesamtpreisBerechnung());
		
		return bestellung;
	}
	
	// finde alle bestellungen
	public static List<Bestellung> findAllBestellungen() {
		final int anz = MAX_BESTELLUNGEN;
		final List<Bestellung> bl = new ArrayList<>(anz);
		for(int i = 1; i <= anz; i++) {
			final Bestellung bestellung = findBestellungById(Long.valueOf(i));
			bl.add(bestellung);
		}
		return bl;
	}
	
	// finde artikel durch ID
	public static Artikel findArtikelById(Long id) {
		if (id > MAX_ID) {
			return null;
		}

		final Artikel artikel = new Artikel();
		artikel.setId(id);
		artikel.setBezeichnung("Bezeichnung: " + id);
		// TODO add + id into setpreis
		artikel.setPreis(DUMMY_PREIS);
		artikel.setId(id);
		artikel.setBezeichnung("Bezeichnung:" + id);
		return artikel;
	}
	
	// erstelle bestellung
	public static Bestellung createBestellung(Bestellung bestellung) {
		final int scale = 10;
		final Kunde kunde = bestellung.getKunde();
		kunde.setId(Long.valueOf(1));
		final BigDecimal gesamtpreis = bestellung.getGesamtpreis();
		gesamtpreis.setScale(scale);
		
		// bestellung = findBestellungById(Long.valueOf(id));
		
		LOGGER.debugf("Erstelle Bestellung: " + bestellung);
		return bestellung;
	}
	
	// update bestellung
	public static void updateBestellung(Bestellung bestellung) {
		LOGGER.debugf("Aktualisierte Bestellung: " + bestellung);
	}
	
	// loesche bestellung
	public static void deleteBestellung(Long bestellungId) {
		LOGGER.debugf("Bestellung mit ID=" + bestellungId + " geloescht");
	}
	
	// estelle artikel
	public static Artikel createArtikel(Artikel artikel) {
		final String bezeichnung = artikel.getBezeichnung();
		artikel.setId(Long.valueOf(bezeichnung.length()));
		artikel.setPreis(artikel.getPreis());
		
		LOGGER.debugf("Ersteller Artikel: " + artikel);
		return artikel;
	}
	
	// update artikel
	public static void updateArtikel(Artikel artikel) {
		LOGGER.debugf("Aktualisierter Artikel: " + artikel);
	}
	
	// loesche artikel
	public static void deleteArtikel(Long id) {
		LOGGER.debugf("Artikel mit ID=" + id + " geloescht");
	}

	// erstelle kunde
	public static Kunde createKunde(Kunde kunde) {
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

	// update kunde
	public static void updateKunde(Kunde kunde) {
		
		LOGGER.debugf("Aktualisierter Kunde: " + kunde);
	}

	// loesche kunde
	public static void deleteKunde(Long kundeId) {
		LOGGER.debugf("Kunde mit ID=" + kundeId + " geloescht");
	}

	private MockService() { /**/ }
}
