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
import tp.jpnpark.entities.Machine;
import tp.jpnpark.services.MachineService;

/**
 *
 * @author Torma Péter
 */
@Path("/machine")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MachineRESTService implements Serializable {

    @Inject
    private MachineService machineService;

    @GET
    @Path("{machineId}")
    public Machine find(@PathParam("machineId") long machineId) {
        return machineService.find(machineId);
    }

    @POST
    public Machine create(Machine machine) {
        return machineService.create(machine);
    }

    @PUT
    @Path("{machineId}")
    public Machine update(@PathParam("machineId") long machineId) {
        return machineService.update(machineId);
    }

    @DELETE
    @Path("{machineId}")
    public void delete(@PathParam("machineId") long machineId) {
        machineService.delete(machineId);
    }

}
