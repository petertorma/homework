package com.mycompany.rest;

import java.io.Serializable;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import tp.jpnpark.entities.GuestBook;
import tp.jpnpark.services.GuestBookService;


/**
 *
 * @author Torma Péter
 */
@Path("/guestbook")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GuestBookRESTService implements Serializable {

    @Inject
    private GuestBookService guestBookService;

    @GET
    @Path("/{gbId}")
    public GuestBook getVisitor(@PathParam("gbId") long gbId) {
        return guestBookService.find(gbId);
    }

    @POST
    @Path("/{visitorId}/{parkId}")
    public GuestBook create(GuestBook guestBook, @PathParam("visitorId") long visitorId, @PathParam("parkId") long parkId) {
        return guestBookService.create(guestBook, visitorId, parkId);
    }

    @PUT
    @Path("/{gbId}")
    public GuestBook update(@PathParam("gbId") long gbId, GuestBook guestBook) {
        return guestBookService.update(gbId, guestBook);
    }

    @DELETE
    @Path("/{gbId}")
    public void delete(@PathParam("gbId") long gbId) {
        guestBookService.delete(gbId);
    }

}
