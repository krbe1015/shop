package de.shop.kundenverwaltung.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.MockService;
import de.shop.util.interceptor.Log;

@Dependent
@Log
public class KundeService implements Serializable {
	private static final long serialVersionUID = 3188789767052580247L;

	@NotNull(message = "{kunde.notFound.id}")
	public AbstractKunde findKundeById(Long id) {
		if (id == null) {
			return null;
		}
		// TODO Datenbanzugriffsschicht statt MockService
		return MockService.findKundeById(id);
	}
	
	@NotNull(message = "{kunde.notFound.email}")
	public AbstractKunde findKundeByEmail(String email) {
		if (email == null) {
			return null;
		}
		// TODO Datenbanzugriffsschicht statt MockService
		return MockService.findKundeByEmail(email);
	}
	
	public List<AbstractKunde> findAllKunden() {
		// TODO Datenbanzugriffsschicht statt MockService
		return MockService.findAllKunden();
	}
	
	@Size(min = 1, message = "{kunde.notFound.nachname}")
	public List<AbstractKunde> findKundenByNachname(String nachname) {
		// TODO Datenbanzugriffsschicht statt MockService
		return MockService.findKundenByNachname(nachname);
	}

	public <T extends AbstractKunde> T createKunde(T kunde) {
		if (kunde == null) {
			return kunde;
		}

		final AbstractKunde tmp = findKundeByEmail(kunde.getEmail());  // Kein Aufruf als Business-Methode
		if (tmp != null) {
			throw new EmailExistsException(kunde.getEmail());
		}
		// TODO Datenbanzugriffsschicht statt MockService
		kunde = MockService.createKunde(kunde);

		return kunde;
	}
	
	public <T extends AbstractKunde> T updateKunde(T kunde) {
		if (kunde == null) {
			return null;
		}

		// Pruefung, ob die Email-Adresse schon existiert
		final AbstractKunde vorhandenerKunde = findKundeByEmail(kunde.getEmail());  // Kein Aufruf als Business-Methode
		if (vorhandenerKunde != null) {
			// Gibt es die Email-Adresse bei einem anderen, bereits vorhandenen Kunden?
			if (vorhandenerKunde.getId().longValue() != kunde.getId().longValue()) {
				throw new EmailExistsException(kunde.getEmail());
			}
		}

		// TODO Datenbanzugriffsschicht statt MockService
		MockService.updateKunde(kunde);
		
		return kunde;
	}

	public void deleteKunde(Long kundeId) {
		AbstractKunde kunde = findKundeById(kundeId);  // Kein Aufruf als Business-Methode
		if (kunde == null) {
			return;
		}

		// Gibt es Bestellungen?
		if (!kunde.getBestellungen().isEmpty()) {
			throw new KundeDeleteBestellungException(kunde);
		}
		
		// TODO Datenbanzugriffsschicht statt MockService
		MockService.deleteKunde(kunde.getId());
	}
}
