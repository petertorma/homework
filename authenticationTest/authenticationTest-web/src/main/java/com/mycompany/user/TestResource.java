/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Torma Péter
 */
@Path("test")
public class TestResource {

    @GET
    @Path("/useronly")
    @RolesAllowed({Roles.USER})
    public String getUserOnlyMessage() {
        return "this is a user only message";
    }

    @GET
    @Path("moderatoronly")
    @RolesAllowed({Roles.ADMIN, Roles.MODERATOR})
    public String getModeratorMessageOnly() {
        return "moderator only message";
    }

    @GET
    @Path("adminonly")
    @RolesAllowed({Roles.ADMIN})
    public String getAdminMessage() {
        return "admin message only";
    }

    @GET
    @Path("public")
    @PermitAll
    public String getPublic() {
        return "message for all";
    }

}
