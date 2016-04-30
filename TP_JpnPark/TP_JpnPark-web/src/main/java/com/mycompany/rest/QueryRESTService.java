package com.mycompany.rest;

import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import tp.jpnpark.entities.GuestBook;
import tp.jpnpark.entities.Machine;
import tp.jpnpark.entities.Visitor;
import tp.jpnpark.services.QueryServices;

/**
 *
 * @author Torma Péter
 */
@Path("/query")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QueryRESTService implements Serializable {

    @Inject
    QueryServices queryService;
/*
    
    @GET
    @Path("/machines/{parkId}")
    public List<Machine> getMachinesByParkId(@PathParam("parkId") Long parkId) {
        return queryService.machinesInPark(parkId);
    }

    @GET
    @Path("/logByVisitor/{parkId}/{visitorId}")
    public List<GuestBook> logByVisitor(@PathParam("parkId") Long parkId, @PathParam("visitorId") Long visitorId) {
        return queryService.logByVisitor(parkId, visitorId);
    }

       @GET
    @Path("/visitorsOnMachine/{machineId}")
    public List<Visitor> visitorsOnMachine(@PathParam("machineId") Long machineId) {
    return queryService.visitorsOnMachine(machineId);
    }
    
    @GET
    @Path("/actionVisitors/{parkId}")
    public Integer actionVisitors(@PathParam("parkId") Long parkId) {
    return queryService.actionVisitors(parkId);
    }
    
    @GET
    @Path("/tiredVisitors/{parkId}")
    public Integer tiredVisitors(@PathParam("parkId") Long parkId) {
    return queryService.tiredVisitors(parkId);
    }*/

}
