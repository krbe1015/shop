package de.shop.artikelverwaltung.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import de.shop.artikelverwaltung.domain.Artikel;
// import de.shop.util.interceptor.Log;
import de.shop.util.MockService;

@Dependent
// @Log
public class ArtikelService implements Serializable {
	private static final long serialVersionUID = 8105686816948437276L;

	@NotNull(message = "{artikel.notFound.id}")
	public Artikel findArtikelById(Long id) {
		// TODO id pruefen
		// TODO Datenbanzugriffsschicht statt Mock
		return MockService.findArtikelById(id);
	}
	
	@Size(min = 1, message = "{artikel.notFound.all}")
	public List<Artikel> findAllArtikel() {
		return MockService.findAllArtikel();
	}
	
	public Artikel createArtikel(Artikel artikel) {
		return MockService.createArtikel(artikel);
	}
	
	public void updateArtikel(Artikel artikel) {
		MockService.updateArtikel(artikel);
	}
	
	public void deleteArtikel(Long id) {
		MockService.deleteArtikel(id)
	}
	
}
