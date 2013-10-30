package de.shop.bestellverwaltung.rest;

import static de.shop.util.Constants.ADD_LINK;
import static de.shop.util.Constants.REMOVE_LINK;
import static de.shop.util.Constants.SELF_LINK;
import static de.shop.util.Constants.UPDATE_LINK;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.MediaType.TEXT_XML;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import de.shop.bestellverwaltung.domain.AbstractArtikel;
import de.shop.kundenverwaltung.domain.AbstractKunde;
import de.shop.util.Mock;
import de.shop.util.rest.NotFoundException;
import de.shop.util.rest.UriHelper;

/**
 * @author <a href="mailto:Juergen.Zimmermann@HS-Karlsruhe.de">J&uuml;rgen Zimmermann</a>
 * 
 */
@Path("/artikel")
@Produces({ APPLICATION_JSON, APPLICATION_XML + ";qs=0.75", TEXT_XML + ";qs=0.5" })
@Consumes
public class ArtikelResource {
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private UriHelper uriHelper;
	
	@GET
	@Produces({ TEXT_PLAIN, APPLICATION_JSON })
	@Path("version")
	public String getVersion() {
		return "1.0";
	}
	
	@GET
	@Path("{id:[1-9][0-9]*}")
	public Response findArtikelById(@PathParam("id") long id) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		final AbstractArtikel artikel = Mock.findArtikelById(id);
		if (artikel == null) {
			throw new NotFoundException("Keinen Artikel mit der ID " + id + " gefunden.");
		}
		
		// Link-Header setzen
		final Response response = Response.ok(artikel)
                                          .links(getTransitionalLinks(artikel, uriInfo))
                                          .build();
		
		return response;
	}
	
	private Link[] getTransitionalLinks(AbstractAtrikel artikel, UriInfo uriInfo) {
		final Link self = Link.fromUri(getUriArtikel(artikel, uriInfo))
                              .rel(SELF_LINK)
                              .build();
		
		final Link add = Link.fromUri(uriHelper.getUri(ArtikelResource.class, uriInfo))
				.rel(ADD_LINK)
				.build;
		
		final Link update = Link.fromUri(uriHelper.getUri(ArtikelResource.class, uriInfo))
				.rel(UPDATE_LINK)
				.build;
		
		final Link remove = Link.fromUri(uriHelper.getUri(ArtikelResource.class, uriInfo))
				.rel(REMOVE_LINK)
				.build;
		
		return new Link[] { self, add, update, remove };
	}
	
	public URI getUriArtikel(AbstractAtrikel artikel, UriInfo uriInfo) {
		return uriHelper.getUri(ArtikelResource.class, "findArtikelById", artikel.getId(), uriInfo);
	}
	
	@POST
	@Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public Response createKunde(AbstractArtikel artikel) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		artikel = Mock.createArtikel(artikel);
		return Response.created(getUriArtikel(artikel, uriInfo))
			           .build();
	}
	
	@PUT
	@Consumes({APPLICATION_JSON, APPLICATION_XML, TEXT_XML })
	@Produces
	public void updateKunde(AbstractArtikel artikel) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		Mock.updateArtikel(artikel);
	}
	
	@DELETE
	@Path("{id:[1-9][0-9]*}")
	@Produces
	public void deleteArtikel(@PathParam("id") Long artikelId) {
		// TODO Anwendungskern statt Mock, Verwendung von Locale
		Mock.deleteArtikel(artikelId);
	}
}
