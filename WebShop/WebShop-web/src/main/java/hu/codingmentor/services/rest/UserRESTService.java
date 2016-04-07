package hu.codingmentor.services.rest;

import hu.codingmentor.dto.UserDTO;
import hu.codingmentor.services.UserManagementService;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.Stateless;
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

@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UserRESTService implements Serializable {

    @Inject
    UserManagementService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO addUser(UserDTO user) {
        return userService.addUser(user);
    }

    @DELETE
    @Path("/{userName}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO removeUser(@PathParam("userName") String username) {
        return userService.removeUser(username);
    }

    @PUT
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public UserDTO editUser(@PathParam("username") String username, UserDTO user) {
        if (!user.getUsername().equals(username)) {
            throw new IllegalArgumentException("Username error");
        }

        return userService.editUser(user);
    }

    @GET
    public Collection<UserDTO> getUsers() {
        return userService.getUsers();
    }

    @GET
    @Path("/{userName}")
    public UserDTO getUserByUsername(@PathParam("userName") String username) {
        return userService.getUser(username);
    }
}
