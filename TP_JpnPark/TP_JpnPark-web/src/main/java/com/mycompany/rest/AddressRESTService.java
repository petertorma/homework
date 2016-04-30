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
import tp.jpnpark.entities.Address;
import tp.jpnpark.services.AddressService;

/**
 *
 * @author Torma Péter
 */
@Path("/address")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddressRESTService implements Serializable {

    @Inject
    private AddressService addressService;

    @POST
    public Address create(Address address) {
        return addressService.create(address);
    }

    @GET
    @Path("/{addressId}")
    public Address find(@PathParam("addressId") long addressId) {
        return addressService.find(addressId);
    }

    @PUT
    @Path("/{addressId}")
    public Address update(@PathParam("addressId") long addressId, Address address) {
        return addressService.update(addressId, address);
    }

    @DELETE
    @Path("/{addressId}")
    public void delete(@PathParam("addressId") long addressId) {
        addressService.delete(addressId);
    }

}
