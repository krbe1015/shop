package de.shop.kundenverwaltung.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.shop.kundenverwaltung.domain.Kunde;
import de.shop.util.MockService;
import de.shop.util.interceptor.Log;

@Dependent
@Log
public class KundeService implements Serializable {
	private static final long serialVersionUID = 3188789767052580247L;

	@NotNull(message = "{kunde.notFound.id}")
	public Kunde findKundeById(Long id) {
		if (id == null) {
			return null;
		}
		// TODO Datenbanzugriffsschicht statt MockService
		return MockService.findKundeById(id);
	}
	
	@NotNull(message = "{kunde.notFound.email}")
	public Kunde findKundeByEmail(String email) {
		if (email == null) {
			return null;
		}
		// TODO Datenbanzugriffsschicht statt MockService
		return MockService.findKundeByEmail(email);
	}
	
	public List<Kunde> findAllKunden() {
		// TODO Datenbanzugriffsschicht statt MockService
		return MockService.findAllKunden();
	}
	
	@Size(min = 1, message = "{kunde.notFound.nachname}")
	public List<Kunde> findKundenByNachname(String nachname) {
		// TODO Datenbanzugriffsschicht statt MockService
		return MockService.findKundenByNachname(nachname);
	}

	public Kunde createKunde(Kunde kunde) {
		if (kunde == null) {
			return kunde;
		}

		// final Kunde tmp = findKundeByEmail(kunde.getEmail());  // Kein Aufruf als Business-Methode
		// if (tmp != null) {
			// TODO email exception
			// throw new EmailExistsException(kunde.getEmail());
		// }
		// TODO Datenbanzugriffsschicht statt MockService
		kunde = MockService.createKunde(kunde);

		return kunde;
	}
	
	public Kunde updateKunde(Kunde kunde) {
		if (kunde == null) {
			return null;
		}

		// Pruefung, ob die Email-Adresse schon existiert
		// final Kunde vorhandenerKunde = findKundeByEmail(kunde.getEmail());  // Kein Aufruf als Business-Methode
		// if (vorhandenerKunde != null) {
			// TODO Gibt es die Email-Adresse bei einem anderen, bereits vorhandenen Kunden?
			// if (vorhandenerKunde.getId().longValue() != kunde.getId().longValue()) {
			//	throw new EmailExistsException(kunde.getEmail());
			// }
		// }

		// TODO Datenbanzugriffsschicht statt MockService
		MockService.updateKunde(kunde);
		
		return kunde;
	}

	public void deleteKunde(Long kundeId) {
		final Kunde kunde = findKundeById(kundeId);  // Kein Aufruf als Business-Methode
		if (kunde == null) {
			return;
		}

		// Gibt es Bestellungen?
		// if (!kunde.getBestellungen().isEmpty()) {
		//	throw new KundeDeleteBestellungException(kunde);
		// }
		
		// TODO Datenbanzugriffsschicht statt MockService
		MockService.deleteKunde(kunde.getId());
	}
}
