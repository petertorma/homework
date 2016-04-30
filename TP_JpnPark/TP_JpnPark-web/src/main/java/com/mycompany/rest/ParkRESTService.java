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
import tp.jpnpark.entities.Park;
import tp.jpnpark.services.ParkService;

/**
 *
 * @author Torma Péter
 */
@Path("/park")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ParkRESTService implements Serializable {

    @Inject
    private ParkService parkService;

    @GET
    @Path("/get/{parkId}")
    public Park getVisitor(@PathParam("parkId") long parkId) {
        return parkService.find(parkId);
    }

    @POST
    @Path("/create/{addressId}")
    public Park create(Park park, @PathParam("addressId") long addressId) {
        return parkService.create(park, addressId);
    }

    @PUT
    @Path("/update/{parkId}")
    public Park update(@PathParam("parkId") long parkId, Park park) {
        return parkService.update(park, parkId);
    }

    @DELETE
    @Path("/delete/{parkId}")
    public void delete(@PathParam("parkId") long parkId) {
        parkService.Delete(parkId);
    }

    @PUT
    @Path("/enter/visitor/{visitorId}/{parkId}")
    public void enterVisitorToPark(@PathParam("visitorId") long visitorId, @PathParam("parkId") long parkId) {
        parkService.addVisitor(visitorId, parkId);
    }

    @PUT
    @Path("/exit/visitor/{visitorId}/{parkId}")
    public void exitVisitorFromPark(@PathParam("visitorId") long visitorId, @PathParam("parkId") long parkId) {
        parkService.exitVisitor(parkId, visitorId);
    }

    @POST
    @Path("/log/{visitorId}/{parkId}/{gbId}")
    public void log(@PathParam("visitorId") long visitorId, @PathParam("parkId") long parkId, @PathParam("gbId") long gbId, String message) {
        parkService.LogIntoGuestBook(parkId, visitorId, gbId, message);
    }

    @DELETE
    @Path("/delete/machine/{machineId}/{parkId}")
    public void deleteMachineFromPark(@PathParam("machineId") long machineId, @PathParam("parkId") long parkId) {
        parkService.deleteMachineFromPark(parkId, machineId);
    }

    @PUT
    @Path("/add/machine/{machineId}/{parkId}")
    public void addMachineToPark(@PathParam("machineId") long machineId, @PathParam("parkId") long parkId) {
        parkService.addMachine(parkId, machineId);
    }

    @PUT
    @Path("/add/visitor/{visitorId}/{parkId}/{machineId}")
    public void addVisitorToMachine(@PathParam("machineId") long machineId, @PathParam("parkId") long parkId, @PathParam("visitorId") long visitorId) {
        parkService.enterVisitorOnMachine(parkId, machineId, visitorId);
    }

    @PUT
    @Path("/delete/visitor/{visitorId}/{parkId}/{machineId}")
    public void exitVisitorFromMachine(@PathParam("machineId") long machineId, @PathParam("parkId") long parkId, @PathParam("visitorId") long visitorId) {
        parkService.exitvisitorFromMachine(parkId, machineId, visitorId);
    }
    
    @GET
    @Path("/logs/{parkId}{gbId}")
    public String getLogs(@PathParam("parkId") long parkId,@PathParam("gbId") long gbId){
        return parkService.getGuestBookLogs(parkId,gbId);
    }

}
