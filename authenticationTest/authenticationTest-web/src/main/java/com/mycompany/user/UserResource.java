package com.mycompany.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author Torma Péter
 */
@Path("user")
public class UserResource {

    @POST
    @Path("login")
    public Response login(@Context HttpServletRequest request, User user) throws ServletException {
        request.getSession();
        request.login(user.getUsername(), user.getPassword());
        return Response.ok().entity("sikeres bejelentkezés: " + user.getUsername()).build();
    }
    
    public Response logout(@Context HttpServletRequest request) throws ServletException{
        request.getSession();
        request.logout();
        return Response.ok().build();
    }
}
