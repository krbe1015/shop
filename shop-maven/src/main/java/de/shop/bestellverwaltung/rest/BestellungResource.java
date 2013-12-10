package de.shop.bestellverwaltung.rest;

import static de.shop.util.Constants.SELF_LINK;
// import static de.shop.util.Constants.ADD_LINK;
// import static de.shop.util.Constants.FIRST_LINK;
// import static de.shop.util.Constants.LAST_LINK;
// import static de.shop.util.Constants.REMOVE_LINK;
// import static de.shop.util.Constants.SELF_LINK;
// import static de.shop.util.Constants.UPDATE_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;
import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.validation.Valid; 

import javax.validation.Valid;
import javax.enterprise.context.RequestScoped;

//import de.shop.artikelverwaltung.rest.ArtikelResource;
// import de.shop.bestellverwaltung.domain.Bestellposition;
import de.shop.bestellverwaltung.domain.Bestellung;
import de.shop.bestellverwaltung.service.BestellungService;
import de.shop.kundenverwaltung.domain.Kunde;
import de.shop.kundenverwaltung.rest.KundeResource;

import de.shop.util.MockService;
import de.shop.util.rest.UriHelper;
// import de.shop.util.rest.NotFoundException;
import de.shop.util.interceptor.Log;

@Path("/bestellungen")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
@Log
@RequestScoped
public class BestellungResource {
	
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private UriHelper uriHelper;
	
	@Inject
	private KundeResource kundeResource;
	
	@Inject
	private BestellungService bs;
	
//	@Inject
//	private ArtikelResource artikelResource;
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findBestellungById(@PathParam("id") Long id) {
		final Bestellung bestellung = bs.findBestellungById(id);
//		if (bestellung == null) {
//			throw new NotFoundException("Keine Bestellung mit der ID " + id + " gefunden.");
//		}
		
		setStructuralLinks(bestellung, uriInfo);
		
		// Link-Header setzen
		final Response response = Response.ok(bestellung)
                                          .links(getTransitionalLinks(bestellung, uriInfo))
                                          .build();
		
		return response;
	}
	
//	@GET
//	@Path("{id:[1-9][0-9]*}/kunde")
//	public Response findKundeByBestellungId(@PathParam("id") Long id) {
//		
//	// FIXME war findKundeByBestellungId
//		final Kunde kunde = bs.findKundeByBestellungId(id);
//		if (kunde == null) {
//			throw new NotFoundException("Kein Kunde zur Bestellung mit ID: " + id + " gefunden");
//		}
//			kundeResource.setStructuralLinks(kunde, uriInfo);
//		return Response.ok(kunde)
//						.links(kundeResource.getTransitionalLinks(kunde, uriInfo))
//						.build();
//	}
	
	public void setStructuralLinks(Bestellung bestellung, UriInfo uriInfo) {
		// URI fuer Kunde setzen
		final Kunde kunde = bestellung.getKunde();
		if (kunde != null) {
			final URI kundeUri = kundeResource.getUriKunde(bestellung.getKunde(), uriInfo);
			bestellung.setKundeUri(kundeUri);
		}
		// TODO URI für Artikel in Bestellposition
		// FIXME bestellposition URI
//		for (Bestellposition bp : bestellung.getBestellposition()) {
//			if (bp != null) {
//				final URI artikelURI = artikelResource.getUriArtikel(bp.getArtikel(), uriInfo);
//				bp.setArtikelURI(artikelURI);
//			}
//		}
	}
	
	private Link[] getTransitionalLinks(Bestellung bestellung, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriBestellung(bestellung, uriInfo))
                              .rel(SELF_LINK)
                              .build();
		return new Link[] {self};
	}
	
	public URI getUriBestellung(Bestellung bestellung, UriInfo uriInfo) {
		return uriHelper.getUri(BestellungResource.class, "findBestellungById", bestellung.getId(), uriInfo);
	}
	
	@POST
	@Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createBestellung(@Valid Bestellung bestellung) {
		bestellung = MockService.createBestellung(bestellung);
		setStructuralLinks(bestellung, uriInfo);
		return Response.created(getUriBestellung(bestellung, uriInfo))
		           .build();
	}
	
	@PUT
	@Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateBestellung(@Valid Bestellung bestellung) {
		MockService.updateBestellung(bestellung);
	}
	
}
