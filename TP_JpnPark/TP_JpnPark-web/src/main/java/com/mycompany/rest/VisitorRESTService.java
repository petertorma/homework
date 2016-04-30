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
import tp.jpnpark.entities.Visitor;
import tp.jpnpark.services.VisitorService;

/**
 *
 * @author Torma Péter
 */
@Path("/visitors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VisitorRESTService implements Serializable {

    @Inject
    private VisitorService visitorService;

    @GET
    @Path("/get/{visitorId}")
    public Visitor getVisitor(@PathParam("visitorId") long visitorId) {
        return visitorService.find(visitorId);
    }

    @POST
    public Visitor create(Visitor visitor) {
        return visitorService.create(visitor);
    }

    @PUT
    @Path("/update/{visitorId}")
    public Visitor update(@PathParam("visitorId") long visitorId, Visitor visitor) {
        return visitorService.update(visitorId, visitor);
    }

    @DELETE
    @Path("/delete/{visitorId}")
    public void delete(@PathParam("visitorId") long visitorId) {
        visitorService.delete(visitorId);
    }
}
