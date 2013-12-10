package de.shop.artikelverwaltung.rest;

import static de.shop.util.Constants.SELF_LINK;
//import static de.shop.util.Constants.ADD_LINK;
//import static de.shop.util.Constants.REMOVE_LINK;
//import static de.shop.util.Constants.UPDATE_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;
// import java.lang.invoke.MethodHandles;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.validation.Valid; 

//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;

import de.shop.artikelverwaltung.domain.Artikel;
import de.shop.artikelverwaltung.service.ArtikelService;
// INFO wurde durch service ersetzt
// import de.shop.util.MockService;
import de.shop.util.rest.UriHelper;
import de.shop.util.rest.NotFoundException;

// import org.jboss.logging.Logger;
import de.shop.util.interceptor.Log;


@Path("/artikel")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
@Log
public class ArtikelResource {
	
//	private static final Logger LOGGER = Logger.getLogger(MethodHandles.lookup().lookupClass());
	
	@Inject
	private ArtikelService as;
	
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private UriHelper uriHelper;

//	@PostConstruct
//	private void postConstruct() {
//		LOGGER.debugf("CDI bean %s erzeugt", this);
//	}
//	
//	@PreDestroy
//	private void preDestroy() {
//		LOGGER.debugf("CDI bean %s geloescht", this);
//	}
		
	// finde artikel durch ID
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findArtikelById(@PathParam("id") Long id, @Context UriInfo uriInfo) {
		final Artikel artikel = as.findArtikelById(id);
		
		if (artikel == null) {
			// TODO fehlermeldung richtig importieren
			throw new NotFoundException("artikel.notFound.id", id);
		}

		return Response.ok(artikel)
	                   .links(getTransitionalLinks(artikel, uriInfo))
	                   .build();
	}
	
	// finde artikel durch bezeichnung
	/*
	public Response findArtikelByBezeichnung(@QueryParam(query_param) String bezeichnung) {
		List<Artikel> artikelliste = null
		if (bezeichnung != null) {
			
		}
		else {
			
		}
		
		return Response.ok(new GenericEntity<List<Artikel>>(artikelliste) { })
						.link(getTransitionalLinksArtikelListe(artikelliste, uriInfo))
						.build();
	}
	*/
	
	// hole links
	private Link[] getTransitionalLinks(Artikel artikel, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo))
                              .rel(SELF_LINK)
                              .build();

		return new Link[] {self};
	}
	
	// hole URI link
	public URI getUriArtikel(Artikel artikel, UriInfo uriInfo) {
		return uriHelper.getUri(ArtikelResource.class, "findArtikelById", artikel.getId(), uriInfo);
	}
	
	// erzeuge artikel
	@POST
	@Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createArtikel(@Valid Artikel artikel) {
		artikel = as.createArtikel(artikel);
		return Response.created(getUriArtikel(artikel, uriInfo))
		           .build();
	}
	
	// update artikel
	@PUT
	@Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateArtikel(@Valid Artikel artikel) {
		as.updateArtikel(artikel);
	}
}
